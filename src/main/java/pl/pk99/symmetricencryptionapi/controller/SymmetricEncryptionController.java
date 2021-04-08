package pl.pk99.symmetricencryptionapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.pk99.symmetricencryptionapi.error.exception.ApiException;
import pl.pk99.symmetricencryptionapi.form.MessageForm;
import pl.pk99.symmetricencryptionapi.form.SymmetricKeyForm;
import pl.pk99.symmetricencryptionapi.service.SymmetricEncryptorService;
import pl.pk99.symmetricencryptionapi.service.SymmetricKeyService;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/symmetric")
@RequiredArgsConstructor
public class SymmetricEncryptionController {

    private final SymmetricKeyService symmetricKeyService;
    private final SymmetricEncryptorService symmetricEncryptorService;

    @GetMapping("/key")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, String> getKey() throws ApiException {
        return Map.of("key", symmetricKeyService.getNewKey());
    }

    @PostMapping("/key")
    @ResponseStatus(HttpStatus.OK)
    public void setKey(@RequestBody @Valid SymmetricKeyForm symmetricKeyForm) throws ApiException {
        symmetricKeyService.setKey(symmetricKeyForm.getKey());
    }

    @PostMapping("/encode")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, String> encodeMessage(@RequestBody @Valid MessageForm messageForm) {
        return Map.of("encryptedMessage", symmetricEncryptorService.encodeMessage(messageForm.getMessage()));
    }

    @PostMapping("/decode")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, String> decodeMessage(@RequestBody @Valid MessageForm messageForm) {
        return Map.of("message", symmetricEncryptorService.decodeMessage(messageForm.getMessage()));
    }
}
