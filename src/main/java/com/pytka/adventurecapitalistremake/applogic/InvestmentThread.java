package com.pytka.adventurecapitalistremake.applogic;



public class InvestmentThread implements Runnable{

    private Investment investment;

    public InvestmentThread(Investment investment){
        this.investment = investment;
    }

    @Override
    public void run(){

        while(true){

            //TODO: check and execute methods from taskQueue

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            investment.addItems(10);

            System.out.println("Added items");

        }
    }

}
