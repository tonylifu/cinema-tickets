package uk.gov.dwp.uc.pairtest.service;

import uk.gov.dwp.uc.pairtest.data.PriceData;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;

public class PayableAmountCalculator implements AmountCalculatorService {
    @Override
    public int getTotalAmountToPay(TicketTypeRequest... ticketTypeRequests) {
        if (ticketTypeRequests == null) {
            throw new IllegalArgumentException("Ticket requests cannot be null");
        }
        int totalAmount = 0;
        for (TicketTypeRequest request : ticketTypeRequests) {
            if (TicketTypeRequest.Type.ADULT == request.getTicketType()) {
                totalAmount = getTotalAmount(request, totalAmount);
            } else if (TicketTypeRequest.Type.CHILD == request.getTicketType()) {
                totalAmount = getTotalAmount(request, totalAmount);
            } else if (TicketTypeRequest.Type.INFANT == request.getTicketType()) {
                totalAmount = getTotalAmount(request, totalAmount);
            }
        }
        return totalAmount;
    }

    private static int getTotalAmount(TicketTypeRequest request, int totalAmount) {
        totalAmount += PriceData.getTicketPrices().get(request.getTicketType().name()) * request.getNoOfTickets();
        return totalAmount;
    }
}
