package com.exe.shell.service;

import com.exe.shell.common.AppResultObj;

public interface ExeShellService {

	/**
	 * linux下执行 shell 命令
	 * 
	 * @param shell
	 * @return
	 */
	AppResultObj executeShell(String shell);

	/**
	 * linux下执行 shell 命令 executeShellSh
	 * 
	 * @param shell
	 * @return
	 */
	AppResultObj executeShellSh(String shell);

}