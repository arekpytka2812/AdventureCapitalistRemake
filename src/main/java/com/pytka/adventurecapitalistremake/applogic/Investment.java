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
    private final double MONEY_PER_ITEM;

    private boolean isRunning;
    private boolean isBought;
    private boolean hasManager;

    private void calculateMoney(){
        this.moneyPerRound = this.MONEY_PER_ITEM * this.itemsCount * this.multiplier;
    }

    public Investment(String NAME, long itemsCount, int waitTime, double multiplier, double moneyPerItem, boolean isBought, boolean hasManager) {
        this.NAME = NAME;
        this.itemsCount = itemsCount;
        this.waitTime = waitTime;
        this.multiplier = multiplier;
        this.MONEY_PER_ITEM = moneyPerItem;
        this.hasManager = hasManager;

        this.isRunning = this.hasManager;

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

    public void setIsRunning(boolean isRunning){
        this.isRunning = isRunning;
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
                ", MONEY_PER_ITEM=" + MONEY_PER_ITEM +
                ", isBought=" + isBought +
                ", hasManager=" + hasManager +
                '}';
    }
}
