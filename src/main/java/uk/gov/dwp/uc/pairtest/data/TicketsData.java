package uk.gov.dwp.uc.pairtest.data;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;

import java.util.Map;

/**
 * The {@code TicketsData} class provides static data related to ticket pricing
 * and limits on the number of tickets that can be purchased at a time.
 * This class cannot be instantiated.
 */
public class TicketsData {

    /**
     * The maximum number of tickets that can be purchased at one time.
     */
    public static final int MAX_NO_TICKET_AT_A_TIME = 25;

    /**
     * Private constructor to prevent instantiation of this class.
     */
    private TicketsData() {}

    /**
     * Returns a map of ticket prices for different types of tickets.
     *
     * @return a {@code Map} where the key is the ticket type as a {@code String},
     * and the value is the price of the ticket as an {@code Integer}.
     */
    public static Map<String, Integer> getTicketPrices() {
        return Map.of(
                TicketTypeRequest.Type.INFANT.name(), 0,
                TicketTypeRequest.Type.CHILD.name(), 15,
                TicketTypeRequest.Type.ADULT.name(), 25
        );
    }
}

