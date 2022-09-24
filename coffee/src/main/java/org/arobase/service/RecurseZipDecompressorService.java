package org.arobase.service;

import org.arobase.abstraction.Result;

import java.io.File;

/**
 *
 */
public class RecurseZipDecompressorService implements RecurseDecompressor {

    /**
     * Decompress all files inside the given folder to the given destination.
     *
     * @param source      the folder where to find files to decompress
     * @param destination the destination where to decompress the files
     * @return a {@link Result} that will be a success if the decompression succeed,
     * otherwise it will be an error
     */
    @Override
    public Result decompress(final File source, final File destination) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
