package com.hhu.codec.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.hhu.codec.serialize.Serializer;

public class JsonSerializer implements Serializer {
	@Override
	public byte[] serialize(Object object) {
		return JSON.toJSONBytes(object);
	}

	@Override
	public <T> T deSerialize(byte[] bytes, Class<T> clazz) {
		return JSON.parseObject(bytes, clazz);
	}
}
