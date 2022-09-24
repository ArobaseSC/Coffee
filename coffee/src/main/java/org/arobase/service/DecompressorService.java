package org.arobase.service;

import org.arobase.abstraction.Result;
import org.arobase.service.factory.DecompressorServiceFactory;

import java.io.File;

/**
 * Represents the main abstraction service to decompress something.
 * <p>
 * It should not be use directly but through the {@link DecompressorServiceFactory}.
 * The factory will return the right implementation of this service based on your needs.
 * For example, if you want to decompress a zip file,
 * you should use the {@link ZipDecompressorService}.
 * </p>
 *
 * @author Alexis Chân Gridel
 */
@FunctionalInterface
public interface DecompressorService {

    /**
     * Decompress the given file to the given destination.
     *
     * @param source      the file to decompress
     * @param destination the destination where to decompress the file
     * @return a {@link Result} that will be a success if the decompression succeed,
     * otherwise it will be an error
     */
    Result decompress(final File source, final File destination);
}
