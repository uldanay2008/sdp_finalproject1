package creational.builder.product;



public record Trip(String destination, java.time.LocalDate departure,
        java.time.LocalDate returnDate, int travelers, boolean hotel, boolean car) {
    public Trip {
        if (destination == null || destination.isBlank()) throw new IllegalArgumentException("Destination required");
        java.util.Objects.requireNonNull(departure, "Departure required");
        java.util.Objects.requireNonNull(returnDate, "Return date required");
        if (!returnDate.isAfter(departure)) throw new IllegalArgumentException("Return must follow departure");
        if (travelers < 1) throw new IllegalArgumentException("At least one traveler required");
        destination = destination.trim();
    }
    public long nights() { return java.time.temporal.ChronoUnit.DAYS.between(departure, returnDate); }
}
