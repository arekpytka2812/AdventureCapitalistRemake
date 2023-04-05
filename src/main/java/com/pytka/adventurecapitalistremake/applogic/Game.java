package com.pytka.adventurecapitalistremake.applogic;


import com.pytka.adventurecapitalistremake.utils.GameInfo;
import com.pytka.adventurecapitalistremake.utils.Task;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Game {

    private static Game game = null;
    private static GameInfo gameInfo;

    private static List<Thread> mainThreads;
    private static HashMap<String, InvestmentThread> investmentThreads;
    private static CountingThread countingThread;

    private Game() {
        SessionManager.openSession();
        gameInfo = SessionManager.getGameInfo();

        mainThreads = new ArrayList<>();
    }

    public static Game getInstance() {
        if (game == null) {
            game = new Game();
        }

        return game;
    }

    public void run() {

        countingThread = new CountingThread(gameInfo.getInvestments());
        investmentThreads = new HashMap<>();

        for(var investment : gameInfo.getInvestments()){
            investmentThreads.put(investment.getNAME(), new InvestmentThread(investment));
        }

        // counting thread
        mainThreads.add(new Thread(countingThread));
        mainThreads.get(0).start();

        var mapKeySet = investmentThreads.keySet();

        // investment threads
        for(var key : mapKeySet){

            mainThreads.add(new Thread(investmentThreads.get(key)));

            var lastIndex = mainThreads.size() - 1;
            mainThreads.get(lastIndex).start();
        }

    }

    public static void addPlayerMoney(double money){
        gameInfo.setPlayerMoney(gameInfo.getPlayerMoney() + money);
    }

    public static void addTask(String investmentName, Task task){
        investmentThreads.get(investmentName).addTask(task);
    }

    public static double getMoney(){
        return gameInfo.getPlayerMoney();
    }

}
