# Travel Booking Engine

Java 17+ console project. No external dependencies. Six coordinated design patterns.

## Patterns
| Pattern | Package | Roles |
|---|---|---|
| Builder | creational.builder | TripBuilder builds immutable Trip |
| Factory Method | creational.factorymethod | BookingCreator declares factory method; Flight/Hotel/Car creators produce corresponding Booking products |
| Adapter | structural.adapter | AirlineAdapter converts LegacyAirlineApi fares into FlightProvider/FlightOffer |
| Facade | structural.facade | TravelBookingFacade coordinates reservation subsystems |
| Strategy | behavioral.strategy | OfferSelector switches Cheapest, Fastest, Balanced strategies |
| Observer | behavioral.observer | PriceTracker notifies subscribed PriceObservers on drops |

Only Main is outside the pattern packages. Each public class/interface/record has its own file.
The Builder is fluent; no Director is necessary. Factory Method is an overridden method in an abstract creator, not a switch-based simple factory.

## Scenario and price rules
Two travelers visit Almaty for five nights. Airline fares are round-trip USD per traveler; the adapter multiplies by traveler count. Hotel rate is USD 80 per night for one room accommodating the party. Car rate is USD 40 per rental day; this demo equates rental days with nights. Cheapest flights cost USD 500, hotel USD 400, car USD 200: total USD 1100.
Balanced strategy minimizes total flight price + duration minutes * configured USD/minute. This is a user preference score, not a payment surcharge. All providers use deterministic mock data; no network, real bookings, payment, persistence, availability checks or transactional rollback. Fixed demo dates are intentional.
PriceTracker monitors total flight fare snapshots for the selected offer ID. Feeding prices is manual in Main; the monitor is not a live airline feed and does not modify existing reservations. Console notifications simulate user alerts.

## Verification
Main performs executable integration checks: all selection strategies, full trip total, optional service exclusion, price drop filtering, per-offer subscriptions, duplicate prevention, unsubscribe, and invalid input. Failed checks throw AssertionError without requiring `-ea`.

## Design principles
- SRP: building, offer conversion, selection, reservations and notifications have separate roles.
- OCP: add a strategy or FlightProvider without modifying the selector/facade.
- LSP: concrete creators and strategies honor their abstract contracts.
- ISP: small Booking, FlightProvider, OfferStrategy and PriceObserver interfaces.
- DIP: facade depends on FlightProvider and ReservationService interfaces; services depend on BookingCreator abstraction. Main assembles dependencies.
- KISS/DRY: one console entry point, reusable creator workflow, immutable values, shared validation for empty offer lists, BigDecimal for prices.

## Team Git workflow
Each member must implement/review their own assigned work and commit under their own Git identity. Do not fabricate commits or contributions. Suggested ownership: member 1 Builder + Factory Method; member 2 Adapter + Facade; member 3 Strategy + Observer. Agree shared contracts first.

