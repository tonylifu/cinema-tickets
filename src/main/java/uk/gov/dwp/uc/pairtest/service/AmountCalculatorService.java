package uk.gov.dwp.uc.pairtest.service;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;

/**
 * The {@code AmountCalculatorService} interface provides a method to calculate
 * the total amount to pay based on the provided ticket type requests.
 */
public interface AmountCalculatorService {

    /**
     * Calculates the total amount that needs to be paid based on the provided
     * {@link TicketTypeRequest} instances.
     *
     * @param ticketTypeRequests the array of {@link TicketTypeRequest} objects which represent
     *                           the types of tickets (e.g., adult, child) being requested.
     *                           This parameter must not be null or empty.
     * @return the total amount to be paid based on the ticket types and quantities
     *         in the provided requests.
     * @throws IllegalArgumentException if {@code ticketTypeRequests} is null or contains null elements.
     */
    int getTotalAmountToPay(TicketTypeRequest... ticketTypeRequests);
}

