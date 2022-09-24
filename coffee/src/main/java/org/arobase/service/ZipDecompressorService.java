package org.arobase.service;

import net.lingala.zip4j.ZipFile;

import org.arobase.abstraction.Result;
import org.arobase.abstraction.ResultError;

import java.io.IOException;
import java.util.logging.Logger;

public final class ZipDecompressorService implements DecompressorService {

    private static final Logger LOGGER = Logger.getLogger(ZipDecompressorService.class.getName());

    /**
     * Decompress the given file to the given destination.
     *
     * @param source      the file to decompress
     * @param destination the destination where to decompress the file
     */
    @Override
    public Result decompress(String source, String destination) {
        try (final var zipFile = new ZipFile(source)){
            zipFile.extractAll(destination);

            return Result.fromSuccess();
        } catch (final IOException exception) {
            LOGGER.severe(exception.getMessage());

            return Result.fromError(new ResultError(exception.getMessage()));
        }
    }
}
