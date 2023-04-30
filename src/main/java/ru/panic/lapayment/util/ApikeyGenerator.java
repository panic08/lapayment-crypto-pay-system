package ru.panic.lapayment.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Random;

public class ApikeyGenerator {
    private static final Random RANDOM = new Random();
    public static String generate(){
        StringBuilder sb = new StringBuilder();
        int length = RANDOM.nextInt(50) + 5;
        for (int i = 0; i < length; i++) {
            char c = (char)(RANDOM.nextInt(26) + 'a');
            sb.append(c);
        }

        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (Exception e) {
        }
        byte[] hashBytes = digest.digest(sb.toString().getBytes(StandardCharsets.UTF_8));
        StringBuilder hexString = new StringBuilder();
        for (byte hashByte : hashBytes) {
            hexString.append(String.format("%02x", hashByte));
        }
        return hexString.toString();

    }
}
