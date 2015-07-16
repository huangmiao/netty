package com.mhuang.client;

import com.mhuang.App;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * 
 * @Description netty 客户端处理
 * @author mHuang
 * @date 2015年7月16日 下午3:40:09 
 * @version V1.0.0
 */
public class NettyClientHandler extends  ChannelHandlerAdapter{

	/**
	 * 
	 * Description  链接成功后执行
	 * @param ctx
	 * @throws Exception 
	 * @see io.netty.channel.ChannelHandlerAdapter#channelActive(io.netty.channel.ChannelHandlerContext)
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println(ctx.channel().id().asLongText());
		System.out.println("client connection success");
		new App().start();;
		while(true){
			for(String msg:App.msgList){
				ctx.write(msg);
			}
			ctx.flush();
			break;
		}
	}

	/**
	 * 
	 * Description 客户端异常
	 * @param ctx
	 * @param cause
	 * @throws Exception 
	 * @see io.netty.channel.ChannelHandlerAdapter#exceptionCaught(io.netty.channel.ChannelHandlerContext, java.lang.Throwable)
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		cause.printStackTrace();
	}
	
	
	/**
	 * 
	 * Description 获取服务端传过来的数据
	 * @param ctx
	 * @param msg
	 * @throws Exception 
	 * @see io.netty.channel.ChannelHandlerAdapter#channelRead(io.netty.channel.ChannelHandlerContext, java.lang.Object)
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		System.out.println("====链接成功后的数据====");
		System.out.println("链接"+msg);
	}

	/**
	 * 
	 * Description 获取成功刷新
	 * @param ctx
	 * @throws Exception 
	 * @see io.netty.channel.ChannelHandlerAdapter#channelReadComplete(io.netty.channel.ChannelHandlerContext)
	 */
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}
}
