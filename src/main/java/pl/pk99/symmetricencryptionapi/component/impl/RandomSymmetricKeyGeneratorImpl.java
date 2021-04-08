package pl.pk99.symmetricencryptionapi.component.impl;

import org.springframework.stereotype.Component;
import pl.pk99.symmetricencryptionapi.component.RandomSymmetricKeyGenerator;
import pl.pk99.symmetricencryptionapi.config.AlgorithmsConfiguration;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Component
public class RandomSymmetricKeyGeneratorImpl implements RandomSymmetricKeyGenerator {

    @Override
    public SecretKey generate() {
        try {
            SecureRandom secureRandom = new SecureRandom();
            KeyGenerator keyGenerator = KeyGenerator.getInstance(AlgorithmsConfiguration.SYMMETRIC_ENCRYPTION_ALGORITHM);
            keyGenerator.init(256, secureRandom);
            return keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error during key generation occurred");
        }
    }
}
