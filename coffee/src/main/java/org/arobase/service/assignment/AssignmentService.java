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

    public Result isTestAssigmentFile(final String source) {
        try {
            final var allLines = Files.readAllLines(new File(source).toPath());
            final var isTestAssignment = allLines.stream().anyMatch(line -> line.contains("@Test"));

            return Result.fromBoolean(isTestAssignment, "The file is not a test assignment file");
        } catch (final IOException ioException) {
            return Result.fromException(ioException);
        }
    }

    public final Collection<File> getTestAssignmentFiles(final String source) {
        try (final var stream = Files.walk(new File(source).toPath())) {
            return stream.map(Path::toFile)
                    .filter(File::isFile)
                    .filter(file -> isTestAssigmentFile(file.getAbsolutePath()).success())
                    .collect(Collectors.toList());
        } catch (final IOException ioException) {
            return Collections.emptyList();
        }
    }
}
