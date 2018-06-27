package com.ivan.newtechnologies.springpassword;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutionException;

public class Main {
    private static final Charset CHARSET = Charset.forName("UTF-8");
    private static final char[] HEX = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f'};

    public static void main(String[] args) throws ExecutionException, InterruptedException, CharacterCodingException, NoSuchAlgorithmException {
        final String hashedPassword = "3b24b705f5c930a015a29cd138707e71540fde7ce008b19a295536399e6f696b0a35d9f714613b2c";
        byte[] digested = hexDecode(hashedPassword);

        byte[] salt = subArray(digested, 0, 8);
        String encodedSalt = new String(hexEncode(salt));// 3b24b705f5c930a0

        byte[] hash = digest(concatenate(salt, utf8Encode("Secret123")));

        System.out.println("Hashed password (Secret123): " + hashedPassword);
        System.out.println("Salt, from previously hashed value: " + encodedSalt);
        System.out.println("Hashed Secret123 with salt: " + new String(hexEncode(hash)));
    }

    public static byte[] hexDecode(String s) {
        int nChars = s.length();

        if (nChars % 2 != 0) {
            throw new IllegalArgumentException(
                    "Hex-encoded string must have an even number of characters");
        }

        byte[] result = new byte[nChars / 2];

        for (int i = 0; i < nChars; i += 2) {
            int msb = Character.digit(s.charAt(i), 16);
            int lsb = Character.digit(s.charAt(i + 1), 16);

            if (msb < 0 || lsb < 0) {
                throw new IllegalArgumentException("Non-hex character in input: " + s);
            }
            result[i / 2] = (byte) ((msb << 4) | lsb);
        }
        return result;
    }

    public static byte[] subArray(byte[] array, int beginIndex, int endIndex) {
        int length = endIndex - beginIndex;
        byte[] subarray = new byte[length];
        System.arraycopy(array, beginIndex, subarray, 0, length);
        return subarray;
    }

    public static char[] hexEncode(byte[] bytes) {
        final int nBytes = bytes.length;
        char[] result = new char[2 * nBytes];

        int j = 0;
        for (int i = 0; i < nBytes; i++) {
            // Char for top 4 bits
            result[j++] = HEX[(0xF0 & bytes[i]) >>> 4];
            // Bottom 4
            result[j++] = HEX[(0x0F & bytes[i])];
        }

        return result;
    }

    public static byte[] digest(byte[] value) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        for (int i = 0; i < 1024; i++) {
            value = messageDigest.digest(value);
        }
        return value;
    }

    public static byte[] concatenate(byte[]... arrays) {
        int length = 0;
        for (byte[] array : arrays) {
            length += array.length;
        }
        byte[] newArray = new byte[length];
        int destPos = 0;
        for (byte[] array : arrays) {
            System.arraycopy(array, 0, newArray, destPos, array.length);
            destPos += array.length;
        }
        return newArray;
    }

    public static byte[] utf8Encode(CharSequence string) {
        try {
            ByteBuffer bytes = CHARSET.newEncoder().encode(CharBuffer.wrap(string));
            byte[] bytesCopy = new byte[bytes.limit()];
            System.arraycopy(bytes.array(), 0, bytesCopy, 0, bytes.limit());

            return bytesCopy;
        }
        catch (CharacterCodingException e) {
            throw new IllegalArgumentException("Encoding failed", e);
        }
    }

}
