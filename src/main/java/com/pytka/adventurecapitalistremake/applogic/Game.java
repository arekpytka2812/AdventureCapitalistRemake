package com.pytka.adventurecapitalistremake.applogic;


import com.pytka.adventurecapitalistremake.utils.CountingTaskService;
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

    private static Map<String, CountingTaskService> progressTasks;
    private static Map<String, ProgressBar> progressBarsMap;

    private static Map<String, InvestmentRunnable> investmentsRunnable;
    private static List<Thread> mainThreads;

    private Game() {
        SessionManager.openSession();
        gameInfo = SessionManager.getGameInfo();

        progressTasks = new HashMap<>();
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

            progressTasks.put(investment.getNAME(), new CountingTaskService(investment));

            investmentsRunnable.put(investment.getNAME(), new InvestmentRunnable(investment));
        }

        var mapKeySet = investmentsRunnable.keySet();

        for(var key : mapKeySet){

           // progressBarsMap.get(key).progressProperty()
          //          .bind(progressTasks.get(key).progressProperty());

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

    public double getMoney(){
        return gameInfo.getPlayerMoney();
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
        progressTasks.get(investmentName).restart();

    }

    public ProgressBar getProgressBar(String barsName){
        return progressBarsMap.get(barsName);
    }

    public CountingTaskService getService(String serviceName){
        return progressTasks.get(serviceName);
    }

    public void putBarAndBindProgress(String investmentName, ProgressBar bar){
        progressBarsMap.put(investmentName, bar);

        bar.progressProperty()
                .bind(progressTasks.get(investmentName).progressProperty());
    }
}