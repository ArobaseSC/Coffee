package org.arobase.service.abstraction;

/****************************************************
 *    Copyright (c) 2022 — Arobase Team
 *
 *    This project may be licensed somewhere,
 *    you may want to check the license file in the GitHub root repository.
 *
 *    All rights reserved to the Arobase team members.
 ****************************************************/

public class SuccessResult<T> extends ResultBase<T> {

	protected SuccessResult() {
		super();
	}

	protected SuccessResult(T objectType) {
		super(objectType);
	}
}
