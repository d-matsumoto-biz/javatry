package org.docksidestage.bizfw.basic.buyticket;

import org.docksidestage.bizfw.basic.buyticket.constants.TicketType;

public class TicketPurchaseApplication {
    private final int handedMoney;
    private final TicketType ticketType;

    /**
     * チケット購入申請
     * @param handedMoney 払った金額
     * @param ticketInfo チケット情報
     */
    public TicketPurchaseApplication(
            int handedMoney,
            TicketType ticketInfo
    ){
        this.handedMoney = handedMoney;
        this.ticketType = ticketInfo;
    }

    /**
     * 払った金額を取得する。
     * @return 払った金額
     */
    public int getHandedMoney(){
        return handedMoney;
    }

    /**
     * チケットの価格を取得する。
     * @return チケットの価格
     */
    public int getPrice(){
        return this.ticketType.getPrice();
    }

    /**
     * チケット情報を取得する。
     * @return チケット情報
     */
    public TicketType getTicketInfo(){
        return ticketType;
    }
}
