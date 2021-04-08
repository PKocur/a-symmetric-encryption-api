package pl.pk99.symmetricencryptionapi.service;

import pl.pk99.symmetricencryptionapi.component.AsymmetricKey;
import pl.pk99.symmetricencryptionapi.form.MessageForm;
import pl.pk99.symmetricencryptionapi.form.MessageVerifierForm;

public interface AsymmetricSignerVerifierService {
    /**
     * Signs given message using asymmetric key.
     *
     * @param messageForm message provided by user
     * @return signed message
     * @see AsymmetricKey
     */
    String signMessage(MessageForm messageForm);

    /**
     * Verifies given message using asymmetric key.
     *
     * @param messageForm plain and signed message provided by user
     * @return true if message was successfully verified, otherwise false
     */
    boolean verifyMessage(MessageVerifierForm messageForm);
}
