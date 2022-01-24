package com.youdi.base;

import org.apache.pulsar.client.admin.PulsarAdmin;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.common.policies.data.TenantInfo;

import java.util.HashSet;
import java.util.List;

public class CreateTenants {
    public static void main(String[] args) throws Exception {
        // 创建pulsar的admin管理对象
        String servicesHttpUrl = "http://node1:8080,node2:8080";
        PulsarAdmin admin = PulsarAdmin.builder().serviceHttpUrl(servicesHttpUrl).build();

        // 通过 admin对象进行相关操作


        HashSet<String> allowedClusters = new HashSet<String>();
        allowedClusters.add("node1");

        // 租户操作
        TenantInfo tenant = TenantInfo.builder().allowedClusters(allowedClusters).build();
        admin.tenants().createTenant("two", tenant);


        // 查看哪些租户
        List<String> tenants = admin.tenants().getTenants();
        for (String s : tenants) {
            TenantInfo tenantInfo = admin.tenants().getTenantInfo(s);
            System.out.println(tenantInfo.toString());
        }

        // 删除租户
        admin.tenants().deleteTenant("hello");


        //  关闭admin
        admin.close();


    }
}
