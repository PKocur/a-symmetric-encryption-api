package pl.pk99.symmetricencryptionapi.component.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import pl.pk99.symmetricencryptionapi.component.AsymmetricKey;
import pl.pk99.symmetricencryptionapi.component.MessageSigner;
import pl.pk99.symmetricencryptionapi.config.AlgorithmsConfiguration;
import pl.pk99.symmetricencryptionapi.error.ErrorMessages;
import pl.pk99.symmetricencryptionapi.error.exception.ApiException;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Base64;

@Component
@RequiredArgsConstructor
public class MessageSignerImpl implements MessageSigner {

    private final AsymmetricKey asymmetricKey;

    @Override
    public byte[] sign(String message) {
        try {
            Signature signature = Signature.getInstance(AlgorithmsConfiguration.ASYMMETRIC_SIGN_ALGORITHM);
            signature.initSign(asymmetricKey.getKeyPair().getPrivate());
            signature.update(message.getBytes());
            byte[] signatureBytes = signature.sign();
            return Base64.getEncoder().encode(signatureBytes);
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            if (e instanceof InvalidKeyException)
                throw new ApiException(HttpStatus.BAD_REQUEST, "key", ErrorMessages.INVALID_KEY_PROVIDED);
            else
                throw new RuntimeException("Error during message signing occurred");
        }
    }
}
