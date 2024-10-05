package uk.gov.dwp.uc.pairtest.service;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;

/**
 * The {@code SeatsCalculatorService} interface provides a method to calculate
 * the number of seats required for the given ticket type requests.
 */
public interface SeatsCalculatorService {

    /**
     * Calculates the total number of seats that should be allocated based on the provided
     * {@link TicketTypeRequest} instances.
     *
     * @param ticketTypeRequests the array of {@link TicketTypeRequest} objects which represent
     *                           the types of tickets (e.g., adult, child) being requested.
     *                           This parameter must not be null or empty.
     * @return the total number of seats to allocate based on the ticket types and quantities
     *         in the provided requests.
     * @throws IllegalArgumentException if {@code ticketTypeRequests} is null or contains null elements.
     */
    int getNumberOfSeatsToAllocate(TicketTypeRequest... ticketTypeRequests);
}

