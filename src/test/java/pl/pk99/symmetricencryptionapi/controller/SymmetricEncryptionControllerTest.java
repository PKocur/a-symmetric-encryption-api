package pl.pk99.symmetricencryptionapi.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.pk99.symmetricencryptionapi.service.SymmetricEncryptorService;
import pl.pk99.symmetricencryptionapi.service.SymmetricKeyService;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SymmetricEncryptionControllerTest {

    private final String KEY_URI = "/symmetric/key";
    private final String ENCODE_URI = "/symmetric/encode";
    private final String DECODE_URI = "/symmetric/decode";

    private final String WRONG_PROPERTY_KEY = "wrongProperty";
    private final String WRONG_PROPERTY_VALUE = "wrongValue";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SymmetricKeyService symmetricKeyService;

    @MockBean
    private SymmetricEncryptorService symmetricEncryptorService;

    @Test
    public void getKey_nullBody_201() throws Exception {
        String key = "mockKey";
        Mockito.when(symmetricKeyService.getNewKey()).thenReturn(key);

        mockMvc
                .perform(get(KEY_URI))
                .andExpect(status().isCreated())
                .andExpect(content().string("{\"key\":" + "\"" + key + "\"}"));
    }

    @Test
    public void setKey_nullBody_400() throws Exception {
        mockMvc
                .perform(post(KEY_URI)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void setKey_invalidData_400() throws Exception {
        Map<String, String> jsonBody = new HashMap<>();
        jsonBody.put(WRONG_PROPERTY_KEY, WRONG_PROPERTY_VALUE);

        mockMvc
                .perform(post(KEY_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new JSONObject(jsonBody).toString()))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("{\"errors\":{\"key\":\"Field is required\"}}"));
    }

    @Test
    public void setKey_validData_200() throws Exception {
        Map<String, String> jsonBody = new HashMap<>();
        jsonBody.put("key", "1234");

        mockMvc
                .perform(post(KEY_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new JSONObject(jsonBody).toString()))
                .andExpect(status().isOk());
    }

    @Test
    public void encodeMessage_nullBody_400() throws Exception {
        mockMvc
                .perform(post(ENCODE_URI)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void encodeMessage_invalidData_400() throws Exception {
        Map<String, String> jsonBody = new HashMap<>();
        jsonBody.put(WRONG_PROPERTY_KEY, WRONG_PROPERTY_VALUE);

        mockMvc
                .perform(post(ENCODE_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new JSONObject(jsonBody).toString()))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("{\"errors\":{\"message\":\"Field is required\"}}"));
    }

    @Test
    public void encodeMessage_validData_200() throws Exception {
        String message = "mockMessage";
        String encryptedMessage = "mockEncryptedMessage";
        Map<String, String> jsonBody = new HashMap<>();
        jsonBody.put("message", message);
        Mockito.when(symmetricEncryptorService.encodeMessage(message)).thenReturn(encryptedMessage);

        mockMvc
                .perform(post(ENCODE_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new JSONObject(jsonBody).toString()))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"encryptedMessage\":" + "\"" + encryptedMessage + "\"}"));
    }

    @Test
    public void decodeMessage_nullBody_400() throws Exception {
        mockMvc
                .perform(post(DECODE_URI)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void decodeMessage_invalidData_400() throws Exception {
        Map<String, String> jsonBody = new HashMap<>();
        jsonBody.put(WRONG_PROPERTY_KEY, WRONG_PROPERTY_VALUE);

        mockMvc
                .perform(post(DECODE_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new JSONObject(jsonBody).toString()))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("{\"errors\":{\"message\":\"Field is required\"}}"));
    }

    @Test
    public void decodeMessage_validData_200() throws Exception {
        String message = "mockMessage";
        String decryptedMessage = "mockDecryptedMessage";
        Map<String, String> jsonBody = new HashMap<>();
        jsonBody.put("message", message);
        Mockito.when(symmetricEncryptorService.decodeMessage(message)).thenReturn(decryptedMessage);

        mockMvc
                .perform(post(DECODE_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new JSONObject(jsonBody).toString()))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"message\":" + "\"" + decryptedMessage + "\"}"));
    }
}
