/*
 * Copyright 2019-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.bizfw.basic.buyticket;

import org.docksidestage.bizfw.basic.buyticket.constants.TicketInfo;

/**
 * @author jflute
 */
public class TicketBooth {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========


    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private Integer salesProceeds; // null allowed: until first purchase

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TicketBooth() {
    }

    // ===================================================================================
    //                                                                          Buy Ticket
    //                                                                          ==========
    // you can rewrite comments for your own language by jflute
    // e.g. Japanese

    // TODO matsumoto 共通のprivateメソッドは、Javaの場合わりと下に置くことが多いので移動をお願いします by jflute (2026/09/30)
    // (基本考え方として、publicが上、privateが下、という慣習がある)
    // TODO matsumoto 一応基本を考えると、動詞始めりのメソッド名にしておいた方が無難 by jflute (2026/09/30)
    // e.g. executeTicketTransaction(), beginTicketTransaction(), doBuyPassport() (実処理メソッドみたいなニュアンス)
    // publicのbuyに対して、doをつけて実処理として区別するみたいな手法。(prefixを分けたい)
    // チケット購入に伴う処理
    private TicketBuyResult ticketTransaction(Integer handedMoney, TicketInfo ticketInfo){
        int price = ticketInfo.getPrice();
        TicketQuantity.isAvailabilityForSale(ticketInfo); // 購入前チェック
        TicketQuantity.checkAndDecreaseQuantity(ticketInfo);
        // TODO matsumoto salesの処理にresultが関わってないので... by jflute (2026/09/30)
        // 先にresultをnewしちゃうと、salesの処理にresultが関わっているように見えちゃう。
        // (戻り値のオブジェクトをあらかじめ用意しておいてわかりやすくする手法もあるが、ここだと中途半端)
        Ticket ticket = new Ticket(ticketInfo);
        TicketBuyResult result = new TicketBuyResult(ticketInfo);
        if (salesProceeds != null) { // second or more purchase
            salesProceeds = salesProceeds + price;
        } else { // first purchase
            salesProceeds = price;
        }
        int change = handedMoney - price;
        
        result.setChange(change);
        return result ;
    }
    // /**
    // * 1Dayパスポートを買う、パークゲスト用のメソッド。
    // * @param handedMoney パークゲストから手渡しされたお金(金額) (NotNull, NotMinus)
    // * @throws TicketSoldOutException ブース内のチケットが売り切れだったら
    // * @throws TicketShortMoneyException 買うのに金額が足りなかったら
    // */
    // TODO matsumoto 既存のJavaDoc, 有効なので戻り値を追加したのであれば、戻り値の説明を追加で by jflute (2026/09/30)
    // (日本語で書いてOKですので)
    /**
     * Buy one-day passport, method for park guest.
     * @param handedMoney The money (amount) handed over from park guest. (NotNull, NotMinus)
     * @throws TicketSoldOutException When ticket in booth is sold out.
     * @throws TicketShortMoneyException When the specified money is short for purchase.
     */
    public TicketBuyResult buyOneDayPassport(Integer handedMoney) {
        // TODO matsumoto ShortMoneyの処理もticketTransaction()に入れてもいいかなと by jflute (2026/09/30)
        if (handedMoney < TicketInfo.ONE_DAY.getPrice()) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }
        TicketBuyResult result = ticketTransaction(handedMoney, TicketInfo.ONE_DAY);
        return result;
    }

    public TicketBuyResult buyTwoDayPassport(Integer handedMoney){
        if (handedMoney < TicketInfo.TWO_DAY.getPrice()) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }
        TicketBuyResult result = ticketTransaction(handedMoney, TicketInfo.TWO_DAY);
        return result;
    }

    public TicketBuyResult buyFourDayPassport(Integer handedMoney){
        if (handedMoney < TicketInfo.FOUR_DAY.getPrice()) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }
        TicketBuyResult result = ticketTransaction(handedMoney, TicketInfo.FOUR_DAY);
        return result;
    }

    public TicketBuyResult buyNightOnlyTwoDayPassport(Integer handedMoney){
        if (handedMoney < TicketInfo.NIGHT_ONLY_TWO_DAY.getPrice()) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }
        TicketBuyResult result = ticketTransaction(handedMoney, TicketInfo.NIGHT_ONLY_TWO_DAY);
        return result;
    }

    // #1on1: $問題の形式に合わせて両方のスタイルを残している (2026/09/30)
    // $下のメソッドだけでいいかなという感覚。
    // チケット購入処理の本質を捉えて、ひとまとまりにできるというのを考えた素晴らしい。
    // どっちが正解というわけでもないレベルではあります。
    // あえてメソッドでチケット種別を表現するスタイルのメリットを挙げるとしたら...
    // TicketBoothでしか売らないチケット種別を表現することができる。(enumの隠蔽)
    // そういう意味では、一応メソッドチケット種別スタイルはチケット種別の種類を隠蔽していると言える。
    // 一方で、enumを階層化して、e.g. BoothableTicketInfo とか作って公開すれば同じこと。
    /**
     * チケットを購入する
     * お金が足りない場合や売り切れの場合はエラーを返す。
     * @param handedMoney 払ったお金
     * @param ticketInfo 購入するチケットの情報
     * @return 購入結果
     */
    public TicketBuyResult buyPassport(Integer handedMoney, TicketInfo ticketInfo){
        if (handedMoney < ticketInfo.getPrice()) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }
        TicketBuyResult result = ticketTransaction(handedMoney, ticketInfo);
        return result;
    }

    public static class TicketSoldOutException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketSoldOutException(String msg) {
            super(msg);
        }
    }

    public static class TicketShortMoneyException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketShortMoneyException(String msg) {
            super(msg);
        }
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getQuantity(TicketInfo ticketInfo) {
        return TicketQuantity.getQuantity(ticketInfo);
    }

    public Integer getSalesProceeds() {
        return salesProceeds;
    }
}
