package org.arobase.service;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;

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
        final var zipFile = new ZipFile(source);

        try {
            zipFile.extractAll(destination);

            return Result.fromSuccess();
        } catch (final ZipException zipException) {
            LOGGER.severe(zipException.getMessage());

            return Result.from(zipException);
        }
    }
}
