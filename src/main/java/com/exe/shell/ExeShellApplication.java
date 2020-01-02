package com.exe.shell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExeShellApplication {

    /**
     * 测试环境：cd /opt/exepython
     * 在linux下后台启动项目：nohup java  -jar exe-shell.jar &
     * 实时日志查看：tail -f nohup.out
     *
     * @param args
     */
    public static void main(String[] args) {
        //相当于 logback.xml 中的
        //<property name="LOG_HOME" value="/home/hadoop/logs/exeShell"/>
        System.setProperty("LOG_HOME", "/home/hadoop/logs/exeShell");
        SpringApplication.run(ExeShellApplication.class, args);
    }
}
