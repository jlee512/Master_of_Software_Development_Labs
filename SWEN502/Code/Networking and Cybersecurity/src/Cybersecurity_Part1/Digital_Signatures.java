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
public class Digital_Signatures {

    public static void main(String[] args) {
        try {
            File publickey_file = new File("public_key");
            File privatekey_file = new File("private_key");
            String digital_signature_message = "I'm a secret message for use in a digital signature";

            System.out.println("Secret message for digital signature: " + digital_signature_message);

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            byte[] public_key_bytes = Files.readAllBytes(publickey_file.toPath());
            byte[] private_key_bytes = Files.readAllBytes(privatekey_file.toPath());
            byte[] message_bytes = digital_signature_message.getBytes();


            PublicKey public_key = keyFactory.generatePublic(new X509EncodedKeySpec(public_key_bytes));
            PrivateKey private_key = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(private_key_bytes));

            //Setup the message digest and hash the secret
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");

            messageDigest.update(message_bytes);
            byte[] secret_digest = messageDigest.digest();
            String secret_digested_string = new String(secret_digest);

            System.out.println("Digested message: " + secret_digested_string);

            //Encrypt the hash for sending to 2nd party
            Cipher cipher = Cipher.getInstance("RSA");

            cipher.init(Cipher.ENCRYPT_MODE, private_key);

            //Encrypt message (encrypted bytes is the encrypted message which would be sent to the second party)
            byte[] encrypted_bytes = cipher.doFinal(secret_digest);
            String encrypted = new String(encrypted_bytes);

            //This is equivalent to the message which would be sent to the second party
            System.out.println("Hashed and Encrypted message: " + encrypted);


            //Second party receives the hashed and encrypted message and also has a local copy of the original message
            System.out.println();
            System.out.println("Second party received encrypted message from first party: " + encrypted);

            //Hash the message and
            MessageDigest messageDigest2 = MessageDigest.getInstance("SHA-1");

            messageDigest2.update(message_bytes);
            byte[] message_digest2 = messageDigest2.digest();
            String message_digested_string2 = new String(message_digest2);
            System.out.println("Digested message by second part: " + message_digested_string2);

            //Re-initialise cipher in decrypt mode
            cipher.init(Cipher.DECRYPT_MODE, public_key);

            //Decrypt message
            byte[] decrypted_bytes = cipher.doFinal(encrypted_bytes);
            String decrypted_string = new String(decrypted_bytes);

            System.out.println("Decrypted using public key: " + decrypted_string);

            System.out.println("Checking equality...");

            if (decrypted_string.equals(message_digested_string2)) {
                System.out.println("Digital signature verified successfully");
            }
            else {
                System.out.println("Digital signature could not be verified");
            }

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
