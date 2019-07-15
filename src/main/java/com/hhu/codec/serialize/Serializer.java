package com.hhu.codec.serialize;

import com.hhu.codec.serialize.impl.JsonSerializer;

public interface Serializer {

	Serializer DEFAULT = new JsonSerializer();

	/**
	 * java object transfer to binary
	 * @param object java object
	 * @return
	 */
	byte[] serialize(Object object);

	/**
	 * binary transfer to java object
	 * @param clazz java object
	 * @param bytes binary array
	 * @param <T>
	 * @return
	 */
	<T> T deSerialize(byte[] bytes, Class<T> clazz);

}
