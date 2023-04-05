package com.pytka.adventurecapitalistremake.utils;

import com.pytka.adventurecapitalistremake.applogic.Investment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class GameInfo {

    private List<Investment> investments;
    private double playerMoney;
    private LocalDateTime lastActivity;

    public GameInfo(){
        investments = new ArrayList<>();
    }

    public void addInvestment(Investment investment){
        investments.add(investment);
    }

    public void recalculateMoney(){

        LocalDateTime now = LocalDateTime.now();
        long secsFromLastActivity = ChronoUnit.SECONDS.between(lastActivity, now);

        for(var investment : investments){

            if(!investment.hasManager()){
                continue;
            }

            long doneRounds = secsFromLastActivity / investment.getWaitTime();
            playerMoney += (doneRounds * investment.getMoneyPerRound());
        }
    }
}
