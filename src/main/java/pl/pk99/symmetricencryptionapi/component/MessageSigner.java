package pl.pk99.symmetricencryptionapi.component;

import pl.pk99.symmetricencryptionapi.config.AlgorithmsConfiguration;

public interface MessageSigner {
    /**
     * Signs given message by the private key and the configuration's algorithm.
     *
     * @param message message to sign
     * @return signed message
     * @see AsymmetricKey
     * @see AlgorithmsConfiguration
     */
    byte[] sign(String message);
}
