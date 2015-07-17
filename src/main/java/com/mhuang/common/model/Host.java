package com.mhuang.common.model;

import java.io.Serializable;

/**
 * 
 * @Description 主机
 * @author mHuang
 * @date 2015年7月17日 上午9:01:07 
 * @version V1.0.0
 */
public class Host extends Port implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public static final String DEFAULT_HOST = "127.0.0.1";
	
	protected String host = DEFAULT_HOST;

	public Host(){
	}
	
	public Host(String host,int port){
		this.host = host;
		this.port = port;
	}
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
}
