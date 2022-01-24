package com.youdi.producer;

import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Schema;

// 同步发布
public class SyncDemo {
    public static void main(String[] args) throws PulsarClientException {
        // 1. 创建pulsar的客户端对象
        PulsarClient client = PulsarClient.builder().serviceUrl("pulsar://127.0.0.1:6650").build();

        // 创建生成者对象
        Producer<String> producer = client.newProducer(
                Schema.STRING
        ).topic("persistent://youdi/jackson/mytopic").create();

        // 数据发送
        producer.send("hello java api");

        producer.close();

    }
}
