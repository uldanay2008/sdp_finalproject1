package structural.facade.facade;

import creational.builder.product.Trip;
import structural.adapter.target.*;
import behavioral.strategy.context.OfferSelector;
import structural.facade.subsystem.*;

/** Coordinates simulated reservations; no real payments or external bookings. */
public final class TravelBookingFacade {
    private final FlightProvider provider;
    private final OfferSelector selector;
    private final ReservationService flights;
    private final ReservationService hotels;
    private final ReservationService cars;
    public TravelBookingFacade(FlightProvider provider, OfferSelector selector,
            ReservationService flights, ReservationService hotels, ReservationService cars) {
        this.provider = java.util.Objects.requireNonNull(provider);
        this.selector = java.util.Objects.requireNonNull(selector);
        this.flights = java.util.Objects.requireNonNull(flights);
        this.hotels = java.util.Objects.requireNonNull(hotels);
        this.cars = java.util.Objects.requireNonNull(cars);
    }
    public TravelReceipt bookTrip(Trip trip) {
        java.util.Objects.requireNonNull(trip);
        FlightOffer offer = selector.select(provider.search(trip));
        var bookings = new java.util.ArrayList<creational.factorymethod.product.Booking>();
        bookings.add(flights.reserve(trip, offer));
        if (trip.hotel()) bookings.add(hotels.reserve(trip, offer));
        if (trip.car()) bookings.add(cars.reserve(trip, offer));
        return new TravelReceipt(bookings);
    }
}
