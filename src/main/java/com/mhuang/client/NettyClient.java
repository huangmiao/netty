package com.mhuang.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import com.mhuang.common.client.MyNettyClient;

/**
 * 
 * @Description netty 客户端 Demo
 * @author mHuang
 * @date 2015年7月16日 下午2:55:36 
 * @version V1.0.0
 */
public class NettyClient implements MyNettyClient{

	@Override
	public void connect(String host,int port) {
		//config thread pool
        EventLoopGroup group=new NioEventLoopGroup();
        try{
            //config client aotu property
            Bootstrap b=new Bootstrap();
            b.group(group).channel(NioSocketChannel.class)
            .option(ChannelOption.TCP_NODELAY, true)
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 30)
            .handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    //添加POJO对象解码器 禁止缓存类加载器
                    ch.pipeline().addLast(new ObjectDecoder(1024,ClassResolvers.cacheDisabled(this.getClass().getClassLoader())));
                    //设置发送消息编码器
                    ch.pipeline().addLast(new ObjectEncoder());
                    //设置网络IO处理器
                    ch.pipeline().addLast(new NettyClientHandler());
                }
            });
            // Start the client.
            ChannelFuture f=b.connect(host,port).sync();
           
            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
            group.shutdownGracefully();
        }
	}

	public static void main(String[] args){
		MyNettyClient nettyClient = new NettyClient();
		nettyClient.connect("127.0.0.1", 8001);
	}
}
