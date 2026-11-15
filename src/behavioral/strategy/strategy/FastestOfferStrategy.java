package behavioral.strategy.strategy;

import structural.adapter.target.FlightOffer;

public final class FastestOfferStrategy implements OfferStrategy {
    @Override public FlightOffer select(java.util.List<FlightOffer> offers) {
        return OfferStrategy.requireOffers(offers).stream().min(java.util.Comparator.comparingInt(FlightOffer::durationMinutes).thenComparing(FlightOffer::price)).orElseThrow();
    }
}
