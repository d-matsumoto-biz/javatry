package org.docksidestage.bizfw.basic.buyticket;

import org.docksidestage.bizfw.basic.buyticket.constants.TicketInfo;

public class TicketBuyResult {
    private final Ticket ticket;
    private int change;

    /**
     * チケット購入結果
     * 購入したチケットの詳細と、お釣りの金額を保持する。
     * @param ticketInfo
     */
    public TicketBuyResult(
            TicketInfo ticketInfo
    ){
        this.ticket = new Ticket(ticketInfo);
    }

    /**
     * 購入したチケットを取得する。
     * @return 購入したチケット
     */
    public Ticket getTicket(){
        return ticket;
    }

    /**
     * お釣りの金額を取得する。
     * @return お釣りの金額
     */
    public int getChange(){
        return change;
    }

    /**
     * お釣りの金額を設定する。
     * @param change お釣りの金額
     */
    public void setChange(int change){
        this.change = change;
    }
}
