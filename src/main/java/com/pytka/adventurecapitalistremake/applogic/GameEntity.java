package com.pytka.adventurecapitalistremake.applogic;

import lombok.Getter;

import java.util.LinkedList;
import java.util.Queue;


@Getter
public class GameEntity{

    private final String NAME;

    private long itemsCount;
    private int waitTime;
    private double moneyPerRound;

    private double multiplier = 1.0;
    private final double MONEY_PER_ITEM;

    private final Queue<Void> TASK_QUEUE;

    private void calculateMoney(){
        this.moneyPerRound = this.MONEY_PER_ITEM * this.itemsCount * this.multiplier;
    }

    public GameEntity(String NAME, long itemsCount, int waitTime, double moneyPerRound, double moneyPerItem) {
        this.NAME = NAME;
        this.itemsCount = itemsCount;
        this.waitTime = waitTime;
        this.moneyPerRound = moneyPerRound;
        this.MONEY_PER_ITEM = moneyPerItem;

        this.TASK_QUEUE = new LinkedList<>();
    }

    public void pushTask(Void task){
        this.TASK_QUEUE.add(task);
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

}
