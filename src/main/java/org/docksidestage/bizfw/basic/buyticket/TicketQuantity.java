package org.docksidestage.bizfw.basic.buyticket;

import java.util.HashMap;
import java.util.Map;

import org.docksidestage.bizfw.basic.buyticket.constants.TicketType;

// #1on1: $Boothが何個あっても、共通の在庫なので、staticでシングルトンになっている (2026/10/27)
// そういう意図がしっかりしているのGood。
// #1on1: Booth共有の在庫になったのであれば、マルチスレッド対応を意識したいところ (2026/10/27)
// o TicketQuantityのロジック的なマルチスレッド対応
// o 内部で使っているMapのマルチスレッド対応
// ここは思考トレーニングのみで。
// checkAndDecreaseQuantity()のところで、実装イメージも追ってみた。

// done matsumoto javadocお願い by jflute (2026/10/27)
// TODO matsumoto @author もお願いします by jflute (2026/11/18)
/**
 * チケットの在庫数を管理するクラス
 */
public class TicketQuantity {
    // done matsumoto 具象クラスで扱うのではなく、Map interfaceで取り扱おう by jflute (2026/10/27)
    private static Map<TicketType, Integer> ticketQuantityMap;
    // もし全くlazyLoadせずに初期化をするならこういうやり方も
    // (変数宣言で new HashMap() する前提で)
    //static {
        // ここでMaxQuantity初期化
        //for (TicketType key : TicketType.values()) {
        //    ticketQuantityMap.put(key, key.getMaxQuantity());
        //}
    //}

    // #1on1: いいね、privateの理由が書いてあるのGood (2026/11/18)
    /**
     * newさせないため
     */
    private TicketQuantity(){
    }

    // #1on1: getInstance(ticketType) でlazyLoadのための引数になっている話 (2026/11/18)
    // $lazyLoadは初期化のやり方でたまたま思いついたもの
    // パフォーマンスとかメモリ効率とかでlazyLoad自体は状況によってはアリなのでそれはそれでOKとして...
    // 引数のticketTypeの役割も書いておきたいところ。
    // e.g. @param ticketType チケットの情報、初期化のために指定する
    //
    // もし、一括初期化をするとしたら...
    // TODO matsumoto まつもとさんのやりたいと思ったやり方で直してみてください by jflute (2026/11/18)
    // TODO matsumoto getInstance()という名前 by jflute (2026/11/18)
    // $Slackのインスタンスの作るときの...
    // Mapがシングルトンの投影になってるのであながち悪くはないが、
    // 一般的なgetInstance()の使い方とはちょっとズレるところがあるので、
    // TicketQuantity自体のインスタンスが取れるように見えてしまう。
    // なので、内部のmapを取るよってニュアンスが合ったほうが無難ではあるかなと。
    // e.g. getInstanceMap(), getInternalMap(), getManagedMap(), ...
    //      getQuantityMap(), ...
    /**
     * チケット残数管理に用いるインスタンスを取得するメソッド
     * @param ticketType チケットの情報
     * @return チケットの在庫数
     */
    private static Map<TicketType, Integer> getInstance(TicketType ticketType) {
        if(ticketQuantityMap == null){
            // ここがインスタンス自体の初期化
            ticketQuantityMap = new HashMap<>();
            // MaxQuantityをlazyLoadしないケースだとこんな感じ by jflute
            //for (TicketType key : TicketType.values()) {
            //    ticketQuantityMap.put(key, key.getMaxQuantity());
            //}
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
        Map<TicketType, Integer> instance = getInstance(ticketType);
        return instance.get(ticketType);
    }

    /**
     * 在庫数を確認して減らすメソッド
     * @param ticketType チケットの情報
     */
    public static void checkAndDecreaseQuantity(TicketType ticketType) {
        Map<TicketType, Integer> instance = getInstance(ticketType);
        checkAvailabilityForSale(ticketType);
        instance.replace(ticketType, instance.get(ticketType) - 1);
    }

    // done matsumoto isだと判定感もあるけどboolean感が強いので... by jflute (2026/10/27)
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
    public static void checkAvailabilityForSale(TicketType ticketType) {
        if(getInstance(ticketType).get(ticketType) <= 0)
            throw new TicketBooth.TicketSoldOutException(ticketType + " is sold out");
    }
    //本当は在庫ロックの仕組みがあるとユーザーには親切なのかも
}
