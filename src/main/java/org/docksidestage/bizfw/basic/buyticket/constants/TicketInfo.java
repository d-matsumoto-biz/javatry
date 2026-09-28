package org.docksidestage.bizfw.basic.buyticket.constants;

public enum TicketInfo {
    //1日券
    ONE_DAY(
            7400,
            1,
            0,
            10
    ),

    //2日券
    TWO_DAY(
            13200,
            2,
            0,
            10
    ),

    //4日券
    FOUR_DAY(
            22400,
            4,
            0,
            10
    ),

    //ナイトツーデイ券
    NIGHT_ONLY_TWO_DAY(
            7400,
            2,
            16,
            2
    );

    private final int price;
    private final int canUseCount;
    private final int entryStartableTime; //簡単のためにLocalDate等は用いない
    private final int maxQuantity;

    /**
     * チケット情報
     * @param price チケットの価格
     * @param canUseCount 使用できる回数
     * @param entryStartableTime 入場可能時間 (0の場合いつでも入場可能)
     */
    TicketInfo(
            int price,
            int canUseCount,
            int entryStartableTime,
            int maxQuantity
    ){
        this.price = price;
        this.canUseCount = canUseCount;
        this.entryStartableTime = entryStartableTime;
        this.maxQuantity = maxQuantity;
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

    /**
     * 最大購入可能枚数を取得する。
     * @return 最大購入可能枚数
     */
    public int getMaxQuantity() {
        return maxQuantity;
    }
}
