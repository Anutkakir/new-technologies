package com.ivan.newtechnologies.digisign;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class SignatureMain {

    public static void main(String[] args) {
        try {
            // ********************** Signing ******************************
            final String valueForSigning = "Hello, world";

            // Get instance and initialize a KeyPairGenerator object.
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
            keyGen.initialize(1024, random);

            // Get a PrivateKey from the generated key pair.
            KeyPair keyPair = keyGen.generateKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();

            // Get an instance of Signature object and initialize it.
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(privateKey);

            // Supply the data to be signed to the Signature object
            // using the update() method and generate the digital signature.
            byte[] bytes = valueForSigning.getBytes();
            signature.update(bytes);
            String signedBase64String = Base64.getEncoder().encodeToString(signature.sign());

            System.out.println(signedBase64String);

            // *********************** Verifying *****************************
            final X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyPair.getPublic().getEncoded());
            final KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            final PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);

            final Signature verifierSignature = Signature.getInstance("SHA256withRSA");
            verifierSignature.initVerify(publicKey); // or keyPair.getPublic() if it is available
            verifierSignature.update(valueForSigning.getBytes());
            final boolean correct = verifierSignature.verify(Base64.getDecoder().decode(signedBase64String));
            if (correct) {
                System.out.println("Verified");
            } else {
                System.out.println("Not verified");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
