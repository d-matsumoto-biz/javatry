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
    private static final int MAX_QUANTITY = 10;


    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private int quantity = MAX_QUANTITY;
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

    // チケット購入に伴う処理
    private TicketBuyResult ticketTransaction(Integer handedMoney, TicketInfo ticketInfo){
        if (quantity <= 0) {
            throw new TicketSoldOutException("Sold out");
        }
        int price = ticketInfo.getPrice();

        if (salesProceeds != null) { // second or more purchase
            salesProceeds = salesProceeds + price;
        } else { // first purchase
            salesProceeds = price;
        }
        --quantity;
        TicketBuyResult result = new TicketBuyResult(ticketInfo);
        result.setChange(handedMoney - price);
        return result ;
    }
    // /**
    // * 1Dayパスポートを買う、パークゲスト用のメソッド。
    // * @param handedMoney パークゲストから手渡しされたお金(金額) (NotNull, NotMinus)
    // * @throws TicketSoldOutException ブース内のチケットが売り切れだったら
    // * @throws TicketShortMoneyException 買うのに金額が足りなかったら
    // */
    /**
     * Buy one-day passport, method for park guest.
     * @param handedMoney The money (amount) handed over from park guest. (NotNull, NotMinus)
     * @throws TicketSoldOutException When ticket in booth is sold out.
     * @throws TicketShortMoneyException When the specified money is short for purchase.
     */
    public TicketBuyResult buyOneDayPassport(Integer handedMoney) {
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
    public int getQuantity() {
        return quantity;
    }

    public Integer getSalesProceeds() {
        return salesProceeds;
    }
}
