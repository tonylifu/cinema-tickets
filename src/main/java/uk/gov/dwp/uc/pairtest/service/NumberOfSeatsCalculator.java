package uk.gov.dwp.uc.pairtest.service;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;

public class NumberOfSeatsCalculator implements SeatsCalculatorService {
    @Override
    public int getNumberOfSeatsToAllocate(TicketTypeRequest... ticketTypeRequests) {
        int totalSeats = 0;
        for (TicketTypeRequest request : ticketTypeRequests) {
            if (request.getTicketType() == TicketTypeRequest.Type.ADULT ||
                    request.getTicketType() == TicketTypeRequest.Type.CHILD) {
                totalSeats += request.getNoOfTickets();
            }
        }
        return totalSeats;
    }
}
