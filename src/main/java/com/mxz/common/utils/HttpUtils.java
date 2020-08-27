package com.mxz.common.utils;

import com.google.common.net.MediaType;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import javax.net.ssl.*;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 基于<a href="https://square.github.io/okhttp">OKHttp3</a>的HTTP请求封装.
 */
@Slf4j
public final class HttpUtils {
    static Pattern urlP = Pattern.compile("<a.*class=\"postTitle2 vertical-middle\".*href=\"(.*)\">");
    static Pattern titleTopP = Pattern.compile("<a.*class=\"postTitle2 vertical-middle\".*\n" +
            ".*<span>\n" +
            "(.*\n" +
            ".*)\n" +
            ".*</span>");
    static Pattern titleP = Pattern.compile("<a.*class=\"postTitle2 vertical-middle\".*\n" +
            ".*<span>\n" +
            "(.*)\n" +
            ".*</span>");

    /**
     * 最大空闲连接数.
     */
    private static final Integer MAX_IDLE_CONNECTIONS = 10;
    /**
     * 连接保持时长, 分钟.
     */
    private static final Integer KEEP_ALIVE_DURATION = 5;
    /**
     * 连接超时时间, 秒.
     */
    private static final Integer TIMEOUT_CONNECT = 30;
    /**
     * 读超时时间, 秒.
     */
    private static final Integer TIMEOUT_READ = 30;
    /**
     * 写超时时间, 秒.
     */
    private static final Integer TIMEOUT_WRITE = 10;

    /**
     * 设置最大连接数和每主机最大连接数.
     */
    private static final Dispatcher DISPATCHER;
    /**
     * {@link OkHttpClient}.
     */
    private static final OkHttpClient HTTP;
    /**
     * 忽略证书的HTTP客户端.
     */
    private static final OkHttpClient HTTP_SSL;

    static {
        /**
         * 初始化{@link OkHttpClient}.
         */
        DISPATCHER = new Dispatcher();
        DISPATCHER.setMaxRequests(64);
        DISPATCHER.setMaxRequestsPerHost(5);

        HTTP =
                new OkHttpClient.Builder().
                        dispatcher(DISPATCHER).
                        connectionPool(new ConnectionPool(MAX_IDLE_CONNECTIONS, KEEP_ALIVE_DURATION, TimeUnit.MINUTES)).
                        connectTimeout(TIMEOUT_CONNECT, TimeUnit.SECONDS).
                        writeTimeout(TIMEOUT_WRITE, TimeUnit.SECONDS).
                        readTimeout(TIMEOUT_READ, TimeUnit.SECONDS).
                        build();

        HTTP_SSL =
                new OkHttpClient.Builder().
                        dispatcher(DISPATCHER).
                        connectionPool(new ConnectionPool(MAX_IDLE_CONNECTIONS, KEEP_ALIVE_DURATION, TimeUnit.MINUTES)).
                        connectTimeout(TIMEOUT_CONNECT, TimeUnit.SECONDS).
                        writeTimeout(TIMEOUT_WRITE, TimeUnit.SECONDS).
                        readTimeout(TIMEOUT_READ, TimeUnit.SECONDS).
                        sslSocketFactory(_getSSLSocketFactory(), _x509TrustManager()).
                        hostnameVerifier(_getHostnameVerifier()).
                        build();
    }

    private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");
    private static final MediaType MEDIA_TYPE_XML = MediaType.parse("application/xml");

    private HttpUtils() {
        super();
    }

    /**
     * GET请求, 返回值通过{@link Function}转换成对象.
     *
     * @param url               请求地址.
     * @param headers           请求头.
     * @param responseConverter {@link Function}, 响应值转换接口.
     * @param <T>
     * @return 值对象.
     */
    public static <T> T get(String url, Map<String, String> headers, Function<String, T> responseConverter) {
        Request.Builder requestBuilder = _build(url, headers);
        return _request(requestBuilder, responseConverter, HTTP);
    }

    public static String get(String url) {
        Request.Builder requestBuilder = _build(url, null);
        return _request(requestBuilder, item -> item, HTTP);
    }

    /**
     * get请求
     *
     * @param url
     * @param headerMap
     * @return
     */
    public static <T> T getByHttps(String url,Map<String,String> headerMap, Function<String, T> responseConverter) {
        Request.Builder request = new Request.Builder().get();
        StringBuilder urlBuilder = new StringBuilder(url);

        request.url(urlBuilder.toString());

        if (headerMap != null) {
            try {
                for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                    request.addHeader(entry.getKey(), entry.getValue());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return _request(request, responseConverter, HTTP_SSL);
    }

    /**
     * 内部构建{@link Request.Builder}.
     *
     * @param url     请求地址.
     * @param headers 请求头.
     * @return {@link Request.Builder}.
     */
    private static Request.Builder _build(String url, Map<String, String> headers) {
        Request.Builder requestBuilder = new Request.Builder().url(url).get();
        /**
         * 设置头.
         */
        Optional.
                ofNullable(headers).
                ifPresent(
                        headersAsNotNull ->
                                headersAsNotNull.entrySet().stream().forEach(entry -> requestBuilder.addHeader(entry.getKey(), entry.getValue()))
                );
        return requestBuilder;
    }

    /**
     * 发起请求, 并获取返回值通过{@link Function}转换成值对象.
     *
     * @param requestBuilder    {@link Request.Builder}.
     * @param responseConverter {@link Function}, 值对象转换接口.
     * @param <T>
     * @return 值对象.
     */
    private static <T> T _request(Request.Builder requestBuilder, Function<String, T> responseConverter, OkHttpClient httpClient) {
        try (Response response = httpClient.newCall(requestBuilder.build()).execute()) {
            if (!response.isSuccessful()) {

            }
            return responseConverter.apply(response.body().string());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 文件下载时段大小设置.
     */
    private static final int DOWNLOAD_CHUNK_SIZE = 2048;


    /**
     * SSL通道工程构造器.
     *
     * @return {@link SSLSocketFactory}.
     */
    private static SSLSocketFactory _getSSLSocketFactory() {
        try {
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, _getTrustManager(), new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 信任管理器队列.
     * <p>
     * 忽略所有证书，不抛出异常.
     * </p>
     *
     * @return {@link TrustManager[]}.
     */
    private static TrustManager[] _getTrustManager() {
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[]{};
                    }
                }
        };
        return trustAllCerts;
    }

    /**
     * Hostname校验器.
     * <p>
     * 直接返回TRUE.
     * </p>
     *
     * @return {@link HostnameVerifier}.
     */
    private static HostnameVerifier _getHostnameVerifier() {
        HostnameVerifier hostnameVerifier = new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return true;
            }
        };
        return hostnameVerifier;
    }

    /**
     * 获取X509TrustManager管理器.
     *
     * @return {@link X509TrustManager}.
     */
    private static X509TrustManager _x509TrustManager() {
        return new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
    }
}
