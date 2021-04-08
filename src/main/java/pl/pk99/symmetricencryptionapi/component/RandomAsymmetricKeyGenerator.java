package pl.pk99.symmetricencryptionapi.component;

import java.security.KeyPair;

public interface RandomAsymmetricKeyGenerator {
    /**
     * Generates a new asymmetric key.
     *
     * @return new asymmetric key
     */
    KeyPair generate();
}
