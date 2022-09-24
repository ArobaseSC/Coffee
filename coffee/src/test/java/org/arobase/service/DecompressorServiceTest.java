package org.arobase.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;

/****************************************************
 *    Copyright (c) 2022 — Arobase Team
 *
 *    This project may be licensed somewhere,
 *    you may want to check the license file in the GitHub root repository.
 *
 *    All rights reserved to the Arobase team members.
 ****************************************************/

class DecompressorServiceTest {

    @TempDir
    private File temporaryFolder;

    @Test
    void ensureTemporaryFolderExists() {
        assertThat(temporaryFolder).exists();
    }
}
