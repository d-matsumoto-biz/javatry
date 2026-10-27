package org.docksidestage.bizfw.basic.buyticket;

import java.util.HashMap;

import org.docksidestage.bizfw.basic.buyticket.constants.TicketType;

// #1on1: $Boothが何個あっても、共通の在庫なので、staticでシングルトンになっている (2026/10/27)
// そういう意図がしっかりしているのGood。
// #1on1: Booth共有の在庫になったのであれば、マルチスレッド対応を意識したいところ (2026/10/27)
// o TicketQuantityのロジック的なマルチスレッド対応
// o 内部で使っているMapのマルチスレッド対応
// ここは思考トレーニングのみで。
// checkAndDecreaseQuantity()のところで、実装イメージも追ってみた。

// TODO matsumoto javadocお願い by jflute (2026/10/27)
public class TicketQuantity {
    // TODO matsumoto 具象クラスで扱うのではなく、Map interfaceで取り扱おう by jflute (2026/10/27)
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

    // TODO matsumoto isだと判定感もあるけどboolean感が強いので... by jflute (2026/10/27)
    // もうちょいthrowしてる感のある名前を付けましょう。
    // e.g. checkSaleAvailable(), checkSoldOut(), (checkQuantity())
    //      assertQuantityExists()
    //
    // $checkSoldOut()
    //
    // check: boolean戻しもいける、目的語にどっちを取ってもよい → 汎用的
    //        (落とすかどうかも少し曖昧)
    //        (状況によっては本当にわかりにくくなることも、目的語どっちもいけるから)
    //
    // assert: もうthrow一択、目的語に正しいことを書く (assertSoldOutは違う) → 具体的
    //         (ダメなら絶対落とす)
    // 
    // 少しボカしたいならcheck, 直感性を重視するならassert。
    // ボカしたい時もある。在庫チェックの処理が増えた時とか変わった時とか。
    // それがある程度想定されるような場面とか、呼び出し側の修正が難しいときとか。
    // 逆に呼び出し側の修正がしやすい環境とかなら直感性の高いメソッド名の方が好まれやすい。
    //
    // ぼくらは普段から抽象度をコントロールして会話している。
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
