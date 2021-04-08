package pl.pk99.symmetricencryptionapi.component;

import javax.crypto.SecretKey;

public interface SymmetricKey {
    /**
     * Gets the symmetric key.
     *
     * @return symmetric key
     */
    SecretKey getSecretKey();

    /**
     * Sets the symmetric key.
     *
     * @param key new symmetric key
     */
    void setKey(SecretKey key);

    /**
     * Sets the symmetric key.
     *
     * @param key new symmetric key
     */
    void setKey(byte[] key);
}
