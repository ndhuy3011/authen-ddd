package com.ndhuy.authen.users.infrastructure.communicate;

import org.springframework.stereotype.Service;

import com.ndhuy.proto_library.user.UserServiceGrpc;

import net.devh.boot.grpc.client.inject.GrpcClient;



@Service
public class UserCommunicateGrpc {
    
    @GrpcClient("user-service")
    private UserServiceGrpc.UserServiceBlockingStub userServiceStub;


}
