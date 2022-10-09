package org.arobase.processor;

import org.arobase.abstraction.Result;
import org.arobase.service.assignment.AssignmentService;

/****************************************************
 *    Copyright (c) 2022 — Arobase Team
 *<p>
 *    This project may be licensed somewhere,
 *    you may want to check the license file in the GitHub root repository.
 *</p>
 *    All rights reserved to the Arobase team members.
 ****************************************************/

public class AssignmentTestProcessor {

    private final AssignmentService assignmentService;


    public AssignmentTestProcessor() {
        this.assignmentService = new AssignmentService();
    }

    public Result process(final String input) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
