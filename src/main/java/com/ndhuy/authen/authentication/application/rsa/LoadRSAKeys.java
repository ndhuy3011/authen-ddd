package com.ndhuy.authen.authentication.application.rsa;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;

import com.nimbusds.jose.jwk.RSAKey;
import lombok.Getter;

public class LoadRSAKeys {

    @Getter
    private  static KeyPair keyPair;

    static {
        try {
            var keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private LoadRSAKeys() {
        
    }

    public static RSAKey getRsaKey(){
        return new com.nimbusds.jose.jwk.RSAKey.Builder(getRSAPublicKey())
        .privateKey(getRSAPrivateKey()) 
        .build();
    }
    public static PrivateKey getPrivateKey() {
        return keyPair.getPrivate();
    }

    public static PublicKey getPublicKey() {
        return keyPair.getPublic();
    }

    public static RSAPublicKey getRSAPublicKey() {
        return (RSAPublicKey) keyPair.getPublic();
    }
    public static RSAPrivateCrtKey getRSAPrivateKey() {
        return (RSAPrivateCrtKey) keyPair.getPrivate();
    }

}
