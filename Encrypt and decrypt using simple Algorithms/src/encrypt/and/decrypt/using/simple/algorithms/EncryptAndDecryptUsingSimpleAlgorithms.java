/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encrypt.and.decrypt.using.simple.algorithms;

import Algorithms.ADFGVXAlgorithm;
import Algorithms.AESAlgorithm;
import Algorithms.Algorithm;
import Algorithms.CeaserAlgorithm;
import Algorithms.DESAlgorithm;
import Algorithms.PlayFairAlgorithm;
import Algorithms.RSAAlgorithm;
import java.security.NoSuchAlgorithmException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author adelali
 */
public class EncryptAndDecryptUsingSimpleAlgorithms {

    /**
     * @param args the command line arguments
     */
    public static List<Algorithms.Algorithm> AlgorithmsList = new ArrayList<Algorithm>();

    public static void main(String[] args) throws NoSuchAlgorithmException, Exception {
        // TODO code application logic here
        GUI e = new GUI();

        

        //System.out.println(get("ADFGVX").Name());
       

        e.show();
    }

  

}
