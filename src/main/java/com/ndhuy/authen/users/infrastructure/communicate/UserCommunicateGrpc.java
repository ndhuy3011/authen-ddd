package com.ndhuy.authen.users.infrastructure.communicate;

import com.ndhuy.authen.authentication.application.domain.CreateUserCommand;
import com.ndhuy.proto_library.user.UserProto;
import org.springframework.stereotype.Service;

import com.ndhuy.proto_library.user.UserProto.AuthRequest;
import com.ndhuy.proto_library.user.UserProto.AuthResponse;
import com.ndhuy.proto_library.user.UserServiceGrpc;

import net.devh.boot.grpc.client.inject.GrpcClient;



@Service
public class UserCommunicateGrpc {
    
    @GrpcClient("user-service")
    private UserServiceGrpc.UserServiceBlockingStub userServiceStub;

    public UserProto.AuthResponse authenticate(String username, String password) {
        var request = UserProto.AuthRequest.newBuilder()
                .setUsername(username)
                .setPassword(password)
                .build();
        return userServiceStub.authenticate(request);
    }

    public UserProto.RegisterReponse register(CreateUserCommand user){
        var request = UserProto.RegisterRequest.newBuilder()
                .setEmail(user.email())
                .setPassword(user.password())
                .setUsername(user.username())
                .setPhone(user.phone())
                .setFullName(user.fullName())
                .build();
        return  userServiceStub.register(request);
    }
}
