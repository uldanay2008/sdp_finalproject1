package creational.factorymethod.creator;

import creational.factorymethod.product.Booking;

public abstract class BookingCreator {
    public final Booking book(String description, java.math.BigDecimal price) {
        if (description == null || description.isBlank()) throw new IllegalArgumentException("Description required");
        java.util.Objects.requireNonNull(price);
        if (price.signum() < 0) throw new IllegalArgumentException("Negative price");
        return createBooking(java.util.UUID.randomUUID().toString(), description, price);
    }
    protected abstract Booking createBooking(String reference, String description, java.math.BigDecimal price);
}
