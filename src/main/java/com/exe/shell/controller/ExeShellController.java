package com.exe.shell.controller;

import com.exe.shell.service.ExeShellService;
import com.exe.shell.common.AppResultObj;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Api("java执行 shell 命令")
@RestController
@RequestMapping("/shell")
public class ExeShellController {

    //    private static final Logger logger = LoggerFactory.getLogger(ExeShellController.class);
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ExeShellService exeShellService;

    @GetMapping("/executeShell")
    @ApiOperation(value = "java执行 shell 命令-window", notes = "java执行 shell 命令")
    public AppResultObj executeShell(@RequestParam String shell, HttpServletRequest request) {
        logger.info("=== executeShell请求ip：【{}】, shell命令:【{}】", request.getRemoteHost(), shell);
        try {
            return exeShellService.executeShell(shell);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AppResultObj.serverError();
    }

    @GetMapping("/executeShellSh")
    @ApiOperation(value = "java执行 shell 命令linux", notes = " /bin/sh,java执行 shell 命令")
    public AppResultObj executeShellSh(@RequestParam String shell, HttpServletRequest request) {
        logger.info("====executeShellSh请求ip：【{}】, shell命令:【{}】", request.getRemoteHost(), shell);
        try {
            return exeShellService.executeShellSh(shell);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AppResultObj.serverError();
    }

    /**
     * 测试接口
     *
     * @return
     * @throws IOException
     */
    @ApiOperation(value = "测试", response = AppResultObj.class, notes = "3406 参数错误 2000 成功")
    @GetMapping("/test")
    public AppResultObj test(HttpServletRequest request) throws IOException {

        try {
            logger.info("======== 测试接口，请求服务器ip：【{}】", request.getRemoteHost());
            return AppResultObj.success("测试通过！");
        } catch (Exception e) {
            logger.info("========== 测试失败！ ==========【{}】", e.getMessage());
            return AppResultObj.serverError(e.getMessage());
        }
    }

    @RequestMapping("/k8s")
    public String k8s() {
        return "hello K8s <br/>111222 ======tiger1111";
    }
}
