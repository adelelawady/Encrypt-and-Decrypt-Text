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
public abstract class Algorithm {

    public abstract String Name();
    public abstract String Decrypt(String MsgToDecrypt);
    public abstract String Encrypt(String MsgToEncrypt);
}
