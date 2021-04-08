package pl.pk99.symmetricencryptionapi.service;

import pl.pk99.symmetricencryptionapi.component.AsymmetricKey;
import pl.pk99.symmetricencryptionapi.form.AsymmetricKeyForm;
import pl.pk99.symmetricencryptionapi.pojo.AsymmetricKeyPair;

public interface AsymmetricKeyService {
    /**
     * Gets new asymmetric key and set it as the new one.
     *
     * @return new asymmetric key
     * @see AsymmetricKey
     */
    AsymmetricKeyPair getNewKeyPair();

    /**
     * Sets the asymmetric key to the new one.
     *
     * @param asymmetricKeyForm new asymmetric key provided by user
     */
    void setKey(AsymmetricKeyForm asymmetricKeyForm);
}
