package org.arobase.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/****************************************************
 *    Copyright (c) 2022 — Arobase Team
 *
 *    This project may be licensed somewhere,
 *    you may want to check the license file in the GitHub root repository.
 *
 *    All rights reserved to the Arobase team members.
 ****************************************************/

class DecompressorServiceTest {

    protected File temporaryGeneratedZipFile;
    protected File temporaryGeneratedOutputDirectory;
    @TempDir
    private File temporaryFolder;

    @BeforeEach
    void setUp() throws IOException {
        temporaryGeneratedZipFile = File.createTempFile("test", ".zip", temporaryFolder);
        temporaryGeneratedOutputDirectory = Files.createDirectory(
            temporaryFolder.toPath().resolve("output")).toFile();
    }

    @Test
    @DisplayName("The temporary folder should exists")
    void ensureTemporaryFolderExists() {
        assertThat(temporaryFolder).exists();
    }

    @Test
    @DisplayName("The temporary generated zip file should exists")
    void ensureTemporaryGeneratedZipFileExists() {
        assertThat(temporaryGeneratedZipFile).exists();
    }

    @Test
    @DisplayName("The temporary generated output directory should exists")
    void ensureTemporaryGeneratedOutputDirectoryExists() {
        assertThat(temporaryGeneratedOutputDirectory).exists();
    }
}
