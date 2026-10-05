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
public class Ticket {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    // matsumoto インスタンス変数の定義順序 by jflute (2026/09/30)
    // 少なくともConstructorでの設定順序に合ってた方が、目視で見合わせやすい。
    // immutableなものと、mutableなもの、で性質が違うので、そこで分けて並べた方が良いかなと
    private final int displayPrice; // written on ticket, park guest can watch this
    private final TicketInfo ticketInfo;
    private int canUseCount;



    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /**
     * チケットの初期化
     * @param ticketInfo チケット情報
     */
    public Ticket(
            TicketInfo ticketInfo
    ) {
        this.displayPrice = ticketInfo.getPrice();
        this.ticketInfo = ticketInfo;
        this.canUseCount = ticketInfo.getCanUseCount();
    }

    // ===================================================================================
    //                                                                             In Park
    //                                                                             =======
    // #1on1: いいね、JavaDocしっかりでわかりやすい (2026/09/30)
    /**
     * チケットを使用する ( 利用可能な時間を省略して呼び出し )
     * 利用可能な回数が残っていない、
     * もしくは入園時間の指定がないチケットで呼び出された場合は
     * エラーを返します
     */
    public void doInPark() {
        if (canUseCount <= 0) {
            throw new IllegalStateException("Already in park by this ticket: displayedPrice=" + displayPrice);
        }
        if (this.ticketInfo.getEntryStartableTime() != 0)
        {
            throw new IllegalStateException("Time of day is required for this ticket");
        }
        canUseCount--;
    }

    /**
     * チケットを使用する
     * @param time 入園時間
     * 利用可能な時間が制限されているチケットに対応した関数
     * 利用可能な回数が残っていない
     * もしくは、利用可能な時間以前での利用の場合はエラーを返します。
     * (この関数は利用可能な時間が制限されていないチケットでも呼び出し可能)
     */
    public void doInPark(int time) {
        if (canUseCount <= 0) {
            throw new IllegalStateException("Already in park by this ticket: displayedPrice=" + displayPrice);
        }
        if (time < 0 || time > 23) {
            throw new IllegalStateException("The time must be between 0 and 23.");
        }
        if (this.ticketInfo.getEntryStartableTime() > time) {
            throw new IllegalStateException("This ticket cannot be used before " + this.ticketInfo.getEntryStartableTime() + " o'clock.");
        }
        canUseCount--;
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    // #1on1: getterメソッドのJavaDocジレンマ (2026/09/30)
    // getterは役割的にわかりきってるところが多いので、まじめに書くと冗長感が出ちゃう。
    // jfluteは、@returnのみのgetter javadocを書くこともある。
    // @returnは必ずつけておいて欲しい。そういう枠組みになっているので。
    // 説明は、getterの場合は説明が不要というレベルなので、そっちを省略する
    // matsumoto 説明削除で@returnだけで表現するでOK by jflute (2026/09/30)
    /**
     * @return チケット価格
     */
    public int getDisplayPrice() {
        return displayPrice;
    }

    // #1on1: isAlreadyIn()のメソッド自体は互換性のために残して内部の処理で辻褄合わせしてるのGood (2026/09/30)
    // matsumoto すでに "すでに入園しているかどうか" という言葉が曖昧になっているので... by jflute (2026/09/30)
    // 実装は、「チケットを使い切っている」というニュアンスになっているので、JavaDocのコメントもどうにか。
    /**
     * @return チケットが使用済みかどうか
     */
    public boolean isAlreadyIn() {
        return canUseCount == 0;
    }

    /**
     * @return チケットの詳細情報
     */
    public TicketInfo getTicketInfo() {
        return ticketInfo;
    }
}
