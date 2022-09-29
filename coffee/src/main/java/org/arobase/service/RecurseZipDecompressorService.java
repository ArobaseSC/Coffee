package org.arobase.service;

import org.arobase.abstraction.Result;
import org.arobase.abstraction.ResultError;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * TODO
 */
public class RecurseZipDecompressorService implements RecurseDecompressor {

    private static final Logger LOGGER = Logger.getLogger(
        RecurseZipDecompressorService.class.getName());

    protected final ZipDecompressorService zipDecompressorService = new ZipDecompressorService();

    /**
     * Decompress the given directory to the given destination.
     *
     * @param source      the directory to decompress
     * @param destination the destination where to decompress the file
     */
    @Override
    public Result decompress(final File source, final File destination) {
        try {
            final var tab = source.listFiles();

            Objects.requireNonNull(tab, "The source directory is empty");

            if (0 == tab.length) {
                return Result.fromError(new ResultError("The given directory is empty"));
            } else {
                final var files = Arrays.stream(Objects.requireNonNull(tab)).filter(File::isFile)
                    .collect(Collectors.toUnmodifiableList());

                return this.decompressRecursive(files, destination);
            }
        } catch (final NullPointerException exception) {
            LOGGER.severe(exception.getMessage());

            return Result.fromError(new ResultError(exception.getMessage()));
        }

    }

    /**
     * Decompress all files inside the given folder to the given destination.
     *
     * @param files       the list of files to decompress
     * @param destination the destination where to decompress the files
     * @return a {@link Result} that will be a success if the decompression succeed
     */

    private Result decompressRecursive(final List<File> files, final File destination) {
        if (files.isEmpty()) {
            return Result.fromSuccess();
        } else {

            final Result result = this.zipDecompressorService.decompress(files.get(0),
                destination.toPath().resolve(files.get(0).getName().replace(".zip", "")).toFile());

            return result.success() ? this.decompressRecursive(files.subList(1, files.size()),
                destination) : result;

        }
    }

}
