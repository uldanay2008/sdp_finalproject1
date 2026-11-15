package behavioral.observer.observer;



@FunctionalInterface
public interface PriceObserver {
    void onPriceDrop(String offerId, java.math.BigDecimal oldPrice, java.math.BigDecimal newPrice);
}
