package org.docksidestage.bizfw.basic.buyticket.constants;

public enum TicketPrice {
    ONE_DAY_PRICE(7400),
    TWO_DAY_PRICE(13200),
    FOUR_DAY_PRICE(22400),
    NIGHT_ONLY_TWO_DAY_PRICE(7400);

    private final int price;
    TicketPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
