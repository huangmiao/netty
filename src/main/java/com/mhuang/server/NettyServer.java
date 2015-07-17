package com.mhuang.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import com.mhuang.common.Closeable;
import com.mhuang.common.server.AbstractNettyServer;

/**
 * 
 * @Description netty 服务器
 * @author mHuang
 * @date 2015年7月16日 下午3:05:17
 * @version V1.0.0
 */
public class NettyServer extends AbstractNettyServer implements Closeable {

	public Logger logger = LoggerFactory.getLogger(getClass());
	private static final long serialVersionUID = 1L;

	@Override
	public void bind() {
		bind(port);
	}

	@Override
	public void bind(int port) {
		logger.info("====begin nettyServer bind====");
		// config nio thread group
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			// config client aotu property
			logger.info("====begin nettyServer configuation property====");
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup)
				.channel(NioServerSocketChannel.class)
				.option(ChannelOption.SO_BACKLOG, 100)
				.handler(new LoggingHandler(LogLevel.INFO))
				.childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel ch)
							throws Exception {
						// 添加对象解码器 负责对序列化POJO对象进行解码 设置对象序列化最大长度为1M 防止内存溢出
						// 设置线程安全的WeakReferenceMap对类加载器进行缓存 支持多线程并发访问 防止内存溢出
						ch.pipeline()
								.addLast(
										new ObjectDecoder(
												1024 * 1024,
												ClassResolvers
														.weakCachingConcurrentResolver(this
																.getClass()
																.getClassLoader())));
						// 添加对象编码器 在服务器对外发送消息的时候自动将实现序列化的POJO对象编码
						ch.pipeline().addLast(new ObjectEncoder());
						ch.pipeline().addLast(new NettyServerHandler());
					}
				});
			logger.info("====end nettyServer configuation property====");
			// bind port wait..
			logger.info("====begin nettyServer bind port====");
			ChannelFuture f = b.bind(port).sync();
			logger.info("====end nettyServer bind port====");
			// 等到服务端监听端口关闭
			f.channel().closeFuture().sync();
			logger.info("====end nettyServer bind====");
			logger.info("====nettyServer start ok!====");
		} catch (InterruptedException e) {
			logger.error("==== connection nettyServer interruptedException",e);
			e.printStackTrace();
		} finally {
			logger.info("====begin release nettyServer resource====");
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
			logger.info("====end release nettyServer resource====");
		}
	}

	@Override
	public void close() {
		logger.info("====nettyServer closed=====");
		// TODO close the server
	}
}
