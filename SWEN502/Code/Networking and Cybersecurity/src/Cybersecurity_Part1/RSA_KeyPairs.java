package Cybersecurity_Part1;

import java.security.*;

/**
 * Created by Julian on 19/09/2017.
 */
public class RSA_KeyPairs {

    public static void main(String[] args) {

        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(1024);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            System.out.println("Public key: " + keyPair.getPublic());
            System.out.println("Private key: " + keyPair.getPrivate());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }

}
