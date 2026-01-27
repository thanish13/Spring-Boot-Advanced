package org.t13.app.service;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@Service
public class VaultWriteService {

    private final VaultTemplate vaultTemplate;

    public VaultWriteService(VaultTemplate vaultTemplate) {
        this.vaultTemplate = vaultTemplate;
        log.info("Initialize VaultWriteService Bean");
    }

    public void writeSecret(String path, Map<String, Object> secrets) {

        Map<String, Object> data = new HashMap<>();
        data.put("data", secrets); // KV v2 requires "data"
        log.info("Write secret to path : {}",path);

        vaultTemplate.write("secret/data/" + path, data);

        log.info("Write secret to path : {} successfully" ,path);
    }

    public String writeFile(String app, String type, MultipartFile file) throws IOException {

        String base64 = Base64.getEncoder().encodeToString(file.getBytes());

        Map<String, Object> data = new HashMap<>();
        data.put(type, base64);
        data.put("uploadedAt", Instant.now().toString());

        String path = "secret/data/" + app + "/jks";

        log.info("Write file to path : {}",path);

        vaultTemplate.write(path, Map.of("data", data));

        log.info("Write file success to path : {} successfully",path);

        return path;
    }
}

