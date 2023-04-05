package com.pytka.adventurecapitalistremake.applogic;


import com.pytka.adventurecapitalistremake.utils.GameInfo;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private static Game game = null;
    private static GameInfo gameInfo;

    private static List<Thread> mainThreads;

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

        CountingThread countingThread = new CountingThread(gameInfo.getInvestments());
        List<InvestmentThread> investmentThreads = new ArrayList<>();

        for(var investment : gameInfo.getInvestments()){
            investmentThreads.add(new InvestmentThread(investment));
        }

        // counting thread
        mainThreads.add(new Thread(countingThread));
        mainThreads.get(0).start();

        // investment threads
        for(var investmentThread : investmentThreads){

            mainThreads.add(new Thread(investmentThread));

            var lastIndex = mainThreads.size() - 1;
            mainThreads.get(lastIndex).start();
        }

    }

    public static void addPlayerMoney(double money){
        gameInfo.setPlayerMoney(gameInfo.getPlayerMoney() + money);
    }

    public static double getMoney(){
        return gameInfo.getPlayerMoney();
    }

}
