package org.docksidestage.bizfw.basic.buyticket;

public class TicketPurchaseApplication {
    private final int handedMoney;
    private final int price;
    private final TicketType ticketType;
    public TicketPurchaseApplication(
            int handedMoney,
            int price,
            TicketType ticketType
    ){
        this.handedMoney = handedMoney;
        this.price = price;
        this.ticketType = ticketType;
    }
    public int getHandedMoney(){
        return handedMoney;
    }

    public int getPrice(){
        return price;
    }

    public TicketType getTicketType(){
        return ticketType;
    }
}
