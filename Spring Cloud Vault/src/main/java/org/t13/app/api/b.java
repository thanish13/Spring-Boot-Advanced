package org.t13.app.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
