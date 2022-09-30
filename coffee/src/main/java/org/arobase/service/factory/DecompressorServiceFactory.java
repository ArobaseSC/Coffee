package org.arobase.service.factory;

import org.arobase.service.DecompressorService;
import org.arobase.service.RecurseZipDecompressorService;
import org.arobase.service.ZipDecompressorService;

/**
 * Factory that creates a DecompressorService.
 * <p>
 * It is used to create a DecompressorService.
 * It is used to decouple the creation of the DecompressorService from the client.
 * </p>
 */
public class DecompressorServiceFactory {

    /**
     * Creates a DecompressorService.
     *
     * @param decompressorServiceType the type of the DecompressorService to create. ("zip", "rzip")
     *                                <p>Note: prefix with r when you want recursive </p>
     * @return the created DecompressorService
     */
    public static DecompressorService getDecompressorService(final String decompressorServiceType) {
        return switch (decompressorServiceType) {
            case "zip" -> new ZipDecompressorService();
            case "rzip" -> new RecurseZipDecompressorService();
            default -> throw new IllegalArgumentException("Unknown decompressor service type");
        };
    }
}
