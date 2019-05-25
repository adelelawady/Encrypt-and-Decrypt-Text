/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import java.io.InputStream;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.Cipher;

/**
 *
 * @author adelali
 */
public class RSAAlgorithm extends Algorithm {

    KeyPair pair;

    public RSAAlgorithm() throws Exception {
        pair = generateKeyPair();
    }

    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048, new SecureRandom());
        KeyPair pair = generator.generateKeyPair();

        return pair;
    }

    public static String encrypt(String plainText, PublicKey publicKey) throws Exception {
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);

        byte[] cipherText = encryptCipher.doFinal(plainText.getBytes(UTF_8));

        return Base64.getEncoder().encodeToString(cipherText);
    }

    public static String decrypt(String cipherText, PrivateKey privateKey) throws Exception {
        byte[] bytes = Base64.getDecoder().decode(cipherText);

        Cipher decriptCipher = Cipher.getInstance("RSA");
        decriptCipher.init(Cipher.DECRYPT_MODE, privateKey);

        return new String(decriptCipher.doFinal(bytes), UTF_8);
    }

    public static String sign(String plainText, PrivateKey privateKey) throws Exception {
        Signature privateSignature = Signature.getInstance("SHA256withRSA");
        privateSignature.initSign(privateKey);
        privateSignature.update(plainText.getBytes(UTF_8));

        byte[] signature = privateSignature.sign();

        return Base64.getEncoder().encodeToString(signature);
    }

    public static boolean verify(String plainText, String signature, PublicKey publicKey) throws Exception {
        Signature publicSignature = Signature.getInstance("SHA256withRSA");
        publicSignature.initVerify(publicKey);
        publicSignature.update(plainText.getBytes(UTF_8));

        byte[] signatureBytes = Base64.getDecoder().decode(signature);

        return publicSignature.verify(signatureBytes);
    }

    @Override
    public String Name() {
        return "RSA";
    }

    @Override
    public String Decrypt(String MsgToDecrypt) {
        try {
            String decipheredMessage = decrypt(MsgToDecrypt, pair.getPrivate());
            String signature = sign(decipheredMessage, pair.getPrivate());
            boolean isCorrect = verify(MsgToDecrypt, signature, pair.getPublic());
            //  if (isCorrect) {
            return decipheredMessage;
            // }
        } catch (Exception ex) {
            Logger.getLogger(RSAAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    @Override
    public String Encrypt(String MsgToEncrypt) {

        try {
            return encrypt(MsgToEncrypt, pair.getPublic());
        } catch (Exception ex) {
            Logger.getLogger(RSAAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

}
