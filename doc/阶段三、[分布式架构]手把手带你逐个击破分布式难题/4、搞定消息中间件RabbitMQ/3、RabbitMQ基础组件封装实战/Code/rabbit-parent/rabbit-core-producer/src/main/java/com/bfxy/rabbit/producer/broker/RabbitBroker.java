package com.bfxy.rabbit.producer.broker;

import com.bfxy.rabbit.api.Message;

/**
 * $RabbitBroker 具体发送不同种类型消息的接口
 *
 * @author Alienware
 */
public interface RabbitBroker {
    // 迅速消息
    void rapidSend(Message message);
    // 确认消息
    void confirmSend(Message message);
    // 可靠性消息
    void reliantSend(Message message);

    void sendMessages();
}
