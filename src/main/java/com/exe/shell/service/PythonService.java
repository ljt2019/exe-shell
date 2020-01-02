package com.exe.shell.service;

import com.exe.shell.common.AppResultObj;
import org.springframework.web.multipart.MultipartFile;

public interface PythonService {

    /**
     * 电表识别:
     * 1、图片上传到服务器指定目录
     * 2、调用python程序对图片进行识别
     * 3、识别后返回json字符串
     *
     * @param file
     * @return
     */
    AppResultObj ammeterRecognition(MultipartFile file);

    /**
     * 分割单：
     * 1、图片上传到服务器指定目录
     * 2、调用python程序对图片进行识别
     * 3、识别后返回json字符串
     *
     * @param file
     * @return
     */
    AppResultObj splitList(MultipartFile file);

}