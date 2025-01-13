package com.ndhuy.authen.users.infrastructure;

import org.springframework.stereotype.Service;

import com.ndhuy.proto_library.user.UserProto.AuthRequest;
import com.ndhuy.proto_library.user.UserProto.AuthResponse;
import com.ndhuy.proto_library.user.UserServiceGrpc;

import net.devh.boot.grpc.client.inject.GrpcClient;



@Service
public class UserGrpc {
    
    @GrpcClient("user-service")
    private UserServiceGrpc.UserServiceBlockingStub userServiceStub;
    
    public void authenticate(String username, String password) {
        AuthRequest request = AuthRequest.newBuilder()
                .setUsername(username)
                .setPassword(password)
                .build();

        AuthResponse response = userServiceStub.authenticate(request);

        System.out.println("Response: " + response);
    }
}
