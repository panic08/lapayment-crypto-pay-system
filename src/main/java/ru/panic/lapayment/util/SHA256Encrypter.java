package ru.panic.lapayment.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class SHA256Encrypter {
    public static String encrypt(String plaintext) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (Exception e) {

        }
        byte[] hashBytes = digest.digest(plaintext.getBytes(StandardCharsets.UTF_8));
        StringBuilder hexString = new StringBuilder();
        for (byte hashByte : hashBytes) {
            hexString.append(String.format("%02x", hashByte));
        }
        return hexString.toString();
    }
}
