package com.youdi.base;

import org.apache.pulsar.client.admin.PulsarAdmin;
import org.apache.pulsar.client.api.PulsarClientException;

import java.util.List;

public class Topic {
    public static void main(String[] args) throws Exception {
        // 创建pulsar的admin管理对象
        String servicesHttpUrl = "http://node1:8080,node2:8080";
        PulsarAdmin admin = PulsarAdmin.builder().serviceHttpUrl(servicesHttpUrl).build();

        admin.topics().createPartitionedTopic("tenants/ns/hello", 10);

        admin.topics().createNonPartitionedTopic("tenants/ns/topicone");

        List<String> list = admin.topics().getList("tenants/ns/hello");


        admin.close();


    }
}
