package com.mxz.service.rss;

import com.youbenzi.mdtool.tool.MDTool;
import org.apache.ibatis.io.Resources;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;

/**
 * TODO: 注释.
 *
 * @author mxz on 2020/08/22 14:52
 */
@Controller
@RequestMapping("/rss")
public class RssController {

    @RequestMapping("/sky")
    public void sky(HttpServletResponse response) throws Exception {
        File file = null;
        try {
            ClassPathResource classPathResource = new ClassPathResource("demo");

            // 获得File对象，当然也可以获取输入流对象
            file = classPathResource.getFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            /**
             getResource()方法会去classpath下找这个文件，获取到url resource, 得到这个资源后，调用url.getFile获取到 文件 的绝对路径
             */
            URL url = classLoader.getResource("demo");
            file = new File(url.getFile());
        } catch (Exception e) {
            e.printStackTrace();
        }

        FileReader reader = new FileReader(file);
        StringBuilder sb = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(reader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            // ... handle IO exception
        }
        //这句话的意思，是告诉servlet用UTF-8转码，而不是用默认的ISO8859
        response.setCharacterEncoding("UTF-8");

        response.getWriter().write(sb.toString());
    }
}
