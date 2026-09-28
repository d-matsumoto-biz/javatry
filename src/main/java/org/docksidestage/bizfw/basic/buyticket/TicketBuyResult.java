package org.docksidestage.bizfw.basic.buyticket;

import org.docksidestage.bizfw.basic.buyticket.constants.TicketType;

public class TicketBuyResult {
    private final Ticket ticket;
    private int change;

    public TicketBuyResult(
            int price,
            TicketType ticketType
    ){
        this.ticket = new Ticket(price,ticketType);
    }
    public Ticket getTicket(){
        return ticket;
    }
    public int getChange(){
        return change;
    }
    public void setChange(int change){
        this.change = change;
    }
}
