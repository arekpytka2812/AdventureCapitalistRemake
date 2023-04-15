package com.pytka.adventurecapitalistremake.applogic;


import com.pytka.adventurecapitalistremake.utils.ScheduledProgressService;
import com.pytka.adventurecapitalistremake.utils.SimpleProgressService;
import com.pytka.adventurecapitalistremake.utils.GameInfo;
import com.pytka.adventurecapitalistremake.utils.Task;
import javafx.scene.control.ProgressBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {

    private static Game game = null;
    private static GameInfo gameInfo;

    private Map<String, SimpleProgressService> simpleProgressServices;
    private Map<String, ScheduledProgressService> scheduledProgressServices;
    private final Map<String, ProgressBar> progressBarsMap;

    private final Map<String, InvestmentRunnable> investmentsRunnable;
    private final List<Thread> mainThreads;

    private Game() {
        SessionManager.openSession();
        gameInfo = SessionManager.getGameInfo();

        simpleProgressServices = new HashMap<>();
        scheduledProgressServices = new HashMap<>();
        progressBarsMap = new HashMap<>();
        investmentsRunnable = new HashMap<>();
        mainThreads = new ArrayList<>();
    }

    public static Game getInstance() {
        if (game == null) {
            game = new Game();
        }

        return game;
    }

    public void run() {

        for(var investment : gameInfo.getInvestments()){

            if(investment.hasManager()){
                scheduledProgressServices.put(investment.getNAME(), new ScheduledProgressService(investment));
            }
            else{
                simpleProgressServices.put(investment.getNAME(), new SimpleProgressService(investment));
            }

            investmentsRunnable.put(investment.getNAME(), new InvestmentRunnable(investment));
        }

        var mapKeySet = investmentsRunnable.keySet();

        for(var key : mapKeySet){

            mainThreads.add(new Thread(investmentsRunnable.get(key)));

            var lastIndex = mainThreads.size() - 1;
            mainThreads.get(lastIndex).start();
        }
    }

    public void addPlayerMoney(double money){
        gameInfo.setPlayerMoney(gameInfo.getPlayerMoney() + money);
    }

    public void addTask(String investmentName, Task task){
        investmentsRunnable.get(investmentName).addTask(task);
    }

    public Investment getInvestment(String investmentName){
        var investments = gameInfo.getInvestments();

        for(var investment : investments){
            if(investment.getNAME().equals(investmentName)){
                return investment;
            }
        }

        return null;
    }

    public void startInvestment(String investmentName){
        if(getInvestment(investmentName).hasManager()){
            scheduledProgressServices.get(investmentName).start();
            return;
        }

        simpleProgressServices.get(investmentName).restart();
    }

    public ProgressBar getProgressBar(String barsName){
        return progressBarsMap.get(barsName);
    }

    public SimpleProgressService getService(String serviceName){
        return simpleProgressServices.get(serviceName);
    }

    public void putBarAndBindProgress(String investmentName, ProgressBar bar){
        progressBarsMap.put(investmentName, bar);

        if(getInvestment(investmentName).hasManager()){
            bar.progressProperty()
                    .bind(scheduledProgressServices.get(investmentName).progressProperty());
            return;
        }

        bar.progressProperty()
                .bind(simpleProgressServices.get(investmentName).progressProperty());
    }
}