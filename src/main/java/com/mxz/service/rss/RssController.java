package com.mxz.service.rss;

import com.youbenzi.mdtool.tool.MDTool;
import org.apache.ibatis.io.Resources;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

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
        ClassPathResource classPathResource = new ClassPathResource("demo");

        // 获得File对象，当然也可以获取输入流对象
        File file = classPathResource.getFile();
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
