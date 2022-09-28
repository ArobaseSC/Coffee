package org.arobase.service;

import org.arobase.abstraction.Result;
import org.arobase.abstraction.ResultError;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class RecurseZipDecompressorService implements RecurseDecompressor {

    private static final Logger LOGGER = Logger.getLogger(RecurseZipDecompressorService.class.getName());

    ZipDecompressorService zipDecompressorService = new ZipDecompressorService();

    /**
     * Decompress the given directory to the given destination.
     *
     * @param source      the directory to decompress
     * @param destination the destination where to decompress the file
     */
    @Override
    public Result decompress(File source, File destination) {
        try {
            final var tab = source.listFiles();

            if (tab.length == 0) {
                return Result.fromError(new ResultError("The given directory is empty"));
            } else {
                final var files = Arrays.stream(Objects.requireNonNull(tab))
                        .filter(File::isFile)
                        .collect(Collectors.toUnmodifiableList());

                return this.decompressRecursive(files, destination);
            }
        } catch (NullPointerException exception) {
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

    private Result decompressRecursive(List<File> files, File destination) {
        if (files.isEmpty()) {
            return Result.fromSuccess();
        } else {

            final Result result = zipDecompressorService.decompress(files.get(0), destination);

            return result.success() ? decompressRecursive(files.subList(1, files.size()), destination) : result;

        }
    }

}
