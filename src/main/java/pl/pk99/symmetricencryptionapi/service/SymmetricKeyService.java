package pl.pk99.symmetricencryptionapi.service;

import pl.pk99.symmetricencryptionapi.component.SymmetricKey;

public interface SymmetricKeyService {
    /**
     * Gets new symmetric key and set it as the new one.
     *
     * @return new symmetric key
     * @see SymmetricKey
     */
    String getNewKey();

    /**
     * Sets the symmetric key to the new one.
     *
     * @param key new symmetric key
     * @see SymmetricKey
     */
    void setKey(String key);
}
