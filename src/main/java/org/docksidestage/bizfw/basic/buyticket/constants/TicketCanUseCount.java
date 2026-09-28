package org.docksidestage.bizfw.basic.buyticket.constants;

public enum TicketCanUseCount {
    TWO_DAY_CAN_USE(2),
    ONE_DAY_CAN_USE(1),
    FOUR_DAY_CAN_USE(4),
    NIGHT_ONLY_TWO_DAY_CAN_USE(2);

    private final int count;

    TicketCanUseCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

}
