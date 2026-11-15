package behavioral.strategy.strategy;

import structural.adapter.target.FlightOffer;

public interface OfferStrategy {
    FlightOffer select(java.util.List<FlightOffer> offers);
    static java.util.List<FlightOffer> requireOffers(java.util.List<FlightOffer> offers) {
        java.util.Objects.requireNonNull(offers);
        if (offers.isEmpty()) throw new IllegalArgumentException("No flight offers available");
        return offers;
    }
}
