package com.hhu.protocol.response;


import com.hhu.protocol.Packet;

import java.net.InetSocketAddress;

import static com.hhu.protocol.command.Command.LOG_EVENT;

public class LogEvent extends Packet {

    public static byte SEPARATOR = ':';
    /**
     * 发送方地址
     */
    private InetSocketAddress source;

    /**
     * 文件名称
     */
    private String logfile;
    /**
     * 实际消息内容
     */
    private String msg;
    /**
     * 发送数据得时间戳
     */
    private long received;

    public LogEvent(String logfile, String msg) {
        this(null, -1, logfile, msg);
    }

    public LogEvent(InetSocketAddress source, long received, String logfile, String msg) {
        this.source = source;
        this.logfile = logfile;
        this.msg = msg;
        this.received = received;
    }

    public InetSocketAddress getSource() {
        return source;
    }

    public String getLogfile() {
        return logfile;
    }

    public String getMsg() {
        return msg;
    }

    public long getReceived() {
        return received;
    }

    public void setSource(InetSocketAddress source) {
        this.source = source;
    }

    @Override
    public Byte getCommand() {
        return LOG_EVENT;
    }

}
