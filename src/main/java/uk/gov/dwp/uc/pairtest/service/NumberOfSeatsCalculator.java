package uk.gov.dwp.uc.pairtest.service;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;

import java.util.stream.Stream;

public class NumberOfSeatsCalculator implements SeatsCalculatorService {
    @Override
    public int getNumberOfSeatsToAllocate(TicketTypeRequest... ticketTypeRequests) {
        if (ticketTypeRequests == null) {
            throw new IllegalArgumentException("Ticket requests cannot be null");
        }
        return Stream.of(ticketTypeRequests)
                .filter(request -> request.getTicketType() == TicketTypeRequest.Type.ADULT
                        || request.getTicketType() == TicketTypeRequest.Type.CHILD)
                .mapToInt(TicketTypeRequest::getNoOfTickets)
                .sum();
    }
}
