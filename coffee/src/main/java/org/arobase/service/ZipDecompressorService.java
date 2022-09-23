package org.arobase.service;

import org.arobase.service.abstraction.DecompressionObjectResult;
import org.arobase.service.abstraction.Result;

public final class ZipDecompressorService implements DecompressorService {

	/**
	 * Decompress the given file to the given destination.
	 *
	 * @param source      the file to decompress
	 * @param destination the destination where to decompress the file
	 */
	@Override
	public Result<DecompressionObjectResult> decompress(String source, String destination) {
		try {
			System.out.println("Hello world");
		} catch (Exception e) {
			return Result.from(e);
		}

		return Result.fromFailure();
	}
}
