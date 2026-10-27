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

import org.docksidestage.bizfw.basic.buyticket.constants.TicketType;

// done matsumoto javadoc, タイトルや説明など、あとauthor by jflute (2026/10/14)
/**
 * チケットを表現するクラス
 * チケットは価格、種類、利用可能な回数を持ち、それらに基づいて使用状況を管理する。
 */
public class Ticket {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    // matsumoto インスタンス変数の定義順序 by jflute (2026/09/30)
    // 少なくともConstructorでの設定順序に合ってた方が、目視で見合わせやすい。
    // immutableなものと、mutableなもの、で性質が違うので、そこで分けて並べた方が良いかなと
    private final int displayPrice; // written on ticket, park guest can watch this
    private final TicketType ticketType;
    private int canUseCount;



    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /**
     * チケットの初期化
     * @param ticketType チケット情報
     */
    public Ticket(
            TicketType ticketType
    ) {
        this.displayPrice = ticketType.getPrice();
        this.ticketType = ticketType;
        this.canUseCount = ticketType.getCanUseCount();
    }

    // ===================================================================================
    //                                                                             In Park
    //                                                                             =======
    // #1on1: いいね、JavaDocしっかりでわかりやすい (2026/09/30)
    // #1on1: いいね、JavaDocで異常系がしっかり書いてあるの良い (2026/10/14)
    // 異常系を表現する時は、@throwsを使うと良い。
    // * @throws IllegalStateException 利用可能な回数が残っていない
    // * @throws IllegalStateException 入園時間の指定がないチケットで呼び出された場合
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
        // done matsumoto ここの括弧降りてる by jflute (2026/10/14)
        // #1on1: オールマン、K&Rの話 (2026/10/14)
        // $既存に合わせるくらいの感じでいいか？
        // yes, 大概フォーマッターがあるので、それで自然とそうなることも多い。
        // ちょこっとIntelliJのフォーマッターのお話。
        // $IntelliJのフォーマッター設定の競合の話。(デフォルトとプラグインで競合したかも)
        if (this.ticketType.getEntryStartableHour() != 0) {
            throw new IllegalStateException("Time of day is required for this ticket");
        }
        canUseCount--;
    }

    // done matsumoto 引数の入園時間、18:30のとき、18を入れるのか？19を入れるのか？ by jflute (2026/10/14)
    // 明示してあげないと、呼び出し側でブレが発生してしまう可能性がある。
    // もし今23時間営業を想定すると、23:30で23を入れると入れちゃうから、24じゃないといけない。
    // すると、18:30も19を入れることになる。切り上げ前提の引数であることをコメントで明示しないと。
    // TODO matsumoto 修行++: そもそも呼び出し側に固定的な処理をお願いするのであれば... by jflute (2026/10/14)
    // そもそも doInPark() の中で解決したいところ。そうすれば呼び出し側のブレやミスも無くなる。
    // だがしかし、それを単純にやると、UnitTestがしづらくなる。ので工夫してみましょう。
    /**
     * チケットを使用する。
     * 利用可能な時間が制限されているチケットに対応したメソッド。
     * (このメソッドは利用可能な時間が制限されていないチケットでも呼び出し可能)
     * @param time 入園時間。切り上げで入力してください (ex. 18:30 -> 19)
     * @throws IllegalStateException 利用可能な回数が残っていない、もしくは利用可能な時間以前での利用の場合
     */
    public void doInPark(int time) {
        if (canUseCount <= 0) {
            throw new IllegalStateException("Already in park by this ticket: displayedPrice=" + displayPrice);
        }
        if (time < 0 || time > 23) {
            throw new IllegalStateException("The time must be between 0 and 23.");
        }
        // TODO matsumoto getEntryStartableHour()を変数に切り出した方が見やすくなる by jflute (2026/10/27)
        if (this.ticketType.getEntryStartableHour() > time) {
            throw new IllegalStateException("This ticket cannot be used before " + this.ticketType.getEntryStartableHour() + " o'clock.");
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

    // TODO matsumoto getTicketInfo() -> getTicketType() by jflute (2026/10/27)
    /**
     * @return チケットの詳細情報
     */
    public TicketType getTicketInfo() {
        return ticketType;
    }
}
