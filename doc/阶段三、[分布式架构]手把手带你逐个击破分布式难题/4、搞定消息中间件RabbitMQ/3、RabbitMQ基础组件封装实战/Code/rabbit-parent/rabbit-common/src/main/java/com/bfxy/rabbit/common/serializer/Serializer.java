package com.bfxy.rabbit.common.serializer;

/**
 * 序列化和反序列化的接口
 *
 * @author Alienware
 */
public interface Serializer {
    // 序列化成数组
    byte[] serializeRaw(Object data);

    // 序列化成字符串
    String serialize(Object data);

    // 字符串反序列化
    <T> T deserialize(String content);

    // 数组反序列化
    <T> T deserialize(byte[] content);
}
