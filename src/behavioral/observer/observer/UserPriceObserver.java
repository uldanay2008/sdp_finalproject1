package behavioral.observer.observer;



public final class UserPriceObserver implements PriceObserver {
    private final String name;
    public UserPriceObserver(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("User name required");
        this.name = name;
    }
    @Override public void onPriceDrop(String id, java.math.BigDecimal oldPrice, java.math.BigDecimal newPrice) {
        System.out.printf("Alert for %s: %s dropped from %s to %s USD%n", name, id, oldPrice, newPrice);
    }
}
