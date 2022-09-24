package org.arobase;

import static org.assertj.core.api.Assertions.assertThat;

import org.arobase.abstraction.Result;
import org.arobase.abstraction.ResultError;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/****************************************************
 *    Copyright (c) 2022 — Arobase Team
 *
 *    This project may be licensed somewhere,
 *    you may want to check the license file in the GitHub root repository.
 *
 *    All rights reserved to the Arobase team members.
 ****************************************************/

class ResultTest {

    private Result nullIfIEqualsFive(final int i) {
        if (i == 5) {
            return Result.fromError(new ResultError("i is equal to 5"));
        }

        return Result.fromSuccess();
    }

    @Test
    @DisplayName("Null object should be a failure result")
    void returnResultObjectOfNullShouldBeFailure() {
        // Given
        final var i = 5;

        // When
        final var result = nullIfIEqualsFive(i);

        // Then
        assertThat(Result.isSuccess(result)).isFalse();
    }

    @Test
    @DisplayName("Non Null object should be a success result")
    void returnResultObjectOfNonNullShouldBeSuccess() {
        // Given
        final var i = 2;

        // When
        final var result = nullIfIEqualsFive(i);

        // Then
        assertThat(Result.isSuccess(result)).isTrue();
    }
}
