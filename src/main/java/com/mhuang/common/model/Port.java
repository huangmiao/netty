package com.mhuang.common.model;

import java.io.Serializable;

/**
 * 
 * @Description 端口
 * @author mHuang
 * @date 2015年7月17日 上午9:00:32 
 * @version V1.0.0
 */
public class Port implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public static final int DEFAULT_PORT = 8000;
	
	protected int port = DEFAULT_PORT;

	public Port(){}
	
	public Port(int port){
		this.port = port;
	}
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}
