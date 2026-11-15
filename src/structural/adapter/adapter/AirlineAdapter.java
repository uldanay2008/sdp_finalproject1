package structural.adapter.adapter;
import structural.adapter.target.*;
import structural.adapter.adaptee.LegacyAirlineApi;
import creational.builder.product.Trip;
public final class AirlineAdapter implements FlightProvider {
    private final LegacyAirlineApi api;
    public AirlineAdapter(LegacyAirlineApi api) { this.api = java.util.Objects.requireNonNull(api); }
    @Override public java.util.List<FlightOffer> search(Trip trip) {
        return api.findTickets(trip.destination(), trip.departure().toString(), trip.returnDate().toString())
                .stream().map(f -> new FlightOffer(f.code(), f.carrier(),
                        java.math.BigDecimal.valueOf(f.cents(), 2).multiply(java.math.BigDecimal.valueOf(trip.travelers())),
                        f.minutes())).toList();
    }
}
