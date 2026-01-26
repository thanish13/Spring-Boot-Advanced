package org.t13.app.api;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.t13.app.dto.VaultSecretRequest;
import org.t13.app.service.VaultWriteService;

import java.time.Instant;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/vault")
public class VaultController {

    private final VaultWriteService vaultWriteService;

    public VaultController(VaultWriteService vaultWriteService) {
        this.vaultWriteService = vaultWriteService;
    }

    @PostMapping("/secrets")
    public ResponseEntity<String> addSecrets(
            @RequestBody VaultSecretRequest request) {

        vaultWriteService.writeSecret(
                request.getPath(),
                request.getSecrets()
        );

        return ResponseEntity.ok("Secrets stored successfully in Vault");
    }

    @PostMapping(value = "/jks", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadJks(
            @RequestParam String app,
            @RequestParam String type,
            @RequestParam MultipartFile file) throws Exception {

        if (!Objects.requireNonNull(file.getOriginalFilename()).endsWith(".jks")) {
            return ResponseEntity.badRequest().body("Only .jks files allowed");
        }

        String path = vaultWriteService.writeFile(app, type, file);

        return ResponseEntity.ok("Stored in Vault at " + path);
    }

}
