package pl.pk99.symmetricencryptionapi.service;

import pl.pk99.symmetricencryptionapi.component.AsymmetricEncryptor;

public interface AsymmetricEncryptorService {
    /**
     * Encrypts given message.
     *
     * @param message message to encrypt
     * @return encrypted message
     * @see AsymmetricEncryptor
     */
    String encodeMessage(String message);

    /**
     * Decrypts given message.
     *
     * @param encryptedMessage to decrypt
     * @return decrypted message
     * @see AsymmetricEncryptor
     */
    String decodeMessage(String encryptedMessage);
}
