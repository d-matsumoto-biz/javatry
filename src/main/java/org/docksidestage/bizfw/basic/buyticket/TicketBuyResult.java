package org.docksidestage.bizfw.basic.buyticket;

import org.docksidestage.bizfw.basic.buyticket.constants.TicketType;

// TODO done matsumoto クラスのJavaDoc, 最低限のことは書いていきましょう by jflute (2026/10/14)
// e.g.
///**
// * 購入者に渡すチケットの購入結果
// * @author matsumoto
// */

//考えたもののこれ以上は思い浮かびませんでした...
/**
 * 購入者に渡すチケットの購入結果
 * @author daichi.matsumoto
 */
public class TicketBuyResult {
    // matsumoto 戻り値クラスの特性から、できるだけimmutableの方が良いかと by jflute (2026/09/30)
    // 呼び出し側でsetChange()を呼べないように
    // changeを後からsetする理由がここではないと思うので。
    private final Ticket ticket;
    private final int change;

    // #1on1: Constructorでどこまで処理するか!? (2026/09/30)
    // ここも慣習的な話にはなって...比較的、Constructorではあまり業務ロジックは入れないのが一般的かなと。
    // Constructorのイメージとして「Resultを構築する」なので、ほぼできあがったResultを受け取るだけ。
    // Resultを作る人は、実際の処理を行ったTicketBoothが自然かなと。
    // TicketBuyResultのイメージ、もう受付の人が、お釣りとかをトレーで戻すときのトレーのイメージ。
    // プログラミング的にいうと、入れ物クラスに徹する、という感じ。
    // TicketCreator とかだったらアリではあるが、Constructorの中でやるかどうかはまた分かれる。
    // Constructorには極力ロジックを入れないスタイルの人も多い。
    // newくらいだったらまあいいけど、お釣りの計算は確実に避けたい。
    // advance: new Ticket()をオーバーライドして拡張させられるようにするってときもTicketBoothがしっくり来る。
    //
    // #1on1: @paramの追加いいね (2026/10/14)
    // 今の時代、コメントしっかり書いておくと、AIがそれを発展させてくれたり読んでくれたりするので費用対効果高い。
    /**
     * チケット購入結果
     * 購入したチケットの詳細と、お釣りの金額を保持する。
     * @param ticketType 購入するチケット情報
     * @param change お釣り
     */
    public TicketBuyResult(
            TicketType ticketType,
            int change
    ){
        this.ticket = new Ticket(ticketType);
        this.change = change;
    }

    /**
     * @return 購入したチケット
     */
    public Ticket getTicket(){
        return ticket;
    }

    /**
     * @return お釣りの金額
     */
    public int getChange(){
        return change;
    }
}
