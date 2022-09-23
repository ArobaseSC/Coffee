package org.arobase;

import static org.assertj.core.api.Assertions.assertThat;

import org.arobase.service.abstraction.FailureResult;
import org.arobase.service.abstraction.Result;
import org.arobase.service.abstraction.SuccessResult;
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

	private Integer nullIfIEqualsFive(final int i) {
		if (i == 5) {
			return null;
		}

		return i;
	}

	@Test
	@DisplayName("Null object should be a failure result")
	void returnResultObjectOfNullShouldBeFailure() {
		// Given
		final var i = 5;

		// When
		final var result = Result.from(nullIfIEqualsFive(i));

		// Then
		assertThat(result).isInstanceOf(FailureResult.class);
	}

	@Test
	@DisplayName("Non Null object should be a success result")
	void returnResultObjectOfNonNullShouldBeSuccess() {
		// Given
		final var i = 2;

		// When
		final var result = Result.from(nullIfIEqualsFive(i));

		// Then
		assertThat(result).isInstanceOf(SuccessResult.class);
	}
}
