package com.pytka.adventurecapitalistremake.applogic;



public class InvestmentThread implements Runnable{

    private Investment investment = null;

    public InvestmentThread(Investment investment){
        this.investment = investment;
    }

    @Override
    public void run(){

        while(true){

            //TODO: check and execute methods from taskQueue

        }
    }

}
