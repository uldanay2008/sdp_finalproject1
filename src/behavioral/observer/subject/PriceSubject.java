package behavioral.observer.subject;



public interface PriceSubject {
    void subscribe(String offerId, behavioral.observer.observer.PriceObserver observer);
    void unsubscribe(String offerId, behavioral.observer.observer.PriceObserver observer);
}
