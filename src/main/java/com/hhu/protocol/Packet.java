package com.hhu.protocol;


import java.net.InetSocketAddress;

public abstract class Packet {

	private InetSocketAddress sender;

	public void setSender(InetSocketAddress address) {
		this.sender = address;
	}

	public InetSocketAddress getSender() {
		return sender;
	}

	public abstract Byte getCommand();

}
