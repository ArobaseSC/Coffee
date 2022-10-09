package org.arobase.service.assignment;

import org.arobase.abstraction.Result;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

/****************************************************
 *    Copyright (c) 2022 — Arobase Team
 *<p>
 *    This project may be licensed somewhere,
 *    you may want to check the license file in the GitHub root repository.
 *</p>
 *    All rights reserved to the Arobase team members.
 ****************************************************/

public class AssignmentService {

    protected static final String NOT_A_VALID_TEST_ASSIGNMENT_FILE = "The file is not a valid test assignment file.";
    private static final String CLASS_FILE_EXTENSION = ".class";

    public final Result isTestAssigmentFile(final String source) {
        try {
            final var allLines = Files.readAllLines(new File(source).toPath());
            final var isTestAssignment = allLines.stream().anyMatch(line -> line.contains("@Test"));

            return Result.fromBoolean(Boolean.valueOf(isTestAssignment),
                "The file is not a test assignment file");
        } catch (final IOException ioException) {
            return Result.fromException(ioException);
        }
    }

    public final Collection<File> getTestAssignmentFiles(final String source) {
        try (final var stream = Files.walk(new File(source).toPath())) {
            return stream.map(Path::toFile).filter(File::isFile)
                .filter(file -> this.isTestAssigmentFile(file.getAbsolutePath()).success())
                .collect(Collectors.toList());
        } catch (final IOException ioException) {
            return Collections.emptyList();
        }
    }

    public final Result isCompiled(final String source) {
        final var isCompiled = source.endsWith(CLASS_FILE_EXTENSION);

        return Result.fromBoolean(Boolean.valueOf(isCompiled), "The file is not compiled.");
    }

    public final Result valid(final String source) {
        final var isTestAssignmentFile = this.isTestAssigmentFile(source);
        final var isCompiled = this.isCompiled(source);
        final var isFile = new File(source).isFile();

        return Result.fromBoolean(
            Boolean.valueOf(isTestAssignmentFile.success() && isCompiled.success() && isFile),
            NOT_A_VALID_TEST_ASSIGNMENT_FILE);
    }

    //FIXME: Should be dispatch to another class ASAP for SRP
    public Result compile(final String source) {
        if (!this.valid(source).success()) {
            return Result.fromBoolean(Boolean.FALSE, NOT_A_VALID_TEST_ASSIGNMENT_FILE);
        }

        if (this.isCompiled(source).success()) {
            return Result.fromBoolean(Boolean.TRUE, "The file is already compiled.");
        }

        final var file = new File(source);
        final var processBuilder = new ProcessBuilder("javac", file.getAbsolutePath());
        final Process process;

        try {
            process = processBuilder.start();
            process.waitFor();
        } catch (final IOException | InterruptedException ioException) {
            return Result.fromException(ioException);
        }

        return Result.fromBoolean(Boolean.TRUE, "The file is compiled.");
    }
}