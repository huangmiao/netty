package com.mhuang.common.client;

import com.mhuang.common.model.Host;

/**
 * 
 * @Description Netty Client 抽象
 * @author mHuang
 * @date 2015年7月16日 下午5:57:13 
 * @version V1.0.0
 */
public abstract class AbstractNettyClient extends Host implements MyNettyClient{
	
	private static final long serialVersionUID = 1L;

	public AbstractNettyClient(){}
	
	public AbstractNettyClient(int port){}
	
	public AbstractNettyClient(String host, int port) {
		super(host, port);
	}
	
	//TODO 添加业务
}
