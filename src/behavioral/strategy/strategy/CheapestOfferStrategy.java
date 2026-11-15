package behavioral.strategy.strategy;

import structural.adapter.target.FlightOffer;

public final class CheapestOfferStrategy implements OfferStrategy {
    @Override public FlightOffer select(java.util.List<FlightOffer> offers) {
        return OfferStrategy.requireOffers(offers).stream().min(java.util.Comparator.comparing(FlightOffer::price).thenComparingInt(FlightOffer::durationMinutes)).orElseThrow();
    }
}
