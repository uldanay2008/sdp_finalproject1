package creational.factorymethod.product;



public record FlightBooking(String reference, String description, java.math.BigDecimal price) implements Booking {
    public FlightBooking {
        if (reference == null || reference.isBlank() || description == null || description.isBlank())
            throw new IllegalArgumentException("Booking details required");
        java.util.Objects.requireNonNull(price);
        if (price.signum() < 0) throw new IllegalArgumentException("Price cannot be negative");
    }
}
