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
            progressBarsMap.put(investment.getNAME(), new ProgressBar());

            investmentsRunnable.put(investment.getNAME(), new InvestmentRunnable(investment));
        }

        var mapKeySet = investmentsRunnable.keySet();

        for(var key : mapKeySet){

            progressBarsMap.get(key).progressProperty().bind(progressTasks.get(key).progressProperty());

            mainThreads.add(new Thread(investmentsRunnable.get(key)));

            var lastIndex = mainThreads.size() - 1;
            mainThreads.get(lastIndex).start();
        }
    }

    public static void addPlayerMoney(double money){
        gameInfo.setPlayerMoney(gameInfo.getPlayerMoney() + money);
    }

    public static void addTask(String investmentName, Task task){
        investmentsRunnable.get(investmentName).addTask(task);
    }

    public static double getMoney(){
        return gameInfo.getPlayerMoney();
    }

    public static Investment getInvestment(String investmentName){
        var investments = gameInfo.getInvestments();

        for(var investment : investments){
            if(investment.getNAME().equals(investmentName)){
                return investment;
            }
        }

        return null;
    }

    public static Map<String, ProgressBar> getProgressBarsMap(){
        return progressBarsMap;
    }

    public static void startInvestment(String investmentName){
        progressTasks.get(investmentName).restart();
    }

    public static ProgressBar getProgressBar(String barsName){
        return progressBarsMap.get(barsName);
    }

}