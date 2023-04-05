package com.pytka.adventurecapitalistremake.applogic;

import lombok.Getter;
import java.util.LinkedList;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;


@Getter
public class Investment {

    private final String NAME;

    private long itemsCount;
    private int waitTime;
    private double moneyPerRound;

    private double multiplier = 1.0;
    private final double MONEY_PER_ITEM;

    private boolean isRunning;
    private boolean isBought;

  //  private final Queue<> TASK_QUEUE;

    private void calculateMoney(){
        this.moneyPerRound = this.MONEY_PER_ITEM * this.itemsCount * this.multiplier;
    }

    public Investment(String NAME, long itemsCount, int waitTime, double moneyPerItem, boolean isBought) {
        this.NAME = NAME;
        this.itemsCount = itemsCount;
        this.waitTime = waitTime;
        this.MONEY_PER_ITEM = moneyPerItem;

        this.isRunning = false;

        this.isBought = isBought;

        calculateMoney();

      //  this.TASK_QUEUE = new LinkedList<>();
    }

   // public void pushTask(){
   //     this.TASK_QUEUE.add();
  //  }

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

}
