package com.ivan.newtechnologies.token;

import com.google.common.collect.ImmutableList;
import org.apache.commons.io.IOUtils;
import org.springframework.security.crypto.encrypt.CustomEncryptors;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AesCipher {

    // 39
    //47
    //49
    //50
    //51
    //52
    //56
    //58
    //59
    //61
    //63
    //114
    //115
    //116
    //117
    //118

    private static final List<String> challengeIds = ImmutableList.of("39", "47", "49", "50", "51", "52", "56", "58", "59", "61", "63", "114", "115", "116", "117", "118");

    // tb9we3U0vSSLbUn - ashoka password
    // 8bdd33f4-8aea-43e9-99e6-8c5f620313a4 - default password
    private static final String PASSWORD = "tb9we3U0vSSLbUn";

    public static void main(String[] args) throws Exception {
//        encryptFile();
        // ~ 500 decryptions per second
        System.out.println(decrypt("aa8ef10e8eaaf354159a50858e8cbc3aa6353c3955f5666a40dabdeecf224786422886986c8b2d816cc4c0641f6d72af5bb791bc6ec575308ffca818aa864fdfc02d43eac2e9dc8722901580ee7ad88645fb5802a835c3ba"));
    }

    public static void decryptFile(String[] args) throws Exception {
        final List<String> lines = IOUtils.readLines(new FileInputStream(new File("c:\\Users\\Ivan_Stankov\\Downloads\\usernames_encrypted.csv")));

        try (final OutputStream fileOutputStream = new BufferedOutputStream(new FileOutputStream(new File("c:\\Users\\Ivan_Stankov\\Downloads\\usernames_decrypted.csv")))) {

            IOUtils.write("username, decrypted\n", fileOutputStream);

            AtomicInteger counter = new AtomicInteger(1);

            lines.stream()
                    .skip(1)
                    .parallel()
                    .forEach(line -> {

                        final String[] arr = line.split(", ");

                        final String decrypted = decrypt(arr[1].trim());
                        final String lineToWrite = arr[0] + ", " + decrypted + "\n";
                        try {
                            IOUtils.write(lineToWrite, fileOutputStream);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        counter.incrementAndGet();
                        if (counter.get() % 100 == 0) {
                            System.out.print("\rProcessed - " + counter.get() + " of " + lines.size());
                        }
                    });
        }
    }

    public static void encryptFile() throws IOException {

        final List<String> userNames = IOUtils.readLines(new FileInputStream(new File("c:\\Users\\Ivan_Stankov\\Downloads\\usernames.csv")));

        try (final OutputStream fileOutputStream = new BufferedOutputStream(new FileOutputStream(new File("c:\\Users\\Ivan_Stankov\\Downloads\\usernames_encrypted.csv")))) {

            IOUtils.write("username-challengeId, encrypted\n", fileOutputStream);

            AtomicInteger counter = new AtomicInteger(1);

            userNames.stream()
                    .skip(1)
                    .parallel()
                    .forEach(username -> {

                        challengeIds.forEach(challengeId -> {
                            final String encrypted = encrypt2(username, "challenge", "custom_message", Long.valueOf(challengeId));
                            final String lineToWrite = username + "-" + challengeId + ", " + encrypted + "\n";

                            try {
                                IOUtils.write(lineToWrite, fileOutputStream);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });

                        counter.incrementAndGet();
                        if (counter.get() % 100 == 0) {
                            System.out.print("\rProcessed - " + counter.get() + " of " + userNames.size());
                        }
                    });
        }
    }


    private static String decrypt(final String token) {
        final String salt = token.substring(0, 16);
        final String encryptedText = token.substring(16);

        final TextEncryptor decryptor = Encryptors.text(PASSWORD, salt);

        return decryptor.decrypt(encryptedText);
    }

    private static String encrypt(final String username, final String unsubscribeType, final String emailType,
            final Long entityId) {

        final String salt = KeyGenerators.string().generateKey();

        final String textToEncrypt = String.format("%s<|>%s<|>%s<|>%s", username, unsubscribeType, emailType, entityId);

        final TextEncryptor encryptor = Encryptors.text(PASSWORD, salt);
        final String encryptedText = encryptor.encrypt(textToEncrypt);

        return salt + encryptedText;
    }

    private static String encrypt2(final String username, final String unsubscribeType, final String emailType,
            final Long entityId) {

        final String salt = "aa8ef10e8eaaf354";

        final String textToEncrypt = String.format("%s<|>%s<|>%s<|>%s", username, unsubscribeType, emailType, entityId);

        final TextEncryptor encryptor = CustomEncryptors.create(PASSWORD, salt, "159a50858e8cbc3aa6353c3955f5666a");
        final String encryptedText = encryptor.encrypt(textToEncrypt);

        return salt + encryptedText;
    }
}
