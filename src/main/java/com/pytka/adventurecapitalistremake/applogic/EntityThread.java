package com.pytka.adventurecapitalistremake.applogic;



public class EntityThread implements Runnable{

    private GameEntity entity = null;

    public EntityThread(GameEntity entity){
        this.entity = entity;
    }

    @Override
    public void run(){

        while(true){

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            entity.addItems(10);
            System.out.println("Added items! New money per round: " + entity.getMoneyPerRound());
        }
    }

}
