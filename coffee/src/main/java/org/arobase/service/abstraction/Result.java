package org.arobase.service.abstraction;

/****************************************************
 *    Copyright (c) 2022 — Arobase Team
 *
 *    This project may be licensed somewhere,
 *    you may want to check the license file in the GitHub root repository.
 *
 *    All rights reserved to the Arobase team members.
 ****************************************************/

public abstract class Result<T> {

	public static <T> ResultBase from(final T returnObject) {
		return returnObject == null ? new FailureResult<>(returnObject)
			: new SuccessResult<>(returnObject);
	}

	public static FailureResult from(final Exception exception) {
		return new FailureResult(exception);
	}

	public static FailureResult fromFailure() {
		return new FailureResult<>();
	}

	public static SuccessResult fromSuccess() {
		return new SuccessResult<>();
	}
}
