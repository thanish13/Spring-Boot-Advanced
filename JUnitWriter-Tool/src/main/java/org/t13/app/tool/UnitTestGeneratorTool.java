package org.t13.app.tool;

import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class UnitTestGeneratorTool {

    /**
     * This tool allows the AI agent to write the generated JUnit test code 
     * to the exact path within the Spring Boot src/test/java directory.
     */
    @Tool("Writes a generated JUnit 5 test class file to the local test directory")
    public String writeUnitTestFile(String targetPackage, String className, String fileContent) {
        try {
            // Map the package format (com.example.demo) to directory paths
            String packageFolder = targetPackage.replace('.', '/');
            Path targetDir = Paths.get("src/test/java", packageFolder);
            
            // Ensure directories exist
            Files.createDirectories(targetDir);
            
            Path filePath = targetDir.resolve(className + ".java");
            Files.writeString(filePath, fileContent);
            
            return "Successfully wrote JUnit test file to: " + filePath.toAbsolutePath();
        } catch (IOException e) {
            return "Failed to write JUnit test file due to: " + e.getMessage();
        }
    }
}