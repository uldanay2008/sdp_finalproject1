package structural.facade.subsystem;
import creational.builder.product.Trip;
import structural.adapter.target.FlightOffer;
import creational.factorymethod.product.Booking;
public interface ReservationService {
    Booking reserve(Trip trip, FlightOffer flight);
}
