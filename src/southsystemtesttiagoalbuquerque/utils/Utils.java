/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package southsystemtesttiagoalbuquerque.utils;

/**
 *
 * @author Tiago
 */
public class Utils {
    public String putSpace(String param, int qtd) {
        while (param.length() < qtd) {
            param += " ";
        }
        return param;
    }
    
    public String completeWith(String param, int qtd) {
        String returnParam = "";
        while (returnParam.length() < qtd) {
            returnParam += param;
        }
        return returnParam;
    }
}


