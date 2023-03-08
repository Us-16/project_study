package com.example.app.util;

public class Hex {
    public String stringToHex(String s){
        StringBuilder result = new StringBuilder();
        for(int i=0; i<s.length(); i++)
            result.append(String.format("%02X", (int) s.charAt(i)));
        return result.toString();
    }

    public String hexToString(String s){
        final String ascii = "0123456789ABCDEF";
        StringBuilder result = new StringBuilder();
        for(int i=0; i<s.length(); i+=2){
            int num = ascii.indexOf(s.charAt(i))*16 + ascii.indexOf(s.charAt(i+1));
            result.append((char) num);
        }
        return result.toString();
    }
}
