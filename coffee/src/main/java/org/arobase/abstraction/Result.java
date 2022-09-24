package org.arobase.abstraction;

/****************************************************
 *    Copyright (c) 2022 — Arobase Team
 *
 *    This project may be licensed somewhere,
 *    you may want to check the license file in the GitHub root repository.
 *
 *    All rights reserved to the Arobase team members.
 ****************************************************/

public class Result {

    private final ResultError error;

    private Result(final ResultError error) {
        this.error = error;
    }

    public static Result fromSuccess() {
        return new Result(null);
    }

    public static <T extends ResultError> Result fromError(final T error) {
        return new Result(error);
    }

    public static boolean isSuccess(final Result result) {
        return result.error == null;
    }
}
