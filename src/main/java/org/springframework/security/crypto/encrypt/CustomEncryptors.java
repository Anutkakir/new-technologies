package org.springframework.security.crypto.encrypt;

import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;

public class CustomEncryptors {

    public static TextEncryptor create(CharSequence password, CharSequence salt, final String iv) {
        return new HexEncodingTextEncryptor(new AesBytesEncryptor(password.toString(), salt, new BytesKeyGenerator() {
            @Override
            public int getKeyLength() {
                return 16;
            }

            @Override
            public byte[] generateKey() {
                return Hex.decode(iv);
            }
        }));
    }

}
