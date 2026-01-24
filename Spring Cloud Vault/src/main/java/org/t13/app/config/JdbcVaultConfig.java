package org.t13.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponse;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
public class JdbcVaultConfig {

    @Bean
    public DataSource dataSource(VaultTemplate vaultTemplate) {

        // SIMPLE STRING PATH
        VaultResponse response = vaultTemplate.read("secret/db");

        assert response != null;
        Map<String, Object> data = response.getData();

        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:postgresql://localhost:5432/mydb");
        assert data != null;
        ds.setUsername((String) data.get("username"));
        ds.setPassword((String) data.get("password"));

        return ds;
    }
}
