package pl.pk99.symmetricencryptionapi.component;

import pl.pk99.symmetricencryptionapi.config.AlgorithmsConfiguration;

public interface MessageVerifier {
    /**
     * Verifies given message by the public key and the configuration's algorithm.
     *
     * @param message message to verify
     * @return verified message
     * @see AsymmetricKey
     * @see AlgorithmsConfiguration
     */
    boolean verify(String message, byte[] signedMessage);
}
