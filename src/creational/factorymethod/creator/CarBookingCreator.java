package creational.factorymethod.creator;

import creational.factorymethod.product.*;

public final class CarBookingCreator extends BookingCreator {
    @Override protected Booking createBooking(String reference, String description, java.math.BigDecimal price) {
        return new CarBooking(reference, description, price);
    }
}
