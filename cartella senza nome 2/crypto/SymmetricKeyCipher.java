package crypto;


import javax.crypto.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class SymmetricKeyCipher implements CryptoInterface {

    /*
    * Advanced Encryption Standard - AES
    * # source: https://en.wikipedia.org/wiki/Advanced_Encryption_Standard
    */

    private final SecretKey secretKey;
    private final String Path = "/home/francesco/Scrivania/josef/Crypto/src/";

    public SymmetricKeyCipher() {
        KeyGenerator keyGenerator;
        // if (this.loadSecret()) return;

        try {
            keyGenerator = KeyGenerator.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        keyGenerator.init(256); // You can choose different key sizes (128, 192, or 256 bits)
        
        if (loadSecret()){
            SecretKey sk = null;
            try{
                sk = getKey();
            } catch (Exception e){
                System.out.println("failed to load the key \n\t" + e.toString());
                System.out.println("generating a new key, please do NOT decrypt files.");

                sk = keyGenerator.generateKey();
            } finally {
                this.secretKey = sk;
            }
        } else {
            this.secretKey = keyGenerator.generateKey();
        }
    }

    private void saveKey() throws IOException {
        FileOutputStream fos = new FileOutputStream(Path + "key");
        ObjectOutputStream oout = new ObjectOutputStream(fos);
        try {
            oout.writeObject(this.secretKey);
        } finally {
            oout.close();
        } 
    }

    private SecretKey getKey() throws IOException, ClassNotFoundException{
        
        FileInputStream fis = new FileInputStream(Path + "key");
        ObjectInputStream ois = new ObjectInputStream(fis);

        SecretKey newSecretKey = (SecretKey) ois.readObject();

        ois.close();

        return newSecretKey;
    }

    public boolean loadSecret() {
        // TODO: Read key from a file with the hash of the message

        File file = new File(Path + "key");
        if (file.exists()) return true;
        return false;
    }

    /**
     * Encrypt data with AES-256 bit
     *
     * @param data provide an unencrypted string
     * @return return an encrypted string
     */
    @Override
    public String crypt(String data) {
        System.out.println(data);
        Cipher cipher;
        try {
            cipher = Cipher.getInstance("AES");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new RuntimeException(e);
        }
        try {
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }

        byte[] encryptedBytes;
        try {
            encryptedBytes = cipher.doFinal(data.getBytes());
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException(e);
        }

        /*      adding missing parts 
         * if we get here we have cripted the file, so the key must be saved
         * bec it is needed in order to decrypt the file.
         * following there is the code to save the key into a file.
        */
        try{
            saveKey();
        } catch (Exception e){
            System.out.println(e.toString());
        }

        return Base64.getEncoder().encodeToString(encryptedBytes);  
    }

    /**
     * Decrypt data with AES-256 bit
     *
     * @param data provide an encrypted string
     * @return return an unencrypted string
     */
    @Override
    public String decrypt(String data) {
        Cipher cipher;
        try {
            cipher = Cipher.getInstance("AES");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new RuntimeException(e);
        }

        try {
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }

        byte[] decodedBytes = Base64.getDecoder().decode(data);
        byte[] decryptedBytes;
        try {
            decryptedBytes = cipher.doFinal(decodedBytes);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException(e);
        }
        
        return new String(decryptedBytes);
    }
}
