package org.arobase.abstraction;

import java.util.Optional;

/**
 * Represents a Result that can be either a success or an error.
 * <p>
 * It is a generic type that can be used to represent any kind of result.
 * It is used to represent the result of a computation that can be either a success or an error.
 * </p>
 */
public final class Result {

    private final ResultError error;

    private Result(final ResultError resultError) {
        this.error = resultError;
    }

    /**
     * Creates a new Result that is a success.
     *
     * @return the result as success (no error)
     */
    public static Result fromSuccess() {
        return new Result(null);
    }

    /**
     * Creates a new Result that is an error.
     *
     * @param error the result error type
     * @param <T>   the type of the result error
     * @return the result as error
     */
    public static <T extends ResultError> Result fromError(final T error) {
        return new Result(error);
    }

    /**
     * Create a Result from an exception.
     *
     * @param exception the exception
     * @return the result as error
     */
    public static Result fromException(final Exception exception) {
        return new Result(new ResultError(exception.getMessage()));
    }

    /**
     * Returns a Result based on the given boolean.
     * <p>
     * If the boolean is true, the result is a success.
     * If the boolean is false, the result is an error.
     * If the boolean is null, the result is null.
     * </p>
     *
     * @param result       the boolean
     * @param errorMessage the error message
     * @return the result
     */
    public static Result fromBoolean(final Boolean result, final String errorMessage) {
        if (null == result) {
            return fromError(new ResultError("The result is null"));
        }

        return result.booleanValue() ? fromSuccess()
            : fromError(new ResultError("The result is false"));
    }

    /**
     * Returns the result error associated with this result.
     *
     * @return the result error
     */
    public Optional<ResultError> getError() {
        return Optional.ofNullable(this.error);
    }

    /**
     * Returns true if the result is a success.
     *
     * @return true if the result is a success, false otherwise
     */
    public boolean success() {
        return null == this.error;
    }
}
