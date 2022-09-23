package org.arobase.service.abstraction;

/****************************************************
 *    Copyright (c) 2022 — Arobase Team
 *
 *    This project may be licensed somewhere,
 *    you may want to check the license file in the GitHub root repository.
 *
 *    All rights reserved to the Arobase team members.
 ****************************************************/

public class ResultError {

    private final String message;

    public ResultError(final String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}
