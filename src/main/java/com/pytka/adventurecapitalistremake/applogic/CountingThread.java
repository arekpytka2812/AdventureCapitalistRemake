package com.pytka.adventurecapitalistremake.applogic;

import com.pytka.adventurecapitalistremake.utils.ForDebugPurposes;

import java.util.List;

public class CountingThread implements Runnable{

    private List<Investment> investments;

    private Integer[] investmentsWaitCounter;

    private final SessionManager sessionManager;

    public CountingThread(List<Investment> investments){
        this.investments = investments;

        this.sessionManager = SessionManager.getInstance();

        this.investmentsWaitCounter = new Integer[investments.size()];

        for(int i = 0; i < investmentsWaitCounter.length; i++){
            investmentsWaitCounter[i] = investments.get(i).getWaitTime();
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

            for (int i = 0; i < investments.size(); i++) {

                var entity = investments.get(i);

                if(!entity.isBought()){
                    continue;
                }

                if(!entity.isRunning()){
                    continue;
                }

                if(investmentsWaitCounter[i] > 0){
                    investmentsWaitCounter[i] -= 1;
                    continue;
                }

                investmentsWaitCounter[i] = entity.getWaitTime();

                entity.setIsRunning(false);

                sessionManager.addPlayerMoney(entity.getMoneyPerRound());

                // @ForDebugPurposes
                System.out.println("Money: " + sessionManager.getPlayerMoney());

            }

        }

    }


}
