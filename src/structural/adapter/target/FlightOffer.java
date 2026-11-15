package structural.adapter.target;
public record FlightOffer(String id, String airline, java.math.BigDecimal price, int durationMinutes) {
    public FlightOffer {
        if (id == null || id.isBlank() || airline == null || airline.isBlank()) throw new IllegalArgumentException("Flight identity required");
        java.util.Objects.requireNonNull(price);
        if (price.signum() <= 0 || durationMinutes <= 0) throw new IllegalArgumentException("Invalid flight offer");
    }
}
