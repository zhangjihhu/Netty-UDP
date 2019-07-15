package com.hhu.client.handler;

import com.hhu.protocol.response.LogEvent;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LogEventHandler extends SimpleChannelInboundHandler<LogEvent> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogEvent logEvent) throws Exception {
        StringBuilder builder = new StringBuilder();
        builder.append(logEvent.getReceived());
        builder.append("[");
        builder.append(logEvent.getSender());
        builder.append("][");
        builder.append(logEvent.getLogfile());
        builder.append("] : ");
        builder.append(logEvent.getMsg());
        System.err.println(builder.toString());
    }
}
