package com.pytka.adventurecapitalistremake.utils;

import com.pytka.adventurecapitalistremake.applogic.Investment;
import lombok.Setter;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

@Setter
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

        // reading last activity
        String lastActivityString = fileScanner.nextLine();
        LocalDateTime lastActivity = LocalDateTime.parse(lastActivityString);

        gameInfo.setPlayerMoney(playerMoney);
        gameInfo.setLastActivity(lastActivity);

        gameInfo.recalculateMoney();

        return gameInfo;

    }

    public static void writeGameInfo(){

    }





}
