package com.mhuang.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

/**
 * 
 * @Description Netty 服务处理
 * @author mHuang
 * @date 2015年7月16日 下午3:19:56 
 * @version V1.0.0
 */
public class NettyServerHandler extends ChannelHandlerAdapter {

	public static volatile Long countWidh = 0L;
	
	/**
	 * 
	 * Description 接收客户端数据后返回
	 * @param ctx
	 * @param msg
	 * @throws Exception 
	 * @see io.netty.channel.ChannelHandlerAdapter#channelRead(io.netty.channel.ChannelHandlerContext, java.lang.Object)
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		try{
			System.out.println("====success====");
			System.out.println((String) msg);
			ctx.writeAndFlush("再见"+msg);
		}finally{
			ReferenceCountUtil.release(msg);
		}
	}
	
	
	/**
	 * 
	 * Description 客户端连接成功后返回
	 * @param ctx
	 * @throws Exception 
	 * @see io.netty.channel.ChannelHandlerAdapter#channelActive(io.netty.channel.ChannelHandlerContext)
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		Channel channel = ctx.channel();
		System.out.println(channel.id().asLongText());
		System.out.println("====server connection success====");
		ctx.writeAndFlush("welcome to netty server"+(++countWidh));
	}

	/**
	 * 
	 * Description 异常处理
	 * @param ctx
	 * @param cause
	 * @throws Exception 
	 * @see io.netty.channel.ChannelHandlerAdapter#exceptionCaught(io.netty.channel.ChannelHandlerContext, java.lang.Throwable)
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
