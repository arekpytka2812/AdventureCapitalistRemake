package com.pytka.adventurecapitalistremake.applogic;

import com.pytka.adventurecapitalistremake.utils.ForDebugPurposes;
import com.pytka.adventurecapitalistremake.utils.GameInfo;

import java.util.List;

public class CountingThread implements Runnable{

    private final List<Investment> investments;
    private final Integer[] investmentsWaitCounter;

    public CountingThread(List<Investment> investments){
        this.investments = investments;
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

                var investment = investments.get(i);

                if(!investment.isBought()){
                    continue;
                }

                if(!investment.isRunning()){
                    continue;
                }

                if(investmentsWaitCounter[i] > 0){
                    investmentsWaitCounter[i] -= 1;
                    continue;
                }

                investmentsWaitCounter[i] = investment.getWaitTime();

                Game.addPlayerMoney(investment.getMoneyPerRound());

                investment.setIsRunning(false);

                if(investment.hasManager()){
                    investment.setIsRunning(true);
                }

                // @ForDebugPurposes
                System.out.println("Investment: " + investment.getNAME() + " added money: " + investment.getMoneyPerRound() + ", now money: " + Game.getMoney());
            }

        }

    }


}
