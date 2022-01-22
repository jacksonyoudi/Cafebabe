package com.youdi.base;

import org.apache.pulsar.client.admin.PulsarAdmin;
import org.apache.pulsar.client.api.PulsarClientException;

import java.util.List;

public class NameSpace {
    public static void main(String[] args) throws Exception {
        String servicesHttpUrl = "http://node1:8080,node2:8080";
        PulsarAdmin admin = PulsarAdmin.builder().serviceHttpUrl(servicesHttpUrl).build();


        //

        admin.namespaces().createNamespace("tenants/ns");

        List<String> namespaces = admin.namespaces().getNamespaces("tenants");
        for (String namespace : namespaces) {
            admin.namespaces().deleteNamespace(namespace);
        }

        admin.close();

    }
}
