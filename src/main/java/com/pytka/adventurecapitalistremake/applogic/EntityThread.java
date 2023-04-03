package com.pytka.adventurecapitalistremake.applogic;

public class EntityThread implements Runnable{

    private GameEntity entity = null;

    public EntityThread(GameEntity entity){
        this.entity = entity;
    }

    @Override
    public void run(){
        while(true){
            System.out.println("Money: " + entity.getMoneyPerRound());

            try {
                System.out.println("start wait");

                var start = System.currentTimeMillis();

                Thread.sleep(entity.getWaitTime());

                var stop = System.currentTimeMillis();

                var time  = (stop - start) / 1000;
                System.out.println("time: " + time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
