package com.example.project;
import java.math.BigInteger;
import java.security.MessageDigest;

public class Encrypter {
    public static String EncrypterData512(String text) {

        byte[] inputData = text.getBytes();
        byte[] outputData = new byte[0];

        try {
            outputData = Encrypter.EncrypterData(inputData, "SHA-512");
        } catch (Exception e) {
            e.printStackTrace();
        }
        BigInteger encryptedData = new BigInteger(1, outputData);
        return  encryptedData.toString(16);
    }


    private static byte[] EncrypterData(byte[] data, String string) throws Exception {

        MessageDigest enc = MessageDigest.getInstance(string);
        enc.update(data);
        return enc.digest();

    }
}