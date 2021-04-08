package pl.pk99.symmetricencryptionapi.component;

import pl.pk99.symmetricencryptionapi.config.AlgorithmsConfiguration;

public interface AsymmetricEncryptor {
    /**
     * Encrypts given text by the asymmetric key and the configuration's algorithm.
     *
     * @param text text to encrypt
     * @return encrypted text
     * @see AsymmetricKey
     * @see AlgorithmsConfiguration
     */
    byte[] encrypt(String text);

    /**
     * Decrypts given text by the asymmetric key and the configuration's algorithm.
     *
     * @param encryptedText encrypted text to decrypt
     * @return decrypted text
     * @see AsymmetricKey
     * @see AlgorithmsConfiguration
     */
    String decrypt(byte[] encryptedText);
}
