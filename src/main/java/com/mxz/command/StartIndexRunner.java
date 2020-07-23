package com.mxz.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * TODO: 注释.
 *
 * @author mxz on 2020/03/31 17:25
 */
@Component
public class StartIndexRunner implements CommandLineRunner {
    private static Logger logger = LoggerFactory.getLogger(StartIndexRunner.class);

    @Value("${spring.web.loginurl}")
    private String loginUrl;

    @Value("${spring.web.googleexecute}")
    private String googleExecutePath;

    @Value("${spring.auto.openurl}")
    private boolean isOpen;

    @Autowired
    private Environment environment;

    @Override
    public void run(String... strings) throws Exception {
        String port = environment.getProperty("server.port");
        if(isOpen){
            String cmd = googleExecutePath +" "+ loginUrl+":"+port;
            Runtime run = Runtime.getRuntime();
            try{
                run.exec(cmd);
                logger.debug("启动浏览器打开项目成功");
            }catch (Exception e){
                e.printStackTrace();
                logger.error(e.getMessage());
            }
        }
    }
}
