package com.pytka.adventurecapitalistremake.applogic;

import com.pytka.adventurecapitalistremake.utils.FileParser;

import java.util.List;

public class Game {

    private static Game game = null;

    private List<Investment> investments;
    private double playerMoney;


    private Game(){
        var session = SessionManager.getInstance();
        session.openSession();

        this.investments = session.getInvestments();
        this.playerMoney = 0.0;
    }

    public static Game getInstance(){
        if(game == null){
            game = new Game();
        }

        return game;
    }

    public void run(){

    }

}
