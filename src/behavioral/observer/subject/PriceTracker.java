package behavioral.observer.subject;

import behavioral.observer.observer.PriceObserver;

/** Single-threaded price monitor. Initial price, equal prices and increases do not alert. */
public final class PriceTracker implements PriceSubject {
    private final java.util.Map<String, java.math.BigDecimal> prices = new java.util.HashMap<>();
    private final java.util.Map<String, java.util.Set<PriceObserver>> observers = new java.util.HashMap<>();
    private static void validateId(String id) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("Offer ID required");
    }
    @Override public void subscribe(String id, PriceObserver observer) {
        validateId(id);
        observers.computeIfAbsent(id, key -> new java.util.LinkedHashSet<>()).add(java.util.Objects.requireNonNull(observer));
    }
    @Override public void unsubscribe(String id, PriceObserver observer) {
        validateId(id);
        var subscribers = observers.get(id);
        if (subscribers != null) subscribers.remove(observer);
    }
    public void updatePrice(String id, java.math.BigDecimal price) {
        validateId(id);
        java.util.Objects.requireNonNull(price);
        if (price.signum() <= 0) throw new IllegalArgumentException("Price must be positive");
        var old = prices.put(id, price);
        if (old != null && price.compareTo(old) < 0) {
            for (var observer : java.util.List.copyOf(observers.getOrDefault(id, java.util.Set.of())))
                observer.onPriceDrop(id, old, price);
        }
    }
}
