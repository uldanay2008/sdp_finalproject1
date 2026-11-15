package structural.adapter.adaptee;
public final class LegacyAirlineApi {
    public record Fare(String code, String carrier, long cents, int minutes) { }
    public java.util.List<Fare> findTickets(String destination, String outbound, String inbound) {
        return java.util.List.of(new Fare("FL-101", "Budget Air", 25000, 420),
                new Fare("FL-202", "Express Air", 39000, 240),
                new Fare("FL-303", "Comfort Air", 30000, 300));
    }
}
