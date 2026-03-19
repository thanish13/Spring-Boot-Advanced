package org.t13.app.service;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.*;

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

    public String writeFile(String appName, String secretName, MultipartFile file) throws IOException {
        Map<String, Object> data = new HashMap<>();
        String path;

        if(Objects.requireNonNull(file.getOriginalFilename()).endsWith(".jks")){
            String base64 = Base64.getEncoder().encodeToString(file.getBytes());

            data.put(secretName, base64);
            data.put("uploadedAt", Instant.now().toString());

            path = "secret/data/" + appName + "/jks";
        }else{
            data.put(secretName, new String(file.getBytes(), StandardCharsets.UTF_8));
            data.put("uploadedAt", Instant.now().toString());

            path = "secret/data/" + appName + "/" + secretName;
        }

        log.info("Write file to path : {}",path);

        vaultTemplate.write(path, Map.of("data", data));

        log.info("Write file success to path : {} successfully",path);

        return path;
    }
}

