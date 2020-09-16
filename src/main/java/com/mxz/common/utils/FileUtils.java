package com.mxz.common.utils;

import com.google.common.io.Files;

import java.io.*;
import java.nio.file.Paths;

/**
 * 文件工具
 *
 * @author mxz on 2020/08/27 16:50
 */
public class FileUtils {

    public void writeFile(String fileContent, String path) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(path);
        byte[] strToBytes = fileContent.getBytes();
        outputStream.write(strToBytes);
        outputStream.close();
    }

    public void writeFile2(String fileContent, String path) throws IOException {
        FileWriter fileWriter = new FileWriter(path);
        fileWriter.write(fileContent);
        fileWriter.close();
    }

    public static byte[] inputStreamTOByte(InputStream in) throws IOException {

        int BUFFER_SIZE = 4096;
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[BUFFER_SIZE];
        int count = -1;

        while((count = in.read(data,0,BUFFER_SIZE)) != -1){
            outStream.write(data, 0, count);
        }

        data = null;
        byte[] outByte = outStream.toByteArray();
        outStream.close();

        return outByte;
    }
}
