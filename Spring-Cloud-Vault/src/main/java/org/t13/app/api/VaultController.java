package org.t13.app.api;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.t13.app.dto.VaultSecretRequest;
import org.t13.app.service.VaultWriteService;
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

    @PostMapping(value = "/files", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFiles(
            @RequestParam String appName,
            @RequestParam String secretName,
            @RequestParam MultipartFile file) throws Exception {

        String path = vaultWriteService.writeFile(appName, secretName, file);

        return ResponseEntity.ok("Stored in Vault at " + path);
    }

}
