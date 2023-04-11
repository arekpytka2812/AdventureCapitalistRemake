package com.pytka.adventurecapitalistremake.applogic;

import com.pytka.adventurecapitalistremake.utils.ForDebugPurposes;
import lombok.Getter;


@Getter
public class Investment {

    private final String NAME;

    private long itemsCount;
    private int waitTime;
    private double moneyPerRound;

    private double multiplier;
    private double moneyPerItem;

    private boolean isBought;
    private boolean hasManager;

    private void calculateMoney(){
        this.moneyPerRound = this.moneyPerItem * this.itemsCount * this.multiplier;
    }

    public Investment(String NAME, long itemsCount, int waitTime, double multiplier, double moneyPerItem, boolean isBought, boolean hasManager) {
        this.NAME = NAME;
        this.itemsCount = itemsCount;
        this.waitTime = waitTime;
        this.multiplier = multiplier;
        this.moneyPerItem = moneyPerItem;
        this.hasManager = hasManager;

        this.isBought = isBought;

        calculateMoney();
    }

    public void addItems(int itemsCount){
        this.itemsCount += itemsCount;

        calculateMoney();
    }

    public void addMultiplier(double factor){
        this.multiplier *= factor;

        calculateMoney();
    }

    public void decreaseWaitTime(int decreaseTime){
        if(this.waitTime == 0){
            return;
        }

        this.waitTime -= decreaseTime;

        if(this.waitTime < 0){
            this.waitTime = 0;
        }
    }

    public void increaseMoneyPerItem(double moneyToAdd){
        this.moneyPerItem += moneyToAdd;
    }

    public boolean hasManager(){
        return hasManager;
    }

    @ForDebugPurposes
    @Override
    public String toString() {
        return "Investment{" +
                "NAME='" + NAME + '\'' +
                ", itemsCount=" + itemsCount +
                ", waitTime=" + waitTime +
                ", moneyPerRound=" + moneyPerRound +
                ", multiplier=" + multiplier +
                ", MONEY_PER_ITEM=" + moneyPerItem +
                ", isBought=" + isBought +
                ", hasManager=" + hasManager +
                '}';
    }
}
