package pl.pk99.symmetricencryptionapi.component.impl;

import org.springframework.stereotype.Component;
import pl.pk99.symmetricencryptionapi.component.RandomAsymmetricKeyGenerator;
import pl.pk99.symmetricencryptionapi.config.AlgorithmsConfiguration;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Component
public class RandomAsymmetricKeyGeneratorImpl implements RandomAsymmetricKeyGenerator {

    @Override
    public KeyPair generate() {
        try {
            SecureRandom secureRandom = new SecureRandom();
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(AlgorithmsConfiguration.ASYMMETRIC_ENCRYPTION_ALGORITHM);
            keyPairGenerator.initialize(2048, secureRandom);
            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error during key generation occurred");
        }
    }
}
