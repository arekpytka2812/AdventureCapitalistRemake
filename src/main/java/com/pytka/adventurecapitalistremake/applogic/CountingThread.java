package com.pytka.adventurecapitalistremake.applogic;

import java.util.List;

public class CountingThread implements Runnable{

    private List<GameEntity> entities;

    private Integer[] entitiesWaitCounter;

    private final SessionManager sessionManager;

    public CountingThread(List<GameEntity> entities){
        this.entities = entities;

        this.sessionManager = SessionManager.getInstance();

        this.entitiesWaitCounter = new Integer[entities.size()];

        for(int i = 0; i < entitiesWaitCounter.length; i++){
            entitiesWaitCounter[i] = entities.get(i).getWaitTime();
        }
    }

    @Override
    public void run(){

        while(true) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < entities.size(); i++) {

                var entity = entities.get(i);

                if(!entity.isBought()){
                    continue;
                }

                if(!entity.isRunning()){
                    continue;
                }

                if(entitiesWaitCounter[i] > 0){
                    entitiesWaitCounter[i] -= 1;
                    continue;
                }

                entitiesWaitCounter[i] = entity.getWaitTime();

                entity.setIsRunning(false);

                sessionManager.addPlayerMoney(entity.getMoneyPerRound());

                System.out.println("Money: " + sessionManager.getPlayerMoney());

            }

        }

    }


}
