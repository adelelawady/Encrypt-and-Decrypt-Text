/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author adelali
 */
public class ADFGVXAlgorithm extends Algorithm {

    public ADFGVXAlgorithm() {

    }

    private static char[][] getKeySquare() {

        return new char[][]{
            new char[]{'q', 'b', 'd', 'y', 't'},
            new char[]{'m', 'c', 'u', 'n', 'i'},
            new char[]{'l', 'h', 'v', 'a', 'x'},
            new char[]{'k', 'f', 'e', 'o', 'w'},
            new char[]{'r', 'p', 'g', 's', 'z'},};
    }

    private static char[][] transposeMatrix(char[][] matrix) {

        char[][] result = new char[matrix[0].length][];

        for (int i = 0; i < result.length; i++) {

            result[i] = new char[matrix.length];
        }

        for (int row = 0; row < matrix.length; row++) {

            for (int col = 0; col < matrix[row].length; col++) {

                result[col][row] = matrix[row][col];
            }
        }

        return result;
    }

    private static String[] splitByRange(String text, int range) {

        String[] result = new String[text.length() / range];

        for (int i = 0; i < result.length; i++) {
            result[i] = text.substring(i * range, i * range + range);
        }

        return result;
    }

    private static String Cipher(
            String clearText, char[][] keySquare, String keyWord) {

        String result = "";

        //Fase 1
        String adfgx = "ADFGX";
        String resultTemp = "";

        //key square matrix encode
        for (char c : clearText.toCharArray()) {

            for (int row = 0; row < keySquare.length; row++) {

                for (int col = 0; col < keySquare[row].length; col++) {

                    if (keySquare[row][col] == c) {

                        resultTemp += adfgx.charAt(row);
                        resultTemp += adfgx.charAt(col);
                    }
                }
            }
        }

        //Fase 2
        //key word transposition
        String[] charMatrix = new String[keyWord.length()];
        for (int i = 0; i < charMatrix.length; i++) {

            charMatrix[i] = String.valueOf(keyWord.charAt(i));
        }

        int idx = 0;
        for (char c : resultTemp.toCharArray()) {

            charMatrix[idx] += String.valueOf(c);

            idx = idx == charMatrix.length - 1 ? 0 : idx + 1;
        }

        Arrays.sort(charMatrix);

        for (String s : charMatrix) {

            result += s.substring(1);
        }

        return result;
    }
    String clearText = "secret";

    @Override
    public String Name() {
        return "ADFGVX";
    }

    @Override
    public String Decrypt(String MsgToDecrypt) {
        char[][] keySquare = getKeySquare();
        return Decipher(MsgToDecrypt, keySquare, clearText);
    }

    @Override
    public String Encrypt(String MsgToEncrypt) {
        char[][] keySquare = getKeySquare();
        return Cipher(MsgToEncrypt, keySquare, clearText);
    }

    private static class KeyCounter {

        public int Count = 0;
    }

    private static String Decipher(
            String cipherText, char[][] keySquare, String keyWord) {

        String result = "";

        //Fase 1 (undo Fase 2 of cipher method)
        //key word transposition
        HashMap<Character, KeyCounter> sizesDict
                = new HashMap<>();

        for (char c : keyWord.toCharArray()) {

            sizesDict.put(c, new KeyCounter());
        }

        int idx = 0;
        for (char a : cipherText.toCharArray()) {

            sizesDict.get(keyWord.charAt(idx)).Count++;
            idx = idx == keyWord.length() - 1 ? 0 : idx + 1;
        }

        List<String> strList = new LinkedList<String>();
        for (char c : keyWord.toCharArray()) {

            strList.add(String.valueOf(c));
        }

        Collections.sort(strList);

        int sIdx = 0;
        for (int i = 0; i < strList.size(); i++) {

            char c = strList.get(0).charAt(0);

            String strListElem = strList.remove(0);
            strListElem
                    += cipherText
                            .substring(sIdx, sIdx + sizesDict.get(c).Count);

            strList.add(strListElem);

            sIdx += sizesDict.get(c).Count;
        }

        // order strList to be like keyWord order
        List<String> strListAux = new LinkedList<String>();
        for (char c : keyWord.toCharArray()) {

            for (int i = 0; i < strList.size(); i++) {

                if (strList.get(i).startsWith(String.valueOf(c))) {

                    strListAux.add(strList.get(i).substring(1));
                    strList.remove(i);
                    break;
                }
            }
        }

        int rows = strListAux.size();
        char[][] charMatrix = new char[rows][];

        for (int i = 0; i < charMatrix.length; i++) {

            charMatrix[i] = strListAux.get(i).toCharArray();
        }

        charMatrix = transposeMatrix(charMatrix);

        String resultTemp = "";
        for (char[] arr : charMatrix) {

            for (char c : arr) {

                if (c != '\0') {

                    resultTemp += c;
                }
            }
        }

        //Fase 2 (undo Fase 1 of cipher method)
        //key square matrix decode
        String adfgx = "ADFGX";

        String[] resultTempSplited = splitByRange(resultTemp, 2);
        for (String s : resultTempSplited) {

            int row = adfgx.indexOf(s.charAt(0));
            int col = adfgx.indexOf(s.charAt(1));

            result += keySquare[row][col];
        }

        return result;
    }

}
