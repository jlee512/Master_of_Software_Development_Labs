package Cybersecurity_Part1;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Created by Julian on 19/09/2017.
 */
public class RSA_KeyPairs_Saved {

    public static void main(String[] args) {
        try {
            File publickey_file = new File("public_key");
            File privatekey_file = new File("private_key");

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] public_key_bytes = Files.readAllBytes(publickey_file.toPath());

            byte[] private_key_bytes = Files.readAllBytes(privatekey_file.toPath());

            PublicKey public_key = keyFactory.generatePublic(new X509EncodedKeySpec(public_key_bytes));
            PrivateKey privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(private_key_bytes));

            Cipher cipher = Cipher.getInstance("RSA");
            //Initialise cipher in encrypt mode
            cipher.init(Cipher.ENCRYPT_MODE, public_key);

            String message = "Hello, encrypted world!";

            System.out.println("Pre-encryption: " + message);

            //Encrypt message
            byte[] encrypted_bytes = cipher.doFinal(message.getBytes());
            String encrypted = new String(encrypted_bytes);

            System.out.println("Encrypted: " + encrypted);

            //Re-initialise cipher in decrypt mode
            cipher.init(Cipher.DECRYPT_MODE, privateKey);

            //Decrypt message
            String decrypted = new String(cipher.doFinal(encrypted_bytes));

            System.out.println("Decrypted: " + decrypted);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
    }

}
