package com.pytka.adventurecapitalistremake.applogic;

import com.pytka.adventurecapitalistremake.utils.FileParser;

import java.util.ArrayList;
import java.util.List;


public class SessionManager {

    private static SessionManager sessionManager = null;
    private static boolean isManagerSet = false;
    private static boolean isSessionOpened = false;

    private static double playerMoney;

    private FileParser fileParser = null;

    private List<GameEntity> entities;

    private SessionManager(FileParser fileParser){
        this.fileParser = fileParser;
    }

    public static SessionManager getInstance(){
        return sessionManager;
    }

    public static void setupManager(FileParser fileParser){
        if(isManagerSet){
            return;
        }

        sessionManager = new SessionManager(fileParser);
        isManagerSet = true;
    }

    public void openSession(){

        if(isSessionOpened){
            return;
        }

        entities = new ArrayList<>();

        //TODO: load game info from file and create game

        entities.add(new GameEntity("test1", 1, 3, 0.5, true));
       // entities.add(new GameEntity("test2", 10, 6, 500, true));
        playerMoney = 0;

        isSessionOpened = true;
    }

    public void closeSession(){

        if(!isSessionOpened){
            return;
        }

        //TODO: write game status and info, close file stream

        isSessionOpened = false;
    }

    public List<GameEntity> getEntities(){
        return entities;
    }

    public double getPlayerMoney(){
        return playerMoney;
    }


    public void addPlayerMoney(double money){
        playerMoney += money;
    }

}
