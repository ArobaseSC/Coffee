package org.arobase.service;

import static org.assertj.core.api.Assertions.assertThat;

import net.lingala.zip4j.ZipFile;

import org.arobase.abstraction.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

/****************************************************
 *    Copyright (c) 2022 — Arobase Team
 *
 *    This project may be licensed somewhere,
 *    you may want to check the license file in the GitHub root repository.
 *
 *    All rights reserved to the Arobase team members.
 ****************************************************/

class ZipDecompressorServiceTest extends DecompressorServiceTest {

    private ZipDecompressorService zipDecompressorService;

    @BeforeEach
    void setUp() throws IOException {
        super.setUp();

        this.zipDecompressorService = new ZipDecompressorService();
    }

    @Test
    @DisplayName("The temporary generated zip file should be a zip file")
    void ensureTemporaryGeneratedZipFileIsAZipFile() throws IOException {
        try (final var zipFile = new ZipFile(this.temporaryGeneratedZipFile)) {
            assertThat(zipFile).isNotNull();
        }
    }

    @Test
    @DisplayName("Extract the zip file should return a success result")
    void extractZipFileShouldReturnASuccessResult() {
        final var result = this.zipDecompressorService.decompress(this.temporaryGeneratedZipFile,
            this.temporaryGeneratedOutputDirectory);

        assertThat(result).isNotNull().isInstanceOf(Result.class).extracting(Result::success)
            .isEqualTo(Boolean.TRUE);
    }

    @Test
    @DisplayName("Extract an unexisting zip file should return a failure result")
    void extractUnexistingZipFileShouldReturnAFailureResult() {
        final var result = this.zipDecompressorService.decompress(new File("unexisting.zip"),
            this.temporaryGeneratedOutputDirectory);

        assertThat(result).isNotNull().isInstanceOf(Result.class).extracting(Result::success)
            .isEqualTo(Boolean.FALSE);
    }

    @Test
    @DisplayName("Extract the zip file in an unexisting output directory should return a success result")
    void extractZipFileInUnexistingOutputDirectoryShouldReturnASuccessResult() {
        final var result = this.zipDecompressorService.decompress(this.temporaryGeneratedZipFile,
            new File("thing"));

        assertThat(result).isNotNull().isInstanceOf(Result.class).extracting(Result::success)
            .isEqualTo(Boolean.TRUE);
    }

    @Test
    @DisplayName("Extract the zip file in an output directory should extract its content")
    void extractZipFileInOutputDirectoryShouldExtractItsContent() {
        final var result = this.zipDecompressorService.decompress(this.temporaryGeneratedZipFile,
            this.temporaryGeneratedOutputDirectory);

        assertThat(result).isNotNull().isInstanceOf(Result.class).extracting(Result::success)
            .isEqualTo(Boolean.TRUE);

        assertThat(this.temporaryGeneratedOutputDirectory.listFiles()).isNotNull().isNotEmpty();
        assertThat(Arrays.stream(
                Objects.requireNonNull(this.temporaryGeneratedOutputDirectory.listFiles()))
            .anyMatch(file -> file.getName().equals(ZIP_CONTENT_FILE_NAME))).isTrue();
    }
}
