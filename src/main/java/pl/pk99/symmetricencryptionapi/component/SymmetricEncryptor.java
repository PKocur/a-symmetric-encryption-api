package pl.pk99.symmetricencryptionapi.component;

import pl.pk99.symmetricencryptionapi.config.AlgorithmsConfiguration;

public interface SymmetricEncryptor {
    /**
     * Encrypts given text by the symmetric key and the configuration's algorithm.
     *
     * @param text text to encrypt
     * @return encrypted text in bytes
     * @see SymmetricKey
     * @see AlgorithmsConfiguration
     */
    byte[] encrypt(String text);

    /**
     * Decrypts given text by the symmetric key and the configuration's algorithm.
     *
     * @param encryptedText encrypted text to decrypt
     * @return decrypted text
     * @see SymmetricKey
     * @see AlgorithmsConfiguration
     */
    String decrypt(byte[] encryptedText);
}
