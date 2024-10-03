package uk.gov.dwp.uc.pairtest.domain;

import lombok.Getter;

/**
 * Represents a request for a specific type and number of tickets.
 * This class is immutable, meaning once an instance is created, it cannot be modified.
 */
public final class TicketTypeRequest {

    /**
     * The number of tickets requested.
     * -- GETTER --
     *  Returns the number of tickets requested.
     *
     * @return the number of tickets

     */
    @Getter
    private final int noOfTickets;

    /**
     * The type of ticket requested (ADULT, CHILD, INFANT).
     */
    private final Type type;

    /**
     * Constructs a {@code TicketTypeRequest} with the specified ticket type and number of tickets.
     *
     * @param type the type of the ticket (must not be null)
     * @param noOfTickets the number of tickets (must be non-negative)
     * @throws IllegalArgumentException if {@code type} is null or {@code noOfTickets} is negative
     */
    public TicketTypeRequest(Type type, int noOfTickets) {
        if (type == null) {
            throw new IllegalArgumentException("Type cannot be null");
        }
        if (noOfTickets < 0) {
            throw new IllegalArgumentException("Number of tickets cannot be negative");
        }
        this.type = type;
        this.noOfTickets = noOfTickets;
    }

    /**
     * Returns the type of the ticket.
     *
     * @return the ticket type
     */
    public Type getTicketType() {
        return type;
    }

    /**
     * Enumeration representing the types of tickets.
     */
    public enum Type {
        /**
         * Represents an adult ticket.
         */
        ADULT,

        /**
         * Represents a child ticket.
         */
        CHILD,

        /**
         * Represents an infant ticket.
         */
        INFANT
    }
}
