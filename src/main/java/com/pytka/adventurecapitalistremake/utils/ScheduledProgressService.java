package com.pytka.adventurecapitalistremake.utils;

import com.pytka.adventurecapitalistremake.applogic.Game;
import com.pytka.adventurecapitalistremake.applogic.Investment;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.util.Duration;

public class ScheduledProgressService extends ScheduledService<Void>{

    private final Investment investment;

    public ScheduledProgressService(Investment investment){
        this.investment = investment;
        this.setPeriod(Duration.seconds(0));
    }


    @Override
    protected Task<Void> createTask() {

        Task<Void> task = new Task<>(){

            @Override
            protected Void call() {

                var waitTime = investment.getWaitTime() * 1000;
                var waitedAlready = 0;

                while (waitedAlready < waitTime) {

                    waitedAlready += 10;

                    updateProgress((double) waitedAlready / waitTime, 1.0);

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        if (isCancelled()) {
                            updateMessage("Cancelled!");
                        }
                    }

                }

                updateProgress(0.0, 1.0);
                succeeded();

                Game.getInstance().addPlayerMoney(investment.getMoneyPerRound());

                return null;
            }
        };
        return task;
    }
}
