package com.hhu.client;

import com.hhu.client.handler.LogEventHandler;
import com.hhu.codec.PacketCodecHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.net.InetSocketAddress;

public class LogEventMonitor {

    private final EventLoopGroup group;
    private final Bootstrap bootstrap;

    private LogEventMonitor(final InetSocketAddress address) {
        group = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioDatagramChannel.class)
                .option(ChannelOption.SO_BROADCAST, true)
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        ChannelPipeline pipeline = channel.pipeline();
                        pipeline.addLast(new PacketCodecHandler(address));
                        pipeline.addLast(new LogEventHandler());
                    }
                }).localAddress(address);
    }

    private Channel bind() {
        return bootstrap.bind().syncUninterruptibly().channel();
    }

    private void stop() {
        group.shutdownGracefully();
    }

    public static void main(String[] args) {
        LogEventMonitor monitor = new LogEventMonitor(new InetSocketAddress(9999));
        try {
            Channel channel = monitor.bind();
            System.out.println("Monitor running");
            channel.closeFuture().await();
        } catch (InterruptedException e) {
            monitor.stop();
        }
    }



}
