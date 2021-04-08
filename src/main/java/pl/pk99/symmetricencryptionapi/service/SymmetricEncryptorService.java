package pl.pk99.symmetricencryptionapi.service;

import pl.pk99.symmetricencryptionapi.component.SymmetricEncryptor;

public interface SymmetricEncryptorService {
    /**
     * Encrypts given message.
     *
     * @param message message to encrypt
     * @return encrypted message
     * @see SymmetricEncryptor
     */
    String encodeMessage(String message);

    /**
     * Decrypts given message.
     *
     * @param encryptedMessage message to decrypt
     * @return decrypted message
     * @see SymmetricEncryptor
     */
    String decodeMessage(String encryptedMessage);
}
