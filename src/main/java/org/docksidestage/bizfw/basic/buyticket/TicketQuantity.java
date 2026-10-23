package org.docksidestage.bizfw.basic.buyticket;

import java.util.HashMap;

import org.docksidestage.bizfw.basic.buyticket.constants.TicketType;

public class TicketQuantity {
    private static HashMap<TicketType, Integer> ticketQuantityMap;

    /**
     * newさせないため
     */
    private TicketQuantity(){
    }

    /**
     * チケット残数管理に用いるインスタンスを取得するメソッド
     * @param ticketType チケットの情報
     * @return チケットの在庫数
     */
    private static HashMap<TicketType, Integer> getInstance(TicketType ticketType) {
        if(ticketQuantityMap == null){
            ticketQuantityMap = new HashMap<>();
        }
        if(!ticketQuantityMap.containsKey(ticketType))
            ticketQuantityMap.put(ticketType, ticketType.getMaxQuantity()); // 全てのチケットの在庫は２枚スタート
        return ticketQuantityMap;
    }

    /**
     * 在庫数を取得するメソッド
     * @param ticketType チケットの情報
     * @return 在庫数
     */
    public static int getQuantity(TicketType ticketType) {
        HashMap<TicketType, Integer> instance = getInstance(ticketType);
        return instance.get(ticketType);
    }

    /**
     * 在庫数を確認して減らすメソッド
     * @param ticketType チケットの情報
     */
    public static void checkAndDecreaseQuantity(TicketType ticketType) {
        HashMap<TicketType, Integer> instance = getInstance(ticketType);
        isAvailabilityForSale(ticketType);
        instance.replace(ticketType, instance.get(ticketType) - 1);
    }

    /**
     * チケットの在庫を確認し、販売可能かどうかをチェックする関数.
     *
     * @param ticketType 販売可否をチェックするチケットの情報.
     */
    public static void isAvailabilityForSale(TicketType ticketType) {
        if(getInstance(ticketType).get(ticketType) <= 0)
            throw new TicketBooth.TicketSoldOutException(ticketType + " is sold out");
    }
    //本当は在庫ロックの仕組みがあるとユーザーには親切なのかも
}
