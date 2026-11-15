package structural.facade.subsystem;
import creational.factorymethod.creator.BookingCreator;
import creational.factorymethod.product.Booking;
import creational.builder.product.Trip;
import structural.adapter.target.FlightOffer;
public final class FlightReservationService implements ReservationService {
    private final BookingCreator creator;
    public FlightReservationService(BookingCreator creator) { this.creator = java.util.Objects.requireNonNull(creator); }
    @Override public Booking reserve(Trip trip, FlightOffer flight) {
        return creator.book(flight.airline() + " " + flight.id(), flight.price());
    }
}
