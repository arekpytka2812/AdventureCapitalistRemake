package com.pytka.adventurecapitalistremake.utils;

import com.pytka.adventurecapitalistremake.applogic.Investment;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class CountingTaskService extends Service<Void> {

    private final Investment investment;

    public CountingTaskService(Investment investment){
        this.investment = investment;
    }

    @Override
    protected Task<Void> createTask() {

        Task<Void> task = new Task<>(){
            @Override
            protected Void call(){

                var waitTime = investment.getWaitTime() * 1000;
                var waitedAlready = 0;

                while(waitedAlready < waitTime){

                    waitedAlready += 100;

                    updateProgress((double) waitedAlready/waitTime, 1.0);
                    System.out.println(waitedAlready + "/" + waitTime);

                    try {
                        Thread.sleep(100);
                    }
                    catch (InterruptedException e) {
                        if(isCancelled()){
                            updateMessage("Cancelled!");
                        }
                    }

                }
                return null;
            }
        };
        return task;
    }

    @ForDebugPurposes
    @Override
    protected void succeeded(){
        System.out.println(investment.getNAME() + " done");
    }
}
