package pl.pk99.symmetricencryptionapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pk99.symmetricencryptionapi.error.exception.ApiException;
import pl.pk99.symmetricencryptionapi.form.AsymmetricKeyForm;
import pl.pk99.symmetricencryptionapi.form.MessageForm;
import pl.pk99.symmetricencryptionapi.form.MessageVerifierForm;
import pl.pk99.symmetricencryptionapi.pojo.AsymmetricKeyPair;
import pl.pk99.symmetricencryptionapi.service.AsymmetricEncryptorService;
import pl.pk99.symmetricencryptionapi.service.AsymmetricKeyService;
import pl.pk99.symmetricencryptionapi.service.AsymmetricSignerVerifierService;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/asymmetric")
@RequiredArgsConstructor
public class AsymmetricEncryptionController {

    private final AsymmetricKeyService asymmetricKeyService;
    private final AsymmetricEncryptorService asymmetricEncryptorService;
    private final AsymmetricSignerVerifierService asymmetricSignerVerifierService;

    @GetMapping("/key")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AsymmetricKeyPair> getKey() throws ApiException {
        return new ResponseEntity<>(AsymmetricKeyPair.of(asymmetricKeyService.getNewKeyPair().getPublicKey(),
                asymmetricKeyService.getNewKeyPair().getPrivateKey()), HttpStatus.OK);
    }

    @GetMapping("/key/ssh")
    @ResponseStatus(HttpStatus.OK)
    public String getKeySSH() throws ApiException {
        // TODO
        return "TODO method";
    }

    @PostMapping("/key")
    @ResponseStatus(HttpStatus.OK)
    public void setKey(@RequestBody @Valid AsymmetricKeyForm asymmetricKeyForm) throws ApiException {
        asymmetricKeyService.setKey(asymmetricKeyForm);
    }

    @PostMapping("/sign")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, String> signMessage(@RequestBody @Valid MessageForm messageForm) throws ApiException {
        return Map.of("signedMessage", asymmetricSignerVerifierService.signMessage(messageForm));
    }

    @PostMapping("/verify")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Boolean> verifyMessage(@RequestBody @Valid MessageVerifierForm messageForm) throws ApiException {
        return Map.of("verifyResult", asymmetricSignerVerifierService.verifyMessage(messageForm));
    }

    @PostMapping("/encode")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, String> encodeMessage(@RequestBody @Valid MessageForm messageForm) {
        return Map.of("encryptedMessage", asymmetricEncryptorService.encodeMessage(messageForm.getMessage()));
    }

    @PostMapping("/decode")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, String> decodeMessage(@RequestBody @Valid MessageForm messageForm) {
        return Map.of("message", asymmetricEncryptorService.decodeMessage(messageForm.getMessage()));
    }
}
