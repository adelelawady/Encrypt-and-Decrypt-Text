/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author adelali
 */
public class DESAlgorithm extends Algorithm {

    class DesEncrypter {

        SecretKey key;
        final String secretKeyStr = "qwertyui";
        Cipher ecipher;

        Cipher dcipher;

        DesEncrypter() throws Exception {
            key = new SecretKeySpec(secretKeyStr.getBytes(), "DES");
            ecipher = Cipher.getInstance("DES");
            dcipher = Cipher.getInstance("DES");
            ecipher.init(Cipher.ENCRYPT_MODE, key);
            dcipher.init(Cipher.DECRYPT_MODE, key);
        }

        public String encrypt(String str) throws Exception {
            // Encode the string into bytes using utf-8
            byte[] utf8 = str.getBytes("UTF8");

            // Encrypt
            byte[] enc = ecipher.doFinal(utf8);

            // Encode bytes to base64 to get a string
            return new BASE64Encoder().encode(enc);
        }

        public String decrypt(String str) throws Exception {
            // Decode base64 to get bytes
            byte[] dec = new BASE64Decoder().decodeBuffer(str);

            byte[] utf8 = dcipher.doFinal(dec);

            // Decode using utf-8
            return new String(utf8, "UTF8");
        }
    }

    @Override
    public String Name() {
        return "DES";
    }

    @Override
    public String Decrypt(String MsgToDecrypt) {

        try {
            //key = KeyGenerator.getInstance("DES").generateKey();
            DesEncrypter encrypter = new DesEncrypter();
            return encrypter.decrypt(MsgToDecrypt);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(DESAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DESAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    @Override
    public String Encrypt(String MsgToEncrypt) {

        try {
            //key = KeyGenerator.getInstance("DES").generateKey();
            DesEncrypter encrypter = new DesEncrypter();
            return encrypter.encrypt(MsgToEncrypt);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(DESAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DESAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
}
