package org.arobase.service;

import org.arobase.abstraction.Result;
import org.arobase.service.factory.DecompressorServiceFactory;

import java.io.File;

/**
 * Represents the main recurse abstraction service to decompress something recursively.
 * <p>
 * It should not be use directly but through the {@link DecompressorServiceFactory}.
 * The factory will return the right implementation of this service based on your needs.
 * For example, if you want to decompress a folder of zip files,
 * you should use the {@link RecurseZipDecompressorService}.
 * </p>
 *
 * @author Alexis Chân Gridel
 */
@FunctionalInterface
public interface RecurseDecompressor extends DecompressorService {

    /**
     * Decompress all files inside the given folder to the given destination.
     *
     * @param source      the folder where to find files to decompress
     * @param destination the destination where to decompress the files
     * @return a {@link Result} that will be a success if the decompression succeed,
     * otherwise it will be an error
     */
    Result decompress(final File source, final File destination);
}