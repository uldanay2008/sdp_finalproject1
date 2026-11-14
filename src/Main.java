import creational.builder.builder.TripBuilder;
import creational.factorymethod.creator.*;
import structural.adapter.adaptee.LegacyAirlineApi;
import structural.adapter.adapter.AirlineAdapter;
import structural.facade.facade.TravelBookingFacade;
import structural.facade.subsystem.*;
import behavioral.strategy.context.OfferSelector;
import behavioral.strategy.strategy.*;
import behavioral.observer.subject.PriceTracker;
import behavioral.observer.observer.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public final class Main {
    public static void main(String[] args) {
        var trip = new TripBuilder().destination("Almaty")
                .dates(LocalDate.of(2027, 6, 10), LocalDate.of(2027, 6, 15))
                .travelers(2).includeHotel().includeCar().build();
        System.out.println("=== Builder: " + trip + " ===");
        var provider = new AirlineAdapter(new LegacyAirlineApi());
        var offers = provider.search(trip);
        System.out.println("=== Adapter: normalized round-trip offers (USD, all travelers) ===");
        offers.forEach(System.out::println);
        var selector = new OfferSelector(new CheapestOfferStrategy());
        check(selector.select(offers).id().equals("FL-101"), "Cheapest strategy");
        System.out.println("Cheapest: " + selector.select(offers).id());
        selector.setStrategy(new FastestOfferStrategy());
        check(selector.select(offers).id().equals("FL-202"), "Fastest strategy");
        System.out.println("Fastest: " + selector.select(offers).id());
        selector.setStrategy(new BalancedOfferStrategy(new BigDecimal("1.00")));
        check(selector.select(offers).id().equals("FL-303"), "Balanced strategy");
        System.out.println("Balanced: " + selector.select(offers).id());
        selector.setStrategy(new CheapestOfferStrategy());
        var facade = new TravelBookingFacade(provider, selector,
                new FlightReservationService(new FlightBookingCreator()),
                new HotelReservationService(new HotelBookingCreator(), new BigDecimal("80.00")),
                new CarReservationService(new CarBookingCreator(), new BigDecimal("40.00")));
        var receipt = facade.bookTrip(trip);
        System.out.println("=== Facade + Factory Method: reservations ===");
        receipt.bookings().forEach(b -> System.out.printf("%s | %s | %s USD%n",
                b.getClass().getSimpleName(), b.description(), b.price()));
        System.out.println("Total: " + receipt.total() + " USD");
        check(receipt.bookings().size() == 3, "Three bookings");
        check(receipt.total().compareTo(new BigDecimal("1100.00")) == 0, "Total calculation");
        var flightOnly = new TripBuilder().destination("Almaty").dates(trip.departure(), trip.returnDate()).build();
        check(facade.bookTrip(flightOnly).bookings().size() == 1, "Optional services excluded");
        System.out.println("=== Observer: price drop notifications ===");
        var tracker = new PriceTracker();
        var user = new UserPriceObserver("Uldanay");
        int[] alerts = {0};
        PriceObserver counter = (id, oldPrice, newPrice) -> alerts[0]++;
        String watched = selector.select(offers).id();
        tracker.subscribe(watched, user);
        tracker.subscribe(watched, counter);
        tracker.subscribe(watched, counter);
        tracker.updatePrice(watched, new BigDecimal("500.00"));
        tracker.updatePrice(watched, new BigDecimal("450.00"));
        tracker.updatePrice(watched, new BigDecimal("450.00"));
        tracker.updatePrice(watched, new BigDecimal("470.00"));
        tracker.updatePrice("UNRELATED", new BigDecimal("100.00"));
        tracker.updatePrice("UNRELATED", new BigDecimal("90.00"));
        check(alerts[0] == 1, "Only drops on subscribed offer; no duplicate subscription");
        tracker.unsubscribe(watched, counter);
        tracker.unsubscribe(watched, user);
        tracker.updatePrice(watched, new BigDecimal("400.00"));
        check(alerts[0] == 1, "Unsubscribe");
        expectInvalid(() -> new TripBuilder().build());
        expectInvalid(() -> selector.select(java.util.List.of()));
        expectInvalid(() -> tracker.updatePrice(watched, new BigDecimal("-1")));
        System.out.println("All integration checks passed.");
    }
    private static void check(boolean condition, String label) {
        if (!condition) throw new AssertionError(label);
    }
    private static void expectInvalid(Runnable action) {
        try { action.run(); }
        catch (IllegalArgumentException | NullPointerException expected) { return; }
        throw new AssertionError("Expected validation error");
    }
}
