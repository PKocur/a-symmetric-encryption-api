package pl.pk99.symmetricencryptionapi.component;

import pl.pk99.symmetricencryptionapi.pojo.AsymmetricKeyPair;

import java.security.KeyPair;

public interface AsymmetricKey {
    /**
     * Gets the asymmetric key.
     *
     * @return pair of public and private keys
     */
    KeyPair getKeyPair();

    /**
     * Sets the asymmetric key.
     *
     * @param key new asymmetric key
     */
    void setKey(KeyPair key);

    /**
     * Sets the asymmetric key.
     *
     * @param key new asymmetric key
     */
    void setKey(AsymmetricKeyPair key);
}
