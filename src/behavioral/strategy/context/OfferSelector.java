package behavioral.strategy.context;

import behavioral.strategy.strategy.OfferStrategy;
import structural.adapter.target.FlightOffer;

public final class OfferSelector {
    private OfferStrategy strategy;
    public OfferSelector(OfferStrategy strategy) { setStrategy(strategy); }
    public void setStrategy(OfferStrategy strategy) { this.strategy = java.util.Objects.requireNonNull(strategy); }
    public FlightOffer select(java.util.List<FlightOffer> offers) { return strategy.select(offers); }
}
