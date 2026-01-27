package org.t13.app.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class VaultSecretRequest {

    private String path;                // ex: myapp
    private Map<String, Object> secrets; // key-value pairs

}
