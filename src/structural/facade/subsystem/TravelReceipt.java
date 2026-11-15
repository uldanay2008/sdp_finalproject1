package structural.facade.subsystem;

import creational.factorymethod.product.Booking;

public record TravelReceipt(java.util.List<Booking> bookings) {
    public TravelReceipt { bookings = java.util.List.copyOf(bookings); }
    public java.math.BigDecimal total() {
        return bookings.stream().map(Booking::price).reduce(java.math.BigDecimal.ZERO, java.math.BigDecimal::add);
    }
}
