package uk.gov.dwp.uc.pairtest.data;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;

import java.util.Map;

public class TicketsData {

    public static final int MAX_NO_TICKET_AT_A_TIME = 25;

    private TicketsData(){}

    public static Map<String, Integer> getTicketPrices() {
        return Map.of(
                TicketTypeRequest.Type.INFANT.name(), 0,
                TicketTypeRequest.Type.CHILD.name(), 15,
                TicketTypeRequest.Type.ADULT.name(), 25
        );
    }
}
