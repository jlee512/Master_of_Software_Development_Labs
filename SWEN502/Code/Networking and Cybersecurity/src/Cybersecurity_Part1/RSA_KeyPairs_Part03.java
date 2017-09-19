package Cybersecurity_Part1;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Julian on 19/09/2017.
 */
public class RSA_KeyPairs_Part03 {

    public static void main(String[] args) {

        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(1024);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            FileOutputStream publickey_outputStream = new FileOutputStream("public_key");
            FileOutputStream privatekey_outputStream = new FileOutputStream("private_key");

            publickey_outputStream.write(keyPair.getPublic().getEncoded());
            privatekey_outputStream.write(keyPair.getPrivate().getEncoded());

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
