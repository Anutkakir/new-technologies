package com.ivan.newtechnologies.crypto.aestoken;

import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {
        final String password = UUID.randomUUID().toString();
        System.out.println("Password: " + password);

        final String token = encrypt(password, "user1-username", "challenge", "CUSTOM", 185_028L);
        System.out.println("Encrypted text(aestoken): " + token);

        System.out.println();

        final String decryptedToken = decrypt(password, token);
        System.out.println("Decrypted aestoken: " + decryptedToken);

        final List<String> strings = extractTokenParts(decryptedToken);
        System.out.println("Username: " + strings.get(0));
        System.out.println("Unsubscribe type: " + strings.get(1));
        System.out.println("Email type: " + strings.get(2));
        System.out.println("Entity id: " + strings.get(3));
    }

    private static String encrypt(final String password, final String username, final String unsubscribeType, final String emailType,
            final Long entityId) {

        final String salt = KeyGenerators.string().generateKey();
        System.out.println("Salt: " + salt);

        final String textToEncrypt = String.format("%s<|>%s<|>%s<|>%s", username, unsubscribeType, emailType, entityId);

        final TextEncryptor encryptor = Encryptors.text(password, salt);
        final String encryptedText = encryptor.encrypt(textToEncrypt);

        return salt + encryptedText;
    }

    private static String decrypt(final String password, final String token) {
        final String salt = token.substring(0, 16);
        final String encryptedText = token.substring(16);

        final TextEncryptor decryptor = Encryptors.text(password, salt);

        return decryptor.decrypt(encryptedText);
    }

    private static List<String> extractTokenParts(final String token) {
        final String[] parts = token.split("<\\|>");
        return Arrays.asList(parts);
    }

}
