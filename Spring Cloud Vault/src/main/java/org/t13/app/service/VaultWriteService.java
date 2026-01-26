package org.t13.app.service;
import org.springframework.stereotype.Service;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class VaultWriteService {

    private final VaultTemplate vaultTemplate;

    public VaultWriteService(VaultTemplate vaultTemplate) {
        this.vaultTemplate = vaultTemplate;
    }

    public void writeSecret(String path, Map<String, Object> secrets) {

        Map<String, Object> data = new HashMap<>();
        data.put("data", secrets); // KV v2 requires "data"

        vaultTemplate.write("secret/data/" + path, data);
    }

    public String writeFile(String app, String type, MultipartFile file) throws IOException {

        String base64 = Base64.getEncoder().encodeToString(file.getBytes());

        Map<String, Object> data = new HashMap<>();
        data.put(type, base64);
        data.put("uploadedAt", Instant.now().toString());

        String path = "secret/data/" + app + "/jks";

        vaultTemplate.write(path, Map.of("data", data));

        return path;
    }
}

