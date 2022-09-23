package org.arobase.service.abstraction;

/****************************************************
 *    Copyright (c) 2022 — Arobase Team
 *
 *    This project may be licensed somewhere,
 *    you may want to check the license file in the GitHub root repository.
 *
 *    All rights reserved to the Arobase team members.
 ****************************************************/

public class FailureResult<T> extends ResultBase<T> {

	protected FailureResult() {
		super();
	}

	protected FailureResult(T objectType) {
		super(objectType);
	}

}
