package com.exe.shell.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


/**
 * Python shell 命令 参数
 *
 * @author tiger
 * @date 2018年12月5日
 */
@Configuration
@ConfigurationProperties(prefix = "shell")
public class ShellString {

    private String path;
    private String str;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
