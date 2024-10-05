package uk.gov.dwp.uc.pairtest.exception;

/**
 * Exception thrown when a ticket purchase request is deemed invalid.
 * <p>
 * This could be due to reasons such as invalid account ID,
 * insufficient ticket quantities, or invalid ticket types.
 * </p>
 */
public class InvalidPurchaseException extends RuntimeException {

    /**
     * Constructs a new InvalidPurchaseException with the specified detail message.
     *
     * @param message the detail message, which provides information on why the purchase is invalid
     */
    public InvalidPurchaseException(String message) {
        super(message);
    }

    /**
     * Constructs a new InvalidPurchaseException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause the cause of the exception (A null value is permitted, and indicates that the cause is nonexistent or unknown)
     */
    public InvalidPurchaseException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new InvalidPurchaseException with the specified cause and a detail message
     * of (cause == null ? null : cause.toString()).
     *
     * @param cause the cause of the exception (A null value is permitted, and indicates that the cause is nonexistent or unknown)
     */
    public InvalidPurchaseException(Throwable cause) {
        super(cause);
    }

    /**
     * This constructor is primarily used when the exception is intended to be serialized.
     * It includes control over suppression and stack trace writing.
     *
     * @param message the detail message
     * @param cause the cause of the exception
     * @param enableSuppression whether or not suppression is enabled or disabled
     * @param writableStackTrace whether or not the stack trace should be writable
     */
    public InvalidPurchaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

