package com.exe.shell.service.impl;

import com.exe.shell.common.AppResultObj;
import com.exe.shell.common.JsonUtils;
import com.exe.shell.service.PythonService;
import com.exe.shell.common.FileUtils;
import com.exe.shell.config.PythonString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

@Service
public class PythonServiceImpl implements PythonService {

    private static final Logger logger = LoggerFactory.getLogger(PythonServiceImpl.class);

    @Autowired
    PythonString pythonString;

    @Override
    public AppResultObj ammeterRecognition(MultipartFile file) {

        //文件上传
        AppResultObj ret = FileUtils.uploadOne(pythonString.getPath1Picture(), file);

        //python程序调用

        if (ret.getCode() != AppResultObj.CODE_OK) {
            return ret;
        }
        //python程序调用

        String[] cmdA = {"/bin/sh", "-c", pythonString.getPath1() + " " + ret.getData().toString()};
        return exeShell(null, cmdA);
    }

    @Override
    public AppResultObj splitList(MultipartFile file) {
        //文件上传
        AppResultObj ret = FileUtils.uploadOne(pythonString.getPath2Picture(), file);

        if (ret.getCode() != AppResultObj.CODE_OK) {
            return ret;
        }
        //python程序调用

        String[] cmdA = {"/bin/sh", "-c", pythonString.getPath2() + " " + ret.getData().toString()};
        return exeShell(null, cmdA);
    }

    /**
     * shell 命令
     *
     * @param shellString
     * @param cmdA
     * @return
     */
    private AppResultObj exeShell(String shellString, String[] cmdA) {

        StringBuffer sbSucceed = new StringBuffer();
        StringBuffer sbError = new StringBuffer();

        try {

            Process process = null;

            if (!StringUtils.isEmpty(shellString)) {
                logger.info("----->shell 参数:【{}】 ", shellString);
                process = Runtime.getRuntime().exec(shellString);
            }
            if (!StringUtils.isEmpty(cmdA)) {
                logger.info("----->shell 参数:【{}】 ", JsonUtils.toJson(cmdA));
                process = Runtime.getRuntime().exec(cmdA);
            }

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            String s;
            while ((s = stdInput.readLine()) != null) {
                sbSucceed.append(s);
                logger.info("--成功执行!：【{}】", s);
            }
            while ((s = stdError.readLine()) != null) {
                sbError.append(s);
                logger.info("--失败日志执行!：【{}】", s);
            }

            // 调用shell脚本，判断是否正常执行，如果正常结束，Process的waitFor()方法返回0
//            int exitValue = process.waitFor();
//            if (0 != exitValue) {
//                logger.info("----->调用shell脚本失败！返回状态码；【{}】 :", exitValue);
//                return AppResultObj.parameterError("调用shell脚本失败:" + exitValue + "--->" + sbError);
//            } else {
//                logger.info("----->调用shell脚本成功！返回状态码；【{}】 :", exitValue);
//            }

            // 关闭流
            stdInput.close();
            stdError.close();

            logger.info("----->exeShellSh执行成功!sbSucceed = 【{}】", sbSucceed);
            String str = sbSucceed.toString();
            str = str.substring(str.indexOf("{"), str.length());
            logger.info("----->exeShellSh执行成功!str = 【{}】", str);
            return AppResultObj.success(JsonUtils.fromJson(str, Map.class));
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("----->exeShellSh执行失败!：【{}】", e.getMessage());
            return AppResultObj.serverError(e.getMessage());
        }
    }

//    public static void main(String[] args) {
//    String str = "Tensor(\"Placeholder:0\", shape=(?, ?, ?, 3), dtype=float32)Tensor(\"conv5_3/conv5_3:0\", shape=(?, ?, ?, 512), dtype=float32)Tensor(\"rpn_conv/3x3/rpn_conv/3x3:0\", shape=(?, ?, ?, 512), dtype=float32)Tensor(\"lstm_o/Reshape_2:0\", shape=(?, ?, ?, 512), dtype=float32)Tensor(\"lstm_o/Reshape_2:0\", shape=(?, ?, ?, 512), dtype=float32)Tensor(\"rpn_cls_score/Reshape_1:0\", shape=(?, ?, ?, 20), dtype=float32)Tensor(\"rpn_cls_prob:0\", shape=(?, ?, ?, ?), dtype=float32)Tensor(\"Reshape_2:0\", shape=(?, ?, ?, 20), dtype=float32)Tensor(\"rpn_bbox_pred/Reshape_1:0\", shape=(?, ?, ?, 40), dtype=float32)Tensor(\"Placeholder_1:0\", shape=(?, 3), dtype=float32)load vggnet done{\"FGD\": [\"中国移动通信集团广东有限公司佛山分公司\", \"电费\", \"备注\", \"基站名\", \"金额\", \"上期度数本期度数实用电量单价\", \"电表号\", \"5194.8\", \"2009b05661482\", \"608669612998\", \"11.2\", \"4329\", \"3-33\", \"总计:\", \"5194.8\", \"中通毋实业\", \"务得羊\", \"a5\"]}";
//        str = str.substring(str.indexOf("{"),str.length());
//        System.out.println(str);
//    }
}