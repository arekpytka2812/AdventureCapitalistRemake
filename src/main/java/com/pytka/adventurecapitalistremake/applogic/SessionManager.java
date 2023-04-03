package com.pytka.adventurecapitalistremake.applogic;

import com.pytka.adventurecapitalistremake.utils.FileParser;


public class SessionManager {

    private static SessionManager sessionManager = null;
    private static boolean isManagerSet = false;
    private static boolean isSessionOpened = false;

    private FileParser fileParser = null;

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

    public static void openSession(){

        if(isSessionOpened){
            return;
        }

        //TODO: load game info from file and create game

        isSessionOpened = true;
    }

    public static void closeSession(){

        if(!isSessionOpened){
            return;
        }

        //TODO: write game status and info, close file stream

        isSessionOpened = false;
    }

}
