package org.t13.app.dto;

import java.util.Map;

public class VaultSecretRequest {

    private String path;                // ex: myapp
    private Map<String, Object> secrets; // key-value pairs

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, Object> getSecrets() {
        return secrets;
    }

    public void setSecrets(Map<String, Object> secrets) {
        this.secrets = secrets;
    }
}
