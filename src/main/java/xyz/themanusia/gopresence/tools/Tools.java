package xyz.themanusia.gopresence.tools;

import lombok.SneakyThrows;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Tools {

    /**
     * Convert from string to md5 hash
     *
     * @param s input string
     * @return md5 hash value
     */
    @SneakyThrows
    public static String stringToMd5(String s) {

        // Static getInstance method is called with hashing MD5
        MessageDigest md = MessageDigest.getInstance("MD5");

        // digest() method is called to calculate message digest
        //  of an input digest() return array of byte
        byte[] messageDigest = md.digest(s.getBytes());

        // Convert byte array into signum representation
        BigInteger no = new BigInteger(1, messageDigest);

        // Convert message digest into hex value
        StringBuilder hashtext = new StringBuilder(no.toString(16));
        while (hashtext.length() < 32) {
            hashtext.insert(0, "0");
        }
        return hashtext.toString();
    }
}
