package com.exe.shell.controller;

import com.exe.shell.common.AppResultObj;
import com.exe.shell.service.PythonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/com/exe/shell")
@Api("-- 执行python程序 --")
public class PythonController {

    private static final Logger logger = LoggerFactory.getLogger(PythonController.class);

    @Autowired
    PythonService pythonService;

    /**
     * 电表识别
     *
     * @param file
     * @param request
     * @return
     */
    @ApiOperation(value = "电表识别", notes = "电表识别")
    @PostMapping(value = "/ammeter-recognition")
    public @ResponseBody
    AppResultObj ammeterRecognition(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request) {
        logger.info("========电表识别入参数，请求服务器ip：【{}】", request.getRemoteHost());
        return pythonService.ammeterRecognition(file);
    }

    /**
     * 分割单
     *
     * @param file
     * @param request
     * @return
     */
    @ApiOperation(value = "分割单", notes = "分割单")
    @PostMapping(value = "/split-list")
    public @ResponseBody
    AppResultObj splitList(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request) {
        logger.info("========分割单入参数，请求服务器ip：【{}】", request.getRemoteHost());
        return pythonService.splitList(file);
    }

}
