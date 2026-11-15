package behavioral.strategy.strategy;

import structural.adapter.target.FlightOffer;

/** Score = total USD price + duration in minutes * user-defined USD/minute. */
public final class BalancedOfferStrategy implements OfferStrategy {
    private final java.math.BigDecimal timeValue;
    public BalancedOfferStrategy(java.math.BigDecimal timeValue) {
        this.timeValue = java.util.Objects.requireNonNull(timeValue);
        if (timeValue.signum() < 0) throw new IllegalArgumentException("Time value cannot be negative");
    }
    private java.math.BigDecimal score(FlightOffer offer) {
        return offer.price().add(timeValue.multiply(java.math.BigDecimal.valueOf(offer.durationMinutes())));
    }
    @Override public FlightOffer select(java.util.List<FlightOffer> offers) {
        return OfferStrategy.requireOffers(offers).stream().min(java.util.Comparator.comparing(this::score)
                .thenComparing(FlightOffer::id)).orElseThrow();
    }
}
