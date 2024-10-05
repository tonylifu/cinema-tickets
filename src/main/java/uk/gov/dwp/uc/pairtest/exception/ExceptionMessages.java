package uk.gov.dwp.uc.pairtest.exception;

public class ExceptionMessages {
    private ExceptionMessages(){}

    public static final String NULL_TICKET_REQUEST_MESSAGE = "Ticket requests cannot be null.";
    public static final String NULL_TYPE_MESSAGE = "Type cannot be null.";
    public static final String NEGATIVE_NO_OF_TICKET_MESSAGE = "Number of tickets cannot be negative.";
    public static final String INVALID_PURCHASE_NULL_EMPTY_TICKET_MESSAGE =
            "Invalid purchase request. Ticket cannot be null or empty.";
    public static final String INVALID_PURCHASE_TICKET_CONTAINS_EMPTY_MESSAGE =
            "Invalid purchase request. Ticket cannot contain null or empty.";
    public static final String INVALID_PURCHASE_INSUFFICIENT_FUNDS_MESSAGE =
            "Invalid purchase request. Insufficient funds.";
    public static final String INVALID_PURCHASE_AT_LEAST_ONE_ADULT_MESSAGE =
            "Invalid purchase request. At least one adult ticket is required.";
    public static final String INVALID_PURCHASE_MAX_NO_OF_TICKETS_MESSAGE =
            "Invalid purchase request. Maximum allowed number of seats exceeded.";
}
