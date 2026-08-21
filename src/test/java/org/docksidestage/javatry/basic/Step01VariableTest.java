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

        // TODO matsumoto 自己紹介です（読んでなくてもDoneでおkです笑） by akinari.tsuji (2026/08/12)
        // (髙宮さん、スギモトさんとは面識あるのですが、松本さんとちゃんとお話ししたことないので...）←部屋の隅の隅なので同期含め部署外の人との交流は少ないです(泣)
        // 改めて25卒の辻です、よろしくお願いします！                          <- お忙しいところありがとうございます...!よろしくお願いいたします！！
        // 自己紹介confluにまとめてるのでもし興味あれば...（お忙しいと思うのでスルーで大丈夫です笑）
        // https://bizreach.atlassian.net/wiki/x/KwBb-w                 <- 読ませていただきました...!! 自分のもぜひ..! https://bizreach.atlassian.net/wiki/x/KQDPRAE

        // TODO matsumoto 松本さんは普段どの言語を使うのでしょう？？？ by akinari.tsuji (2026/08/12)
        // そういえば知らなかったので教えて欲しいです！
        // （念の為ですが）publicリポジトリなので、プロダクト名などは伏せて教えてください！
        // （去年たまーに、業務情報をjavatyrリポジトリに書いてないか不安になることがありました笑）　← ヒェッ　肝が冷えますね
        // TODO 普段はAngularとScalaがメインです！
        // 大学生の頃の個人開発ではもっぱらPython, WebアプリならDjangoでまとめて作るか、LLMで開発ができるようになってきてから
        // FastAPIとNext.jsで分けて作っていました！

        // これからよろしくお願いいたします！
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
        // done jflute 次回1on1にてimmutableのお話をする予定 (2026/07/29)
        // #1on1: BigDecimalのソースコードリーディングしてみた (2026/07/29)
        // よもやま話: ソースコードリーディングのコツ。構造に着目して、知りたいことを効率よく知る。
        // #1on1: immutableとは？ (2026/08/10)
        // immutableという言葉、あんまり使ってない by まつもとさん
        // immutable (不変な), mutable (可変な)
        // o immutable なクラス(インスタンス) // default
        // o immutable な変数
        //
        // immutableのメリット/デメリット
        // o メリット: 値が変わらないことが保証される
        //  → (学生時代に)並列処理などで値が変わっちゃうのを防げる!? by まつもとさん
        //  → 安全性 (値を変えちゃいけないものが変わらないので安心)
        //  → 可読性 (immutableが読むための情報になる)
        //
        // o デメリット: メモリの効率 by まつもとさん
        //  → でもStringくらいなら些細なことかな？ by まつもとさん
        //  → そのデメリット、昔は気にしていたけど、今はだいぶ薄くなってきた
        //  → immutableを実現するのに多少手間が掛かる by jflute
        //  → mutableに手間をかけたらimmutable, なので言語の文法のフォローなどが必要
        //  → 昨今の言語だと、immutableサポートが手厚くなってきている
        //
        // immutable歴史
        // o 昔はインフラが貧弱だった (メモリめっちゃ少ないし、言語の文法の貧弱)
        // o いまやメモリたくさんなので、細かいことは気にしなくても良くなってきた
        // o 昨今の言語だと、immutableサポートが手厚くなってきている
        // o なので、immutableの流れが来ている (この10年15年イメージ)
        //
        // immutableのバランス
        // o Java: 歴史的なこともあれば、コンセプトもあって、混ざってる
        //    → 8:2くらいでimmutable頑張るけど無理しない (by jflute)
        //    → mutableで扱うときは、安全性/可読性を向上させる別の手段をうまく使って工夫する
        // o immutable推しの他の言語
        //    → immutable推しの文法が用意されていてやりやすい

        // TODO matsumoto ソースコードリーディングいいですね！！ by akinari.tsuji (2026/08/12)
        // （チームによるかと思いますが）先輩・ベテランエンジニアとライブラリやOSSのコードを一緒に読む機会がとても貴重なので、
        // ぜひ読み方を盗んでください！そしてここにコツを書いておいてください（自分が読んで参考にします笑）
        // もし機会があれば、チームの先輩とペアプロするととても勉強になるのでぜひ！
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
        // #1on1: immutableの論理がわかっていれば、helpメソッドを読まなくても答えがわかる (2026/08/10)
        // 読み飛ばし。immutableは情報。
        // mutableな引数だったら、helpの中で変えてなくても、変えてないことを確認しないといけない。
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
        // done jflute 次回1on1にてimmutable/mutable (2026/07/29)
    }

    private void helpMethodArgumentMethodcall(StringBuilder sea, int land) {
        ++land; //416
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
