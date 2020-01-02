package com.exe.shell.service.impl;

import com.exe.shell.service.ExeShellService;
import com.exe.shell.common.AppResultObj;
import com.exe.shell.common.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Service
public class ExeShellServiceImpl implements ExeShellService {

	private static final Logger logger = LoggerFactory.getLogger(ExeShellServiceImpl.class);

	@Override
	public AppResultObj executeShellSh(String shell) {
		String[] cmdA = { "/bin/sh", "-c", shell };
		return exeShell(null, cmdA);
	}

	@Override
	public AppResultObj executeShell(String shell) {
		return exeShell(shell, null);
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
			int exitValue = process.waitFor();
			if (0 != exitValue) {
				logger.info("----->调用shell脚本失败！返回状态码；【{}】 :", exitValue);
				return AppResultObj.parameterError("调用shell脚本失败:" + exitValue + "--->" + sbError);
			} else {
				logger.info("----->调用shell脚本成功！返回状态码；【{}】 :", exitValue);
			}

			// 关闭流
			stdInput.close();
			stdError.close();

			logger.info("----->exeShellSh执行成功!：【{}】", sbSucceed);
			return AppResultObj.success(sbSucceed);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("----->exeShellSh执行失败!：【{}】", e.getMessage());
			return AppResultObj.serverError(e.getMessage());
		}
	}
}