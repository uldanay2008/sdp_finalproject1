package structural.facade.subsystem;

import creational.factorymethod.creator.BookingCreator;
import creational.factorymethod.product.Booking;
import creational.builder.product.Trip;
import structural.adapter.target.FlightOffer;

public final class CarReservationService implements ReservationService {
    private final BookingCreator creator;
    private final java.math.BigDecimal rate;
    public CarReservationService(BookingCreator creator, java.math.BigDecimal rate) {
        this.creator = java.util.Objects.requireNonNull(creator);
        this.rate = java.util.Objects.requireNonNull(rate);
        if (rate.signum() <= 0) throw new IllegalArgumentException("Rate must be positive");
    }
    @Override public Booking reserve(Trip trip, FlightOffer flight) {
        return creator.book("Car in " + trip.destination(), rate.multiply(java.math.BigDecimal.valueOf(trip.nights())));
    }
}
