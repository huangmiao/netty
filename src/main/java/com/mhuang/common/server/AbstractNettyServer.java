package com.mhuang.common.server;

import com.mhuang.common.model.Port;

/**
 * 
 * @Description netty 服务 抽象
 * @author mHuang
 * @date 2015年7月16日 下午5:52:54 
 * @version V1.0.0
 */
public abstract class AbstractNettyServer extends Port implements MyNettyServer{

	private static final long serialVersionUID = 1L;

	public AbstractNettyServer(){}
	
	public AbstractNettyServer(int port){
		super(port);
	}
	
	//TODO 添加业务
}
