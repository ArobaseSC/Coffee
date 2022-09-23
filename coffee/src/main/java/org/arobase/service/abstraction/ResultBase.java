package org.arobase.service.abstraction;

/****************************************************
 *    Copyright (c) 2022 — Arobase Team
 *
 *    This project may be licensed somewhere,
 *    you may want to check the license file in the GitHub root repository.
 *
 *    All rights reserved to the Arobase team members.
 ****************************************************/

public abstract class ResultBase<T extends ObjectResult> extends Result<T>{

	private final T returnObject;

	protected ResultBase() {
		this.returnObject = null;
	}

	protected ResultBase(final T objectType) {
		this.returnObject = objectType;
	}

}
