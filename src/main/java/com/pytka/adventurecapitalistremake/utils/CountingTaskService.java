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

                    waitedAlready += 10;

                    updateProgress((double) waitedAlready/waitTime, 1.0);

                    try {
                        Thread.sleep(10);
                    }
                    catch (InterruptedException e) {
                        if(isCancelled()){
                            updateMessage("Cancelled!");
                        }
                    }

                }

                updateProgress(0.0,1.0);
                succeeded();

                return null;
            }
        };
        return task;
    }

}
