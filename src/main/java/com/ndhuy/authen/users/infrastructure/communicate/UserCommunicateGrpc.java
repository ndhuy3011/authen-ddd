package com.ndhuy.authen.users.infrastructure.communicate;

import org.springframework.stereotype.Service;

import com.ndhuy.proto_library.user.UserProto.AuthRequest;
import com.ndhuy.proto_library.user.UserProto.AuthResponse;
import com.ndhuy.proto_library.user.UserServiceGrpc;

import net.devh.boot.grpc.client.inject.GrpcClient;



@Service
public class UserCommunicateGrpc {
    
    @GrpcClient("user-service")
    private UserServiceGrpc.UserServiceBlockingStub userServiceStub;

    public AuthResponse authenticate(String username, String password) {
        var request = AuthRequest.newBuilder()
                .setUsername(username)
                .setPassword(password)
                .build();
        return userServiceStub.authenticate(request);
    }

}
