package com.ivan.newtechnologies.string;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SequenceGenerator {

    private static final String[] letters = new String[]{"v", "a", "n", "o", "r", "o", "c", "k"};
    private static final String[] digits = new String[]{"1", "2", "3", "4"};
    private static final String[] symbols = new String[]{"!", "@", "#", "$"};

    private static final List<String> pwds = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        for (int j = 0; j < letters.length; j++) {
            for (int i = 0; i < letters.length - j; i++) {

                int used = j + i + 1;

                final StringBuilder sb = new StringBuilder();

                for (int t = 0; t < letters.length; t++) {
                    sb.append(getLetter(letters, t, i, used--));
                }

                appendLetterAndSymbol(sb);
            }
        }

        tryGuess();
    }

    private static void appendLetterAndSymbol(final StringBuilder prefix) {
        for (int i = 1; i <= digits.length; i++) {//digit
            final StringBuilder work = new StringBuilder(prefix);
            for (int k = 0; k < i; k++) {//digit
                append(work, digits, k, i);
            }

            for (int i1 = 1; i1 <= symbols.length; i1++) {//digit
                final StringBuilder end = new StringBuilder(work);
                for (int k = 0; k < i1; k++) {//symbol
                    append(end, symbols, k, i1);
                }
                pwds.add(end.toString());
                System.out.println(end.toString());
            }
            pwds.add(work.toString());
//            System.out.println(work.toString());
        }
    }

    private static String getLetter(final String[] arr, final int i, final int expected, int used) {
        return (i == expected || (i > expected && used > 0)) ? arr[i].toUpperCase() : arr[i];
    }

    private static void append(final StringBuilder sb, final String[] arr, final int i, final int j) {
        if (i < j) {
            sb.append(arr[i]);
        }
    }

    private static void tryGuess() throws Exception {
        final KeyStore p12 = KeyStore.getInstance("pkcs12");

        final Set<String> lowerCased = pwds.stream()
                .map(String::toLowerCase)
                .collect(Collectors.toSet());
        pwds.addAll(lowerCased);

        for (final String pwd : pwds) {
            try {
                p12.load(new FileInputStream("d:\\Misc\\Эцп Иван\\RSA256_e252fb2ad8851560eab4af1565e01efd743e1577_copy.p12"),
                        pwd.toCharArray());
                System.out.println("Success: " + pwd);
                return;
            } catch (final IOException e) {
                if (!e.getMessage().equals("keystore password was incorrect")) {
                    System.out.println(e.getMessage() + ": '" + pwd + "'");
                }
            }
        }
        System.out.println("No success :(");
    }

}
