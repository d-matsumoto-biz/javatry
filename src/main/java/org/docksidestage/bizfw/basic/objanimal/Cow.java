package org.docksidestage.bizfw.basic.objanimal;

import org.docksidestage.bizfw.basic.objanimal.Ridable;

/**
 * The object for Cow(牛)
 * @author daichi
 */
public class Cow extends Animal implements Ridable{

    @Override
    protected String getBarkWord() {
        return "moo"; // 「モー🐮」
    }

    @Override
    public String ride() {
        return "背中に乗って、ゆっくり進む";
    }
}
