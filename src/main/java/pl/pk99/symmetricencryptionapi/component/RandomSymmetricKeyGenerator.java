package pl.pk99.symmetricencryptionapi.component;

import javax.crypto.SecretKey;

public interface RandomSymmetricKeyGenerator {
    /**
     * Generates a new symmetric key.
     *
     * @return new symmetric key
     */
    SecretKey generate();
}
