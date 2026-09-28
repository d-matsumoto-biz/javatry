package org.docksidestage.bizfw.basic.buyticket.constants;

import java.time.LocalDateTime;

public enum TicketInfo {
    ONE_DAY(7400, 1, 0), //1日券
    TWO_DAY(13200, 2, 0), //2日券
    FOUR_DAY(22400, 4, 0), //4日券
    NIGHT_ONLY_TWO_DAY(7400, 2, 16); //ナイトツーデイ券

    private final int price;
    private final int canUseCount;
    private final int entryStartableTime; //簡単のためにLocalDate等は用いない

    /**
     * チケット情報
     * @param price チケットの価格
     * @param canUseCount 使用できる回数
     * @param entryStartableTime 入場可能時間 (0の場合いつでも入場可能)
     */
    TicketInfo(
            int price,
            int canUseCount,
            int entryStartableTime
    ){
        this.price = price;
        this.canUseCount = canUseCount;
        this.entryStartableTime = entryStartableTime;
    }

    /**
     * チケットの価格を取得する。
     * @return チケットの価格
     */
    public int getPrice(){
        return price;
    }

    /**
     * チケットの使用回数を取得する。
     * @return チケットの使用回数
     */
    public int getCanUseCount(){
        return canUseCount;
    }

    /**
     * チケットの入場可能時間を取得する。
     * @return チケットの入場可能時間 (0の場合いつでも入場可能)
     */
    public int getEntryStartableTime(){
        return entryStartableTime;
    }
}
