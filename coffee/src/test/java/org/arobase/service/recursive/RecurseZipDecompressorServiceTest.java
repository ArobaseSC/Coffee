package org.arobase.service.recursive;

import static org.assertj.core.api.Assertions.assertThat;

import org.arobase.abstraction.Result;
import org.arobase.service.RecurseZipDecompressorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.beans.Transient;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

class RecurseZipDecompressorServiceTest {


    protected static final String ZIP_CONTENT_FILE_NAME1 = "quizio.txt";

    protected static final String ZIP_CONTENT_FILE_NAME2 = "tests.txt";

    protected static final String ZIP_CONTENT_FILE_NAME3 = "abuse.txt";

    protected static final String ZIP_CONTENT_FILE_NAME4 = "moche.txt";


    private File temporaryGeneratedZipFile1;

    private File temporaryGeneratedZipFile2;

    private File temporaryGeneratedZipFile3;

    private File temporaryGeneratedZipFile4;

    private File temporaryGeneratedOutputDirectory;

    private RecurseZipDecompressorService recurseZipDecompressorService;

    @TempDir
    private File temporaryFolder;

    @TempDir
    private File temporaryFolderEmpty;

    @BeforeEach
    void setUp() throws IOException {
        this.temporaryGeneratedZipFile1 = Files.createTempFile(this.temporaryFolder.toPath(),
            "test1",
            ".zip").toFile();

        this.temporaryGeneratedZipFile2 = Files.createTempFile(this.temporaryFolder.toPath(),
            "test2",
            ".zip").toFile();

        this.temporaryGeneratedZipFile3 = Files.createTempFile(this.temporaryFolder.toPath(),
            "test3",
            ".zip").toFile();

        this.temporaryGeneratedZipFile4 = Files.createTempFile(this.temporaryFolder.toPath(),
            "test4",
            ".zip").toFile();

        try (final var zipOutputStream = new ZipOutputStream(
            new FileOutputStream(this.temporaryGeneratedZipFile1))) {
            final var zipEntry = new ZipEntry(ZIP_CONTENT_FILE_NAME1);

            zipOutputStream.putNextEntry(zipEntry);
        }

        try (final var zipOutputStream = new ZipOutputStream(
            new FileOutputStream(this.temporaryGeneratedZipFile2))) {
            final var zipEntry = new ZipEntry(ZIP_CONTENT_FILE_NAME2);

            zipOutputStream.putNextEntry(zipEntry);
        }

        try (final var zipOutputStream = new ZipOutputStream(
            new FileOutputStream(this.temporaryGeneratedZipFile3))) {
            final var zipEntry = new ZipEntry(ZIP_CONTENT_FILE_NAME3);

            zipOutputStream.putNextEntry(zipEntry);
        }

        try (final var zipOutputStream = new ZipOutputStream(
            new FileOutputStream(this.temporaryGeneratedZipFile4))) {
            final var zipEntry = new ZipEntry(ZIP_CONTENT_FILE_NAME4);

            zipOutputStream.putNextEntry(zipEntry);
        }

        this.temporaryGeneratedOutputDirectory = Files.createDirectory(
            this.temporaryFolder.toPath().resolve("recOutput")).toFile();

        this.recurseZipDecompressorService = new RecurseZipDecompressorService();
    }

    @Test
    @DisplayName("Extract empty file should return a failure result")
    void decompressEmpty() {
        final var result = this.recurseZipDecompressorService.decompress(this.temporaryFolderEmpty,
            this.temporaryGeneratedOutputDirectory);

        assertThat(result).isNotNull().isInstanceOf(Result.class).extracting(Result::success)
            .isEqualTo(Boolean.FALSE);
    }

    @Test
    @DisplayName("Extract not existing directory should return a failure result")
    void decompressNotExistingDirectory() {
        final var result = this.recurseZipDecompressorService.decompress(new File("notExisting"),
            this.temporaryGeneratedOutputDirectory);

        assertThat(result).isNotNull().isInstanceOf(Result.class).extracting(Result::success)
            .isEqualTo(Boolean.FALSE);
    }

    @Test
    @DisplayName("Extract the zip file should return a success result")
    void decompress() {
        final var result = this.recurseZipDecompressorService.decompress(this.temporaryFolder,
            this.temporaryGeneratedOutputDirectory);

        assertThat(result).isNotNull().isInstanceOf(Result.class).extracting(Result::success)
            .isEqualTo(Boolean.TRUE);
    }

    @Test
    @DisplayName("Extract the directory in an unexisting output directory should return a success result")
    void decompressInUnexistingOutputDirectory() {
        final var result = this.recurseZipDecompressorService.decompress(this.temporaryFolder,
            new File("truc"));

        assertThat(result).isNotNull().isInstanceOf(Result.class).extracting(Result::success)
            .isEqualTo(true);
    }

    @Test
    @DisplayName("Extract the directory should unzip all directories in the output directory")
    void decompressAllDirectories() {
        final var result = this.recurseZipDecompressorService.decompress(this.temporaryFolder,
            this.temporaryGeneratedOutputDirectory);

        assertThat(result).isNotNull().isInstanceOf(Result.class).extracting(Result::success)
            .isEqualTo(true);

        assertThat(this.temporaryGeneratedOutputDirectory.listFiles()).isNotNull()
            .hasSize(4);

        assertThat(this.temporaryGeneratedOutputDirectory.listFiles()).extracting(File::getName)
            .containsExactlyInAnyOrder(this.temporaryGeneratedZipFile1.getName().replace(".zip", ""),
                this.temporaryGeneratedZipFile2.getName().replace(".zip", ""),
                this.temporaryGeneratedZipFile3.getName().replace(".zip", ""),
                this.temporaryGeneratedZipFile4.getName().replace(".zip", ""));
    }

    @Test
    @DisplayName("Extract the directory should correctly unzip all files in the output directory and subdirectories")
    void decompressAllFiles() {
        final var result = this.recurseZipDecompressorService.decompress(this.temporaryFolder,
            this.temporaryGeneratedOutputDirectory);

        assertThat(result).isNotNull().isInstanceOf(Result.class).extracting(Result::success)
            .isEqualTo(true);

        assertThat(this.temporaryGeneratedOutputDirectory.listFiles()).extracting(File::listFiles)
            .extracting(files -> files[0].getName())
            .containsExactlyInAnyOrder(ZIP_CONTENT_FILE_NAME1, ZIP_CONTENT_FILE_NAME2,
                ZIP_CONTENT_FILE_NAME3, ZIP_CONTENT_FILE_NAME4);
    }

    @Test
    @DisplayName("Decompress recursive should return a failure result when the output directory is not a directory")
    void decompressRecursiveOutputNotDirectory() {
        final var result = this.recurseZipDecompressorService.decompress(this.temporaryFolder,
            this.temporaryGeneratedZipFile1);

        assertThat(result).isNotNull().isInstanceOf(Result.class).extracting(Result::success)
            .isEqualTo(false);
    }

}