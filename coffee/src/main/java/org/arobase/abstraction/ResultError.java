package org.arobase.abstraction;

/**
 * Represents a result that is an error.
 * <p>
 * It should be used in a Result context to represent an error.
 */
public class ResultError {

    private final String message;

    /**
     * Creates a new ResultError.
     *
     * @param resultErrorMessage the error message
     */
    public ResultError(final String resultErrorMessage) {
        this.message = resultErrorMessage;
    }

    /**
     * Returns the error message.
     *
     * @return the error message
     */
    public String message() {
        return this.message;
    }
}
