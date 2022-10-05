package org.arobase.service.assignment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;

public class AssignmentServiceMiddlewareTest {

    private File nonTestJavaFile;

    private File testJavaFile;

    @TempDir
    private File temporaryFolder;

    @BeforeEach
    void setup() throws IOException {
        this.nonTestJavaFile = File.createTempFile("nonTest", ".java", this.temporaryFolder);
        this.testJavaFile = File.createTempFile("test", ".java", this.temporaryFolder);

        nonTestJavaFile.deleteOnExit();
        testJavaFile.deleteOnExit();

        // fill
        var testCodeContent = """
                package org.arobase.service.assignment;
                                
                import org.junit.jupiter.api.BeforeEach;
                                
                public class AssignmentServiceTest {
                                
                    @BeforeEach
                    void setup() {
                                
                    }
                    
                    @Test
                    void testFoo() {
                        // TODO
                    }
                                
                }
                """;

        try (var writer = new java.io.FileWriter(this.testJavaFile)) {
            writer.write(testCodeContent);
        }

        var nonTestCodeContent = """
                package org.arobase.service.assignment;
                                
                import org.junit.jupiter.api.BeforeEach;
                                
                public class AssignmentServiceTest {
                                
                    @BeforeEach
                    void setup() {
                                
                    }
                }
                """;

        try (var writer = new java.io.FileWriter(this.nonTestJavaFile)) {
            writer.write(nonTestCodeContent);
        }
    }

    @Test
    @DisplayName("The temporary folder should exists")
    void ensureTemporaryFolderExists() {
        Assertions.assertThat(this.temporaryFolder).exists();
    }

    @Test
    @DisplayName("The temporary generated non test java file should exists")
    void ensureTemporaryGeneratedNonTestJavaFileExists() {
        Assertions.assertThat(this.nonTestJavaFile).exists();
    }

    @Test
    @DisplayName("The temporary generated test java file should exists")
    void ensureTemporaryGeneratedTestJavaFileExists() {
        Assertions.assertThat(this.testJavaFile).exists();
    }

}
