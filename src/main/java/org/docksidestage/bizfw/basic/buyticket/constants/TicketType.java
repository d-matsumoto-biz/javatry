package org.docksidestage.bizfw.basic.buyticket.constants;

// #1on1: クラス名の選択肢、TicketType or TicketType (2026/10/14)
// TicketInfoは比較的幅広い、TicketTypeは種類を示すもうちょい具体的。
// $内容が変わらないので、TicketTypeの方が実態に合ってるかも。
// 確かに、enumだから定数的なものしか入ってないので、Infoだと動的な情報も想像しちゃうかも。

// TODO done matsumoto こちらもJavaDocをお願いします by jflute (2026/10/14)

/**
 * チケットの種類とチケットに関する情報
 */
public enum TicketType {
    /**
     * 1日券
     */
    ONE_DAY(
            7400,
            1,
            0,
            10
    ),

    /**
     * 2日券
     */
    TWO_DAY(
            13200,
            2,
            0,
            10
    ),

    /**
     * 4日券
     */
    FOUR_DAY(
            22400,
            4,
            0,
            10
    ),

    /**
     * ナイトツーデイ券
     */
    NIGHT_ONLY_TWO_DAY(
            7400,
            2,
            16,
            2
    );

    private final int price;
    private final int canUseCount;
    // TODO done matsumoto Timeだと少々曖昧なので、Hourとかにすると正確に表現できる by jflute (2026/10/14)
    private final int entryStartableHour; //簡単のためにLocalDate等は用いない
    private final int maxQuantity;

    /**
     * チケット情報
     * @param price チケットの価格
     * @param canUseCount 使用できる回数
     * @param entryStartableHour 入場可能時間 (0の場合いつでも入場可能)
     */
    TicketType(
            int price,
            int canUseCount,
            int entryStartableHour,
            int maxQuantity
    ){
        this.price = price;
        this.canUseCount = canUseCount;
        this.entryStartableHour = entryStartableHour;
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
    public int getEntryStartableHour(){
        return entryStartableHour;
    }

    /**
     * 最大購入可能枚数を取得する。
     * @return 最大購入可能枚数
     */
    public int getMaxQuantity() {
        return maxQuantity;
    }
}
