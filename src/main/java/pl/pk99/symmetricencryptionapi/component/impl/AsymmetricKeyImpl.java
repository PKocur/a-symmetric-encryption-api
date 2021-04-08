package pl.pk99.symmetricencryptionapi.component.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import pl.pk99.symmetricencryptionapi.component.AsymmetricKey;
import pl.pk99.symmetricencryptionapi.config.AlgorithmsConfiguration;
import pl.pk99.symmetricencryptionapi.error.ErrorMessages;
import pl.pk99.symmetricencryptionapi.error.exception.ApiException;
import pl.pk99.symmetricencryptionapi.pojo.AsymmetricKeyPair;

import javax.xml.bind.DatatypeConverter;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

@Component
public class AsymmetricKeyImpl implements AsymmetricKey {
    private static KeyPair keyPair;

    @Override
    public KeyPair getKeyPair() {
        if (keyPair == null) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "key", ErrorMessages.KEY_NOT_EXIST);
        }
        return keyPair;
    }

    @Override
    public void setKey(KeyPair keyPair) {
        AsymmetricKeyImpl.keyPair = keyPair;
    }

    @Override
    public void setKey(AsymmetricKeyPair key) {
        KeySpec keySpecPublic = new X509EncodedKeySpec(DatatypeConverter.parseHexBinary(key.getPublicKey()),
                AlgorithmsConfiguration.ASYMMETRIC_ENCRYPTION_ALGORITHM);
        KeySpec keySpecPrivate = new PKCS8EncodedKeySpec(DatatypeConverter.parseHexBinary(key.getPrivateKey()),
                AlgorithmsConfiguration.ASYMMETRIC_ENCRYPTION_ALGORITHM);
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(AlgorithmsConfiguration.ASYMMETRIC_ENCRYPTION_ALGORITHM);
            AsymmetricKeyImpl.keyPair = new KeyPair(keyFactory.generatePublic(keySpecPublic), keyFactory.generatePrivate(keySpecPrivate));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            if (e instanceof InvalidKeySpecException)
                throw new ApiException(HttpStatus.BAD_REQUEST, "key", ErrorMessages.INVALID_KEY_PROVIDED);
            else
                throw new RuntimeException("Error during key setting occurred");
        }
    }
}
