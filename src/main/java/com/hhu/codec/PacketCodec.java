package com.hhu.codec;

import com.hhu.codec.serialize.Serializer;
import com.hhu.protocol.Packet;
import com.hhu.protocol.response.LogEvent;
import io.netty.buffer.ByteBuf;

import java.util.HashMap;
import java.util.Map;

import static com.hhu.protocol.command.Command.LOG_EVENT;

public class PacketCodec {

	public static final PacketCodec INSTANCE = new PacketCodec();

	private final Map<Byte, Class<? extends Packet>> PACKET_TYPE_MAP;

	private PacketCodec() {
		PACKET_TYPE_MAP = new HashMap<>();
		PACKET_TYPE_MAP.put(LOG_EVENT, LogEvent.class);
	}


	public void encode(ByteBuf byteBuf, Packet packet) {
		//1.序列 java 对象
		byte[] bytes = Serializer.DEFAULT.serialize(packet);
		//2.实际编码过程
		byteBuf.writeByte(packet.getCommand());
		byteBuf.writeInt(bytes.length);
		byteBuf.writeBytes(bytes);
	}

	public Packet decode(ByteBuf byteBuf) {
		// 1.数据包指令
		byte command = byteBuf.readByte();
		// 2.数据包长度
		int length = byteBuf.readInt();

		byte[] bytes = new byte[length];
		byteBuf.readBytes(bytes);

		Class<? extends Packet> requestType = getRequestType(command);

		Serializer serializer = Serializer.DEFAULT;

		if (requestType != null) {
			return serializer.deSerialize(bytes, requestType);
		}

		return null;
	}

	private Class<? extends Packet> getRequestType(byte command) {
		return PACKET_TYPE_MAP.get(command);
	}




}
