package pl.pk99.symmetricencryptionapi.component.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.pk99.symmetricencryptionapi.component.SymmetricEncryptor;
import pl.pk99.symmetricencryptionapi.component.SymmetricKey;
import pl.pk99.symmetricencryptionapi.config.AlgorithmsConfiguration;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Component
@RequiredArgsConstructor
public class SymmetricEncryptorImpl implements SymmetricEncryptor {

    private final SymmetricKey symmetricKey;

    private static byte[] INITIALIZATION_VECTOR;

    @Override
    public byte[] encrypt(String text) {
        try {
            Cipher cipher = Cipher.getInstance(AlgorithmsConfiguration.SYMMETRIC_CIPHER_ENCRYPTION_ALGORITHM);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(generateInitializationVector());
            cipher.init(Cipher.ENCRYPT_MODE, symmetricKey.getSecretKey(), ivParameterSpec);
            return cipher.doFinal(text.getBytes());
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException
                | InvalidKeyException | InvalidAlgorithmParameterException e) {
            throw new RuntimeException("Error during encryption occurred");
        }
    }

    @Override
    public String decrypt(byte[] encryptedText) {
        try {
            Cipher cipher = Cipher.getInstance(AlgorithmsConfiguration.SYMMETRIC_CIPHER_ENCRYPTION_ALGORITHM);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(INITIALIZATION_VECTOR);
            cipher.init(Cipher.DECRYPT_MODE, symmetricKey.getSecretKey(), ivParameterSpec);
            return new String(cipher.doFinal(encryptedText));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException
                | InvalidKeyException | InvalidAlgorithmParameterException e) {
            throw new RuntimeException("Error during decryption occurred");
        }
    }

    private byte[] generateInitializationVector() {
        byte[] initializationVector = new byte[16];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(initializationVector);
        INITIALIZATION_VECTOR = initializationVector;
        return initializationVector;
    }
}
