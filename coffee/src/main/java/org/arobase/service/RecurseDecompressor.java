package org.arobase.service;

/****************************************************
 *    Copyright (c) 2022 — Arobase Team
 *
 *    This project may be licensed somewhere,
 *    you may want to check the license file in the GitHub root repository.
 *
 *    All rights reserved to the Arobase team members.
 ****************************************************/

import org.arobase.abstraction.Result;
import org.arobase.service.factory.DecompressorServiceFactory;

/**
 * Represents the main recurse abstraction service to decompress something recursively.
 * <p>
 * It should not be use directly but through the {@link DecompressorServiceFactory}.
 * The factory will return the right implementation of this service based on your needs.
 * For example, if you want to decompress a folder of zip files, you should use the {@link RecurseZipDecompressorService}.
 * </p>
 *
 * @author Alexis Chân Gridel
 */
public interface RecurseDecompressor extends DecompressorService {

	/**
     * Decompress all files inside the given folder to the given destination.
     *
     * @param source      the folder where to find files to decompress
     * @param destination the destination where to decompress the files
     */
	Result decompress(final String source, final String destination);
}