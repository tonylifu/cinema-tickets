package uk.gov.dwp.uc.pairtest.service.validation;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;

/**
 * The {@code ValidationService} interface provides a method to validate whether an
 * adult ticket is present in the provided ticket type requests.
 */
public interface ValidationService {

    /**
     * Checks whether any of the provided {@link TicketTypeRequest} instances represents
     * an adult ticket.
     *
     * @param ticketTypeRequests the array of {@link TicketTypeRequest} objects representing
     *                           the types of tickets (e.g., adult, child) being requested.
     *                           This parameter must not be null or empty.
     * @return {@code true} if there is at least one adult ticket in the request,
     *         {@code false} otherwise.
     * @throws IllegalArgumentException if {@code ticketTypeRequests} is null or contains null elements.
     */
    boolean isAdultInTicketsRequest(TicketTypeRequest... ticketTypeRequests);
}

