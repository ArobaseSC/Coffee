package org.arobase.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

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
        temporaryGeneratedZipFile = Files.createTempFile(temporaryFolder.toPath(), "test", ".zip")
            .toFile();

        try (final var zipOutputStream = new ZipOutputStream(
            new FileOutputStream(temporaryGeneratedZipFile))) {
            final var zipEntry = new ZipEntry("quizio.txt");

            zipOutputStream.putNextEntry(zipEntry);
        }

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

    @Test
    @DisplayName("The temporary output folder should be empty")
    void ensureTemporaryOutputFolderIsEmpty() {
        assertThat(temporaryGeneratedOutputDirectory).isEmptyDirectory();
    }

    @Test
    @DisplayName("The zip file should contain a file named quizio.txt")
    void ensureZipFileContainsAFileNamedQuizioTxt() {
        try (final var zipFile = new ZipFile(temporaryGeneratedZipFile)) {
            assertThat(zipFile.getEntry("quizio.txt")).isNotNull();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}
