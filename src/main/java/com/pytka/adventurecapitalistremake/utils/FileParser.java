package com.pytka.adventurecapitalistremake.utils;

import com.pytka.adventurecapitalistremake.applogic.Investment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class FileParser {

    private static GameInfo gameInfo;
    private static File gameInfoFile;

    public static GameInfo readGameInfo() throws FileNotFoundException {

        gameInfoFile = new File("bin/gameInfo.txt");

        gameInfo = new GameInfo();

        Scanner fileScanner = new Scanner(gameInfoFile);

        //TODO: change investments file type to json
        //then change scanning method

        // reading investments
        for(int i = 0; i < 1; i++){

            String investment = fileScanner.nextLine();

            String[] investmentData = investment.split(";");

            gameInfo.addInvestment(new Investment(
                    investmentData[0],
                    Long.parseLong(investmentData[1]),
                    Integer.parseInt(investmentData[2]),
                    Double.parseDouble(investmentData[3]),
                    Double.parseDouble(investmentData[4]),
                    Boolean.parseBoolean(investmentData[5]),
                    Boolean.parseBoolean(investmentData[6])));
        }

        // reading players money
        double playerMoney = Double.parseDouble(fileScanner.nextLine());
        gameInfo.setPlayerMoney(playerMoney);

        // reading last activity
        if(fileScanner.hasNextLine()){
            String lastActivityString = fileScanner.nextLine();
            LocalDateTime lastActivity = LocalDateTime.parse(lastActivityString);
            gameInfo.setLastActivity(lastActivity);
            gameInfo.recalculateMoney();
        }

        fileScanner.close();

        return gameInfo;
    }

    public static void writeGameInfo(GameInfo gameInfo) throws IOException {

        var fileWriter = new FileWriter(gameInfoFile);

        var investments = gameInfo.getInvestments();
        var money = gameInfo.getPlayerMoney();
        var now = LocalDateTime.now();

        for(var investment : investments){
            fileWriter.write(
                    investment.getNAME() + ";"
                    + investment.getItemsCount() + ";"
                    + investment.getWaitTime() + ";"
                    + investment.getMultiplier() + ";"
                    + investment.getMONEY_PER_ITEM() + ";"
                    + investment.isBought() + ";"
                    + investment.isHasManager() + "\n"
                    );
        }

        fileWriter.write(money + "\n");
        fileWriter.write(now.toString());

        fileWriter.close();
    }





}
