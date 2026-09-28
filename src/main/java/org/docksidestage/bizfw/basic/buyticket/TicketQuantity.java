package org.docksidestage.bizfw.basic.buyticket;

import java.util.HashMap;

import org.docksidestage.bizfw.basic.buyticket.constants.TicketInfo;

public class TicketQuantity {
    private static HashMap<TicketInfo, Integer> ticketQuantityMap;

    /**
     * newさせないため
     */
    private TicketQuantity(){
    }

    /**
     * チケット残数管理に用いるインスタンスを取得するメソッド
     * @param ticketInfo チケットの情報
     * @return チケットの在庫数
     */
    private static HashMap<TicketInfo, Integer> getInstance(TicketInfo ticketInfo) {
        if(ticketQuantityMap == null){
            ticketQuantityMap = new HashMap<>();
        }
        if(!ticketQuantityMap.containsKey(ticketInfo))
            ticketQuantityMap.put(ticketInfo, ticketInfo.getMaxQuantity()); // 全てのチケットの在庫は２枚スタート
        return ticketQuantityMap;
    }

    /**
     * 在庫数を取得するメソッド
     * @param ticketInfo チケットの情報
     * @return 在庫数
     */
    public static int getQuantity(TicketInfo ticketInfo) {
        HashMap<TicketInfo, Integer> instance = getInstance(ticketInfo);
        return instance.get(ticketInfo);
    }

    /**
     * 在庫数を確認して減らすメソッド
     * @param ticketInfo チケットの情報
     */
    public static void checkAndDecreaseQuantity(TicketInfo ticketInfo) {
        HashMap<TicketInfo, Integer> instance = getInstance(ticketInfo);
        isAvailabilityForSale(ticketInfo);
        instance.replace(ticketInfo, instance.get(ticketInfo) - 1);
    }

    /**
     * チケットの在庫を確認し、販売可能かどうかをチェックする関数.
     *
     * @param ticketInfo 販売可否をチェックするチケットの情報.
     */
    public static void isAvailabilityForSale(TicketInfo ticketInfo) {
        if(getInstance(ticketInfo).get(ticketInfo) <= 0)
            throw new TicketBooth.TicketSoldOutException(ticketInfo + " is sold out");
    }
    //本当は在庫ロックの仕組みがあるとユーザーには親切なのかも
}
