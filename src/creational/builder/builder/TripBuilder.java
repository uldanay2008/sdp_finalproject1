package creational.builder.builder;

import creational.builder.product.Trip;

public final class TripBuilder {
    private String destination;
    private java.time.LocalDate departure;
    private java.time.LocalDate returnDate;
    private int travelers = 1;
    private boolean hotel;
    private boolean car;
    public TripBuilder destination(String value) { destination = value; return this; }
    public TripBuilder dates(java.time.LocalDate start, java.time.LocalDate end) { departure = start; returnDate = end; return this; }
    public TripBuilder travelers(int value) { travelers = value; return this; }
    public TripBuilder includeHotel() { hotel = true; return this; }
    public TripBuilder includeCar() { car = true; return this; }
    public Trip build() { return new Trip(destination, departure, returnDate, travelers, hotel, car); }
}
