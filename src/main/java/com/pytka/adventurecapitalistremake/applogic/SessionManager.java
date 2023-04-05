package com.pytka.adventurecapitalistremake.applogic;

import com.pytka.adventurecapitalistremake.utils.FileParser;
import com.pytka.adventurecapitalistremake.utils.ForDebugPurposes;
import com.pytka.adventurecapitalistremake.utils.GameInfo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class SessionManager {

    private static boolean isSessionOpened = false;
    private static GameInfo gameInfo = null;

    public static void openSession(){

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

        isSessionOpened = true;
    }

    public void closeSession(){

        if(!isSessionOpened){
            return;
        }

        try {
            FileParser.writeGameInfo(gameInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }

        isSessionOpened = false;
    }

    public void setGameInfo(GameInfo gameInfo_){
        gameInfo = gameInfo_;
    }

    public static GameInfo getGameInfo(){
        return gameInfo;
    }
}
