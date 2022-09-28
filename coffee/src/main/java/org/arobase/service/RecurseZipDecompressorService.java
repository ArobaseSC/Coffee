package org.arobase.service;

import org.arobase.abstraction.Result;
import org.arobase.abstraction.ResultError;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

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
        final List<File> files = Arrays.stream(Objects.requireNonNull(source.listFiles())).toList();

        return this.decompressRecursive(files, destination);
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
