package creational.factorymethod.creator;

import creational.factorymethod.product.*;

public final class FlightBookingCreator extends BookingCreator {
    @Override protected Booking createBooking(String reference, String description, java.math.BigDecimal price) {
        return new FlightBooking(reference, description, price);
    }
}
