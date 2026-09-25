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
package org.docksidestage.javatry.basic;

import java.util.ArrayList;
import java.util.List;

import org.docksidestage.unit.PlainTestCase;

/**
 * The test of if-for. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author your_name_here
 */
public class Step02IfForTest extends PlainTestCase {

    // ===================================================================================
    //                                                                        if Statement
    //                                                                        ============
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_if_basic() { // example, so begin from the next method
        int sea = 904;
        if (sea >= 904) {
            sea = 2001;
        }
        log(sea); // your answer? => 2001
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_if_else_basic() {
        int sea = 904;
        if (sea > 904) {
            sea = 2001;
        } else {
            sea = 7;
        }
        log(sea); // your answer? => 7
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_if_elseif_basic() {
        int sea = 904;
        if (sea > 904) {
            sea = 2001;
        } else if (sea >= 904) { //ここでTrueになるから以降は飛ぶ
            sea = 7;
        } else if (sea >= 903) {
            sea = 8;
        } else {
            sea = 9;
        }
        log(sea); // your answer? => 7
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_if_elseif_nested() {
        boolean land = false;
        int sea = 904;
        if (sea > 904) {
            sea = 2001;
            sea = sea++ * 2;
        } else if (land && sea >= 904) { // land = false で and 条件だからFalse
            sea = 7;
            sea = ++sea * 2;
        } else if (sea >= 903 || land) { // sea >= 903 で or だから True
            if (sea % 2 == 0) { // True
                sea = sea++ * 2; // 905 * 2 = 1810
            }
            if (!land) { // True
                land = true; // True
            } else if (sea <= 903) {
                sea++;
            }
            if (sea < 1810) { // False
                sea = 8;
            }
        } else if (sea == 8) { // 上でTrueだから通らない
            sea++;
            land = false;
        } else { // 上でTrueだから通らない
            sea = 9;
        }
        if (sea >= 9 || (sea > 7 && sea < 9)) { // sea >= 9 でTrue
            sea--; // 1809
            if (sea % 2 == 1) {
                sea++; // 1810
            }
        }
        if (land) { // True
            sea = 10; // 10
        }
        log(sea); // your answer? => 10

        // done jflute 次回1on1にて、漠然読みの話 (2026/08/09)
        // o 漠然読みで、まず構造だけを理解して全体像を把握
        //  → すると、自然と当たりが見えてくる (ギャンブルしたくなる箇所が見つかる)
        // o 当たりを付けてフォーカス読み
        //  → ギャンブルに負けることもある。でも損はしない。
        //  → ある程度踏み込んでコード把握したことで、改めて上から読んでいっても0の状態よりは速い

        // 思考をコメントしててイイですね！！  by akinari.tsuji (2026/08/12)
        // 業務しながらjavatryを進めると、自分でも驚くほどに忘れていくので...
        // 何を考えたか適度にコメント残しておくと、次進めるときの負担が少なくなります！
        // だんだん、TODOの管理も煩雑になってくので頑張ってください笑
        // 自分のコメントは基本読んだら（読まなくても）Doneにしちゃって大丈夫です。
        // （適当にDoneにしちゃってください）

        // ありがとうございます！ 些細なことでも残していきます！(松本)

    }

    // ===================================================================================
    //                                                                       for Statement
    //                                                                       =============
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_inti_basic() {
        List<String> stageList = prepareStageList(); //size 4
        String sea = null;
        for (int i = 0; i < stageList.size(); i++) {
            String stage = stageList.get(i);
            if (i == 1) {
                sea = stage; // dockside
            }
        }
        log(sea); // your answer? => dockside
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_foreach_basic() {
        List<String> stageList = prepareStageList();
        String sea = null;
        for (String stage : stageList) {
            sea = stage; // magiclamp
        }
        log(sea); // your answer? => magiclamp
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_foreach_continueBreak() {
        List<String> stageList = prepareStageList();
        String sea = null;
        for (String stage : stageList) {
            if (stage.startsWith("br")) { // broadway が引っかかってcontinueで上に戻る
                continue;
            }
            sea = stage;
            if (stage.contains("ga")) { // hangarが引っかかってbreakでforを抜ける
                break;
            }
        }
        log(sea); // your answer? => hangar
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_listforeach_basic() {
        List<String> stageList = prepareStageList();
        StringBuilder sb = new StringBuilder();
        stageList.forEach(stage -> { //全ての要素に対して適用するやつ
            if (sb.length() > 0) { // ≒文字が入ったら抜ける  docksideが入ったらここに入る
                return;
            }
            if (stage.contains("i")) {
                sb.append(stage);// dockside
            }
        });
        String sea = sb.toString();
        log(sea); // your answer? => dockside

        // done jflute 次回1on1にて、for文とforEach()メソッドの違いについて (2026/08/09)
        // (書き換えエクササイズをやってもらってからフォローすることにする)
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Make list containing "a" from list of prepareStageList() and show it as log by loop. (without Stream API) <br>
     * (prepareStageList()のリストから "a" が含まれているものだけのリストを作成して、それをループで回してログに表示しましょう。(Stream APIなしで))
     */
    public void test_iffor_making() {
        List<String> stageList = prepareStageList();
        List<String> result = new ArrayList<>();
        for (String stage : stageList) {
            if (stage.contains("a")) {
                result.add(stage);
            }
            log(result);
        }
        // write if-for here
        /**
         *             [broadway]
         *             [broadway]
         *             [broadway, hangar]
         *             [broadway, hangar, magiclamp]
         */
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========

    /**
     * Change foreach statement to List's forEach() (keep result after fix) <br>
     * (foreach文をforEach()メソッドへの置き換えてみましょう (修正前と修正後で実行結果が同じになるように))
     */
    public void test_iffor_refactor_foreach_to_forEach() {
        List<String> stageList = prepareStageList();
        StringBuilder sea = new StringBuilder();
        stageList.forEach(stage -> {
            if (stage.startsWith("br")) {
                return;
            }
            // done matsumoto パフォーマンス配慮、toString()でマイループStringインスタンス by jflute (2026/08/27)
            // StringBuilder自身で、contains()に相当する処理ができたら世話ない。
            if (sea.indexOf("ga") >= 0) { //ga が存在しないときは -1になる
                return; // break後の要素を処理しない
            }
            sea.setLength(0);
            sea.append(stage);
        });
        log(sea.toString()); // hangar
        // このエクササイズ、"置き換えてみましょう" なので書き問題です by jflute (2026/08/09)
        // 修正しましたが、seaの型を変えたりしないと全く一緒の動きにはならなそうです、、
        // [へんじ] seaの型は変えてもOKで、コンソールに出てくる結果が同じになればOK by jflute (2026/08/10)

        // #1on1: へんなフラグ変数とか使わずに実現しているのでスマート (2026/08/27)

        // #1on1: for文とforEach()メソッドの違い (2026/08/27)
        // $forEach()メソッドだと、拡張for文に比べて、関係性がわかりやすい。
        //
        // 機能的な違い:
        // forEach()メソッドだとcontinue;break;ができない。
        // forEach()メソッドだと外側のローカル変数の再代入ができない。
        //
        // $for文が慣れていたので、forEach()メソッド慣れてなかった。
        // $stageList.forEach... なので中の処理が属してるように見える？？？
        //
        // forEach()メソッドは、Javaの文法のループではなく、ただのメソッド。
        // 中でJavaの文法のループを使ってループを表現している代理人みたいなもの。
        //
        // ${}の中は別のクラスだから、さらに別のローカル変数を変えられるのも確かに変
        // そう、その直感合ってます。{} は別クラス別メソッド。
        // -> {} Lambda式って呼ぶ。step8 でじっくりやるので詳細はまた今後。
        // ローカル変数というコンセプトもそうだし、仮にできたとしてもライフサイクルですれ違いが起きてカオス。
        // final or 実質finalなら参照はできる。コピーするだけなのでカオスは起きない。
        //
        // continue;break;ができない理由。
        // $クラスと同じ扱い、ループで回れる処理だと思ってないから
        //
        // forEach()メソッドの存在意義は？
        // 先ほどおっしゃった「関係性がわかりやすい」は確かにあるかもだけど...
        // それだけで出てくるか？
        //
        // o intあいfor文: Java当初から (1995年)
        // o 拡張for文: Java10年目くらい (2005)
        // o forEach()メソッド: Java20年目くらい (2015)
        //
        // $メモリとかパフォーマンスとか？
        // 確かにミクロに見ればあるけど、そこまで大きなものではないかも。
        // $可読性？
        // yes, でも「関係性がわかりやすい」だけの話じゃない。
        // forEach()は制限だらけのループと言える。
        // immutable/mutableで比較すると...
        // 外側のローカル変数を変えられないことの安全性
        // 変えられないというルールが決まっているからこその可読性
        // mutable: 拡張for文
        // immutable: forEach()メソッド
        // forEach()メソッドは、ストレートなループを回すのに向いている。
        // しかも、webサービスの現場ではストレートなループがほとんど。
        //
        // よもやま話:
        // 制限はデメリットとは限らない。制限から得られるものがある。
        // ぼくらはいかにうまく制限デザインをするか？
        // 
        // かといって適材適所すぎるのもつらい。
        // 使い分けの判断コストというがデメリットになる。
        // そこを配慮して統一性を優先する考え方もある。
    }

    /**
     * Make your original exercise as question style about if-for statement. <br>
     * (if文for文についてあなたのオリジナルの質問形式のエクササイズを作ってみましょう)
     * <pre>
     * _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
     * your question here (ここにあなたの質問を):
     * どんな挙動をするコードでしょうか
     * _/_/_/_/_/_/_/_/_/_/
     * </pre>
     */
    public void test_iffor_yourExercise() {
        List<String> stageList = prepareStageList();
        for (String stage : stageList) {
            if (stage.contains("i")) {
                log(stage.toUpperCase());
            } else {
                log(stage);
            }
        }
        // done matsumoto [いいね] forとifの挙動の理解が問われる良いエクササイズですね^^ by jflute (2026/08/09)
    }

    // ===================================================================================
    //                                                                        Small Helper
    //                                                                        ============
    private List<String> prepareStageList() {
        List<String> stageList = new ArrayList<>();
        stageList.add("broadway");
        stageList.add("dockside");
        stageList.add("hangar");
        stageList.add("magiclamp");
        return stageList;
    }
}
