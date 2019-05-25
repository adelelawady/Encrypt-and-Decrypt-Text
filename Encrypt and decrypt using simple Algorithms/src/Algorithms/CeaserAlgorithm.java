/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

/**
 *
 * @author adelali
 */
public class CeaserAlgorithm extends Algorithm{
    public int key =3;
    
     //ceaser encryption
    public static String CeaserEncrypt(String input, int key) {
        int index = 0;
        String EncryptedString = "";
        char[] ArrayChar = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        char[] inputArr = input.toCharArray();
        for (int i = 0; i < inputArr.length; i++) {
            for (int j = 0; j < ArrayChar.length; j++) {
                if (inputArr[i] == ArrayChar[j]) {
                    index = ((j + key) % 26);
                    EncryptedString += ArrayChar[index];

                }

            }

        }
        return EncryptedString;
    }
    
     public static String CeaserDecrypt(String input, int key) {
        int index = 0;
        String EncryptedString = "";
        char[] ArrayChar = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'b', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        char[] inputArr = input.toCharArray();
        for (int i = 0; i < inputArr.length; i++) {
            for (int j = 0; j < ArrayChar.length; j++) {
                if (inputArr[i] == ArrayChar[j]) {
                    index = ((j - key) % 26);
                    EncryptedString += ArrayChar[index];

                }

            }

        }

        return EncryptedString;

    }

    @Override
    public String Name() {
        return "Ceaser";
    }

    @Override
    public String Decrypt(String MsgToDecrypt) {
        return CeaserDecrypt(MsgToDecrypt , key);
    }

    @Override
    public String Encrypt(String MsgToEncrypt) {
        return CeaserEncrypt(MsgToEncrypt, key);
    }

}
