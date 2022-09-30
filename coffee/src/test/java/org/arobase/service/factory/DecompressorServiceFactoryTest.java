package org.arobase.service.factory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.arobase.service.RecurseZipDecompressorService;
import org.arobase.service.ZipDecompressorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/****************************************************
 *    Copyright (c) 2022 — Arobase Team
 *<p>
 *    This project may be licensed somewhere,
 *    you may want to check the license file in the GitHub root repository.
 *</p>
 *    All rights reserved to the Arobase team members.
 ****************************************************/

class DecompressorServiceFactoryTest {

    @Test
    @DisplayName("Should return a ZipDecompressorService if zip is providen")
    void factoryMethodShouldReturnAZipDecompressorService() {
        final var service = DecompressorServiceFactory.getDecompressorService("zip");

        assertThat(service).isInstanceOf(ZipDecompressorService.class);
    }

    @Test
    @DisplayName("Should return a RecurseZipDecompressorService if rzip is providen")
    void factoryMethodShouldReturnARecurseZipDecompressorService() {
        final var service = DecompressorServiceFactory.getDecompressorService("rzip");

        assertThat(service).isInstanceOf(RecurseZipDecompressorService.class);
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException if no service is provided")
    void factoryMethodShouldThrowIllegalArgumentException() {
        assertThatIllegalArgumentException().isThrownBy(
            () -> DecompressorServiceFactory.getDecompressorService("noService"));
    }

}
