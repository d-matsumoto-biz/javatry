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

import java.math.BigDecimal;

import org.docksidestage.unit.PlainTestCase;

/**
 * The test of variable. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author your_name_here
 */
public class Step01VariableTest extends PlainTestCase {

    // ===================================================================================
    //                                                                      Local Variable
    //                                                                      ==============
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_variable_basic() { // example, so begin from the next method
        String sea = "mystic";
        log(sea); // your answer? => mystic
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_initial() {
        String sea = "mystic";
        Integer land = 8;
        String piari = null;
        String dstore = "mai";
        sea = sea + land + piari + ":" + dstore;
        log(sea); // your answer? => mystic8:mai -> mystic8null:mai
        /**
         * String型にnullが入っている場合、そのままnullが出てくる
         * piariのみ関数に引き渡した場合もnullとでたので結合有無は関係ない
         */
        // done matsumoto [いいね] 実験もしてて素晴らしい。javatryをぜひコードの実験場に^^ by jflute (2026/07/29)
        // log()メソッドでnullを渡して "null" と表示されるのは、またちょっと仕組み(経路)が違ったりはします。
        // log()の場合は StringBuilderのappend()の引数にnullを渡して "null" になります。
        // +によるnullの連結と同じ挙動になるように合わせているのだと思われます。
        // #1on1: プログラミング言語の決め (2026/07/29)
        // C#だったら空文字になる。エラーになる言語もある。一つでもnullがあると結果全部nullになる言語(SQL)も。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_reassigned_basic() {
        String sea = "mystic"; // 1 (1丁目1番地)
        String land = "oneman"; // 2 (2丁目2番地)
        sea = land; // 参照を渡しているだけ by まつもとさん
        // → この瞬間、seaとlandは同じインスタンスを指ししめている。
        //   変数とインスタンスは1:1とは限らず、n:1になりうる。
        land = land + "'s dreams"; // 3('s dreams)? + 4も作られる
        log(land); // 4("oneman's dreams")
        log(sea); // your answer? => oneman
        // done jflute 1on1にて、変数とインスタンスについてフォロー予定 (2026/07/29)
        // #1on1: インスタンスとは？ (2026/07/29)
        // $ 授業でインスタンスという言葉を聞いた。クラスを初期化したらインスタンスが変数に入る。
        // インスタンスにフォーカスする理由。インスタンス間違いを起こさないように。
        // Stringはimmutableなので書き変わらない。Stringの+はStringBuilderに比べて効率悪い。
        // $ 参照されなくなったインスタンスはゴミ？ガベージコレクションされる？
        // yes, すぐではないけど、Javaの都合の良いタイミングでガベージされる。
        // 変数とインスタンスの関係性、後半エクササイズで時系列を超えたn:1の関係の紹介。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_reassigned_int() {
        int sea = 94;
        int land = 415;
        sea = land;
        land++;
        log(sea); // your answer? => 415
        // #1on1: プリミティブ型の紹介 (2026/07/29)
        // 言語によっては、intって書いてオブジェクトになるものある。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_reassigned_BigDecimal() {
        BigDecimal sea = new BigDecimal(94); // 1
        BigDecimal land = new BigDecimal(415); // 2
        sea = land;
        sea = land.add(new BigDecimal(1)); // 3, 4(416)
        sea.add(new BigDecimal(1)); // 5, 6(417)
        log(sea); // your answer? => 417 -> 416
        /**
         * sea.add はsea自体にその値を加えて変数自体を更新するのではなく、
         * 変数にaddした結果を返しているだけで更新されないから、82行目の結果を拾っていないので416
         */
        // done matsumoto [いいね] yes, 自分自身のインスタンスを状態は更新しないわけですね。 by jflute (2026/07/29)
        // BigDecimalはimmutableなクラスとして実装されているので、このようなスタイルのメソッドになります。
        // TODO jflute 1on1にてimmutableのお話をする予定 (2026/07/29)
        // #1on1: BigDecimalのソースコードリーディングしてみた (2026/07/29)
        // よもやま話: ソースコードリーディングのコツ。構造に着目して、知りたいことを効率よく知る。
    }

    // ===================================================================================
    //                                                                   Instance Variable
    //                                                                   =================
    private String instanceBroadway;
    private int instanceDockside;
    private Integer instanceHangar;
    private String instanceMagiclamp;

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_default_String() {
        String sea = instanceBroadway;
        log(sea); // your answer? => "" -> null
        /**
         * 初期化されていないString型の変数はnullになる
         */
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_default_int() {
        int sea = instanceDockside;
        log(sea); // your answer? => null -> 0
        /**
         * int型はnullではなく初期値は0になるらしい
         * これは、intはプリミティブ型だから
         * プリミティブ型の反対は参照型
         * プリミティブ型はint やboolean やlong など
         */
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_default_Integer() {
        Integer sea = instanceHangar;
        log(sea); // your answer? => null
        /**
         * こいつは参照型
         */
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_via_method() {
        instanceBroadway = "bbb";
        instanceMagiclamp = "magician";
        helpInstanceVariableViaMethod(instanceMagiclamp);
        String sea = instanceBroadway + "|" + instanceDockside + "|" + instanceHangar + "|" + instanceMagiclamp;
        log(sea); // your answer? => bbb|0|null|magician -> bigband|1|null|magician
        /**
         * instanceBroadwayはメソッド内のローカル変数ではなくって、クラスに定義されている
         * それをhelpInstanceVariableViaMethodによって書き換えている
         *instanceMagiclampはhelpInstanceVariableViaMethodの引数になっているためそっちが優先的に扱われた？
         */
        // done matsumoto [ふぉろー] クラスに定義されているinstanceMagiclamp (インスタンス変数) と... by jflute (2026/07/29)
        // helpの引数変数としてのinstanceMagiclamp は、同じ名前が付いているけど別物(別の変数、別の箱)です。
        // helpメソッド内では、同名の変数が同じにスコープに存在することになりますが、スコープ的に近い方が優先されます。
    }

    private void helpInstanceVariableViaMethod(String instanceMagiclamp) {
        instanceBroadway = "bigband";
        ++instanceDockside;
        instanceMagiclamp = "burn";
        // #1on1: 同じ名前でも、変数は別物話 (2026/07/29)
    }

    // ===================================================================================
    //                                                                     Method Argument
    //                                                                     ===============
    // -----------------------------------------------------
    //                                 Immutable Method-call
    //                                 ---------------------
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_method_argument_immutable_methodcall() {
        String sea = "harbor";
        int land = 415;
        helpMethodArgumentImmutableMethodcall(sea, land);
        log(sea); // your answer? => harbor
    }

    private void helpMethodArgumentImmutableMethodcall(String sea, int land) {
        ++land;
        String landStr = String.valueOf(land); // is "416"
        sea.concat(landStr); //これまたseaに渡してるわけじゃないからひっかけでは
        // done matsumoto [いいね] ひっかけごめんなさいm(_ _)m by jflute (2026/07/29)
    }

    // -----------------------------------------------------
    //                                   Mutable Method-call
    //                                   -------------------
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_method_argument_mutable_methodcall() {
        StringBuilder sea = new StringBuilder("harbor");
        int land = 415;
        helpMethodArgumentMethodcall(sea, land);
        log(sea); // your answer? => harbor416
        /**
         * StringBulderはStringとは違うと大学で習ったのを思い出した
         * https://qiita.com/dr_tensyo/items/bfe0341c4ec3103159c6
         * https://it-biz.online/java/java-stringbuilder/#toc1
         * 参照先を代入しているのかの違い
         */
        // done matsumoto [いいね] 記事の通り、全くの別物ではあります(^^ by jflute (2026/07/29)
        // 基礎的で安全性を優先してimmutableである String に対して、
        // パフォーマンスを優先してmutableなStringBuilderという感じです。
        // どちらも「文字列」を扱う似た概念のクラスですが、コンセプトが違うことで使い方がガラリと変わります。
        // TODO jflute 1on1にて (2026/07/29)
    }

    private void helpMethodArgumentMethodcall(StringBuilder sea, int land) {
        ++land; //416
        sea.append(land);
    }

    // -----------------------------------------------------
    //                                   Variable Assignment
    //                                   -------------------
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_method_argument_variable_assignment() {
        StringBuilder sea = new StringBuilder("harbor");
        int land = 415;
        helpMethodArgumentVariable(sea, land);
        log(sea); // your answer? => harbor
    }

    private void helpMethodArgumentVariable(StringBuilder sea, int land) {
        ++land;
        String seaStr = sea.toString(); // is "harbor" String型（参照型が返ってくる）
        sea = new StringBuilder(seaStr).append(land); //これまた引数のseaに代入されている。返してない
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Define variables as followings:
     * <pre>
     * o local variable named sea typed String, initial value is "mystic"
     * o local variable named land typed Integer, initial value is null
     * o instance variable named piari typed int, without initial value
     * o show all variables by log() as comma-separated
     * </pre>
     * (変数を以下のように定義しましょう):
     * <pre>
     * o ローカル変数、名前はsea, 型はString, 初期値は "mystic"
     * o ローカル変数、名前はland, 型はInteger, 初期値は null
     * o インスタンス変数、名前はpiari, 型はint, 初期値なし
     * o すべての変数をlog()でカンマ区切りの文字列で表示
     * </pre>
     */
    private int piari;

    public void test_variable_writing() {
        // define variables here
        String sea = "mystic";
        Integer land = null;
        log(sea, land, piari);
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * Make your original exercise as question style about variable. <br>
     * (変数についてあなたのオリジナルの質問形式のエクササイズを作ってみましょう)
     * <pre>
     * _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
     * your question here (ここにあなたの質問を):
     *  instanceBroadway;
     *  instanceDockside;
     *  instanceHangar;
     *  instanceMagiclamp;
     *  それぞれに任意の値を代入してlog()で出力する
     * _/_/_/_/_/_/_/_/_/_/
     * </pre>
     */

    public void test_variable_yourExercise() {
        // write your code here
        instanceBroadway = "hogehoge";
        instanceDockside = 1200;
        instanceHangar = 1234;
        instanceMagiclamp = "mystic";
        log(instanceBroadway, instanceDockside, instanceHangar, instanceMagiclamp);
    }
}
