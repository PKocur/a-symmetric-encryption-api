package pl.pk99.symmetricencryptionapi.pojo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AsymmetricKeyPair {
    private final String publicKey;
    private final String privateKey;

    public static AsymmetricKeyPair of(String publicKey, String privateKey) {
        return new AsymmetricKeyPair(publicKey, privateKey);
    }
}
