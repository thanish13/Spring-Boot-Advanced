package org.t13.app.service;
import org.springframework.stereotype.Service;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponse;

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
}

