package com.mhuang.common.server;

/**
 * 
 * @Description netty 服务端接口
 * @author mHuang
 * @date 2015年7月16日 下午3:04:22 
 * @version V1.0.0
 */
public interface MyNettyServer {

	public void bind();
	
	public void bind(int port);
}
