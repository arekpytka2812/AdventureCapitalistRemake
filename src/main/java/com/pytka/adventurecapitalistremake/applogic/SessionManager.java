package com.pytka.adventurecapitalistremake.applogic;

import com.pytka.adventurecapitalistremake.utils.FileParser;
import com.pytka.adventurecapitalistremake.utils.ForDebugPurposes;
import com.pytka.adventurecapitalistremake.utils.GameInfo;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


public class SessionManager {

    private static SessionManager sessionManager = null;
    private static boolean isSessionOpened = false;

    private static double playerMoney;

    private GameInfo gameInfo;

    private List<Investment> investments;

    private SessionManager(){}

    public static SessionManager getInstance(){
        if(sessionManager == null){
            sessionManager = new SessionManager();
        }
        return sessionManager;
    }

    public void openSession(){

        if(isSessionOpened){
            return;
        }

        try{
            gameInfo = FileParser.readGameInfo();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();

            return;
        }

        investments = gameInfo.getInvestments();
        playerMoney = gameInfo.getPlayerMoney();

        printGameInfo();

        isSessionOpened = true;
    }

    public void closeSession(){

        if(!isSessionOpened){
            return;
        }

        //TODO: write game status and info, close file stream

        isSessionOpened = false;
    }

    public List<Investment> getInvestments(){
        return investments;
    }

    public double getPlayerMoney(){
        return playerMoney;
    }

    public void addPlayerMoney(double money){
        playerMoney += money;
    }

    @ForDebugPurposes
    private void printGameInfo() {
        for (var investment : investments) {
            System.out.println(investment.toString());
        }
    }

}
