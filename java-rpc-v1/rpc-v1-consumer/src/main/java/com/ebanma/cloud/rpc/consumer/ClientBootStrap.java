package com.ebanma.cloud.rpc.consumer;

import com.ebanma.cloud.rpc.api.UserApi;
import com.ebanma.cloud.rpc.consumer.proxy.RpcClientProxy;
import com.ebanma.cloud.rpc.api.dto.UserInfoDTO;

/**
 * 测试类
 */
public class ClientBootStrap {
    public static void main(String[] args) {
        UserApi userService = (UserApi) RpcClientProxy.createProxy(UserApi.class);
        UserInfoDTO user = userService.getById(1);
        System.out.println(user);
    }
}