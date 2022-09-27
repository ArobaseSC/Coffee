package org.arobase.service;

import net.lingala.zip4j.ZipFile;
import org.arobase.abstraction.Result;
import org.arobase.abstraction.ResultError;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

public class RecurseZipDecompressorService implements RecurseDecompressor {

    private static final Logger LOGGER = Logger.getLogger(RecurseZipDecompressorService.class.getName());

    /**
     * Decompress the given directory to the given destination.
     *
     * @param source      the directory to decompress
     * @param destination the destination where to decompress the file
     */
    @Override
    public Result decompress(File source, File destination) {
        ZipDecompressorService zipDecompressorService = new ZipDecompressorService();
        for (File file : Objects.requireNonNull(source.listFiles())) {
            zipDecompressorService.decompress(file, destination);
        }
        return Result.fromSuccess();
    }

}
