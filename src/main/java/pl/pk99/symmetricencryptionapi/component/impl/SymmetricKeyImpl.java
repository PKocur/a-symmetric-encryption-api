package pl.pk99.symmetricencryptionapi.component.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import pl.pk99.symmetricencryptionapi.component.SymmetricKey;
import pl.pk99.symmetricencryptionapi.config.AlgorithmsConfiguration;
import pl.pk99.symmetricencryptionapi.error.ErrorMessages;
import pl.pk99.symmetricencryptionapi.error.exception.ApiException;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@Component
public class SymmetricKeyImpl implements SymmetricKey {

    private static SecretKey key;

    @Override
    public SecretKey getSecretKey() {
        if (key == null) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "key", ErrorMessages.KEY_NOT_EXIST);
        }
        return key;
    }

    public void setKey(SecretKey key) {
        SymmetricKeyImpl.key = key;
    }

    public void setKey(byte[] key) {
        SymmetricKeyImpl.key = new SecretKeySpec(key, AlgorithmsConfiguration.SYMMETRIC_ENCRYPTION_ALGORITHM);
    }
}
