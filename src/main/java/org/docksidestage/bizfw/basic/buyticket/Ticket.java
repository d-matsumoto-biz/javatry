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

/**
 * @author jflute
 */
public class Ticket {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final int displayPrice; // written on ticket, park guest can watch this
    private int canUseCount;
    private static final int TWO_DAY_CAN_USE = 2;
    private static final int ONE_DAY_CAN_USE = 1;
    private static final int FOUR_DAY_CAN_USE = 4;
    private static final int NIGHT_ONLY_TWO_DAY_CAN_USE = 2;
    private final TicketType ticketType;


    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Ticket(
            int displayPrice,
            TicketType ticketType
    ) {
        this.displayPrice = displayPrice;
        this.ticketType = ticketType;
        switch (ticketType) {
            case ONE_DAY:
                this.canUseCount = ONE_DAY_CAN_USE;
                break;
            case TWO_DAY:
                this.canUseCount = TWO_DAY_CAN_USE;
                break;
            case FOUR_DAY:
                this.canUseCount = FOUR_DAY_CAN_USE;
                break;
            case NIGHT_ONLY_TWO_DAY:
                this.canUseCount = NIGHT_ONLY_TWO_DAY_CAN_USE;
                break;
        }
    }

    // ===================================================================================
    //                                                                             In Park
    //                                                                             =======
    public void doInPark() {
        if (canUseCount <= 0) {
            throw new IllegalStateException("Already in park by this ticket: displayedPrice=" + displayPrice);
        }
        if (this.ticketType == TicketType.NIGHT_ONLY_TWO_DAY)
        {
            throw new IllegalStateException("Time of day is required for this ticket");
        }
        canUseCount--;
    }

    //昼夜判定用
    public void doInPark(TimeOfDay time) {
        if (canUseCount <= 0) {
            throw new IllegalStateException("Already in park by this ticket: displayedPrice=" + displayPrice);
        }
        if(this.ticketType == TicketType.NIGHT_ONLY_TWO_DAY && time != TimeOfDay.NIGHT)
        {
            throw new IllegalStateException("This ticket is only usable for night");
        }
        canUseCount--;
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getDisplayPrice() {
        return displayPrice;
    }

    public boolean isAlreadyIn() {
        return canUseCount == 0;
    }
    public TicketType getTicketType() {
        return ticketType;
    }
}
