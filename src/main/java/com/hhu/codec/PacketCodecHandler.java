package com.hhu.codec;

import com.hhu.protocol.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageCodec;

import java.net.InetSocketAddress;
import java.util.List;

public class PacketCodecHandler extends MessageToMessageCodec<DatagramPacket, Packet> {

	/**
	 * remote address
	 */
	private final InetSocketAddress address;

	public PacketCodecHandler(InetSocketAddress address) {
		this.address = address;
	}

	@Override
	protected void encode(ChannelHandlerContext ctx, Packet packet, List<Object> list) throws Exception {
		ByteBuf byteBuf = ctx.alloc().ioBuffer();
		PacketCodec.INSTANCE.encode(byteBuf, packet);
		list.add(new DatagramPacket(byteBuf, address));
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, DatagramPacket datagramPacket, List<Object> list) throws Exception {
		Packet packet = PacketCodec.INSTANCE.decode(datagramPacket.content());
		packet.setSender(datagramPacket.sender());
		list.add(packet);
	}
}
