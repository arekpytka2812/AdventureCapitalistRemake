package com.pytka.adventurecapitalistremake.controllers;

import com.pytka.adventurecapitalistremake.applogic.Game;
import com.pytka.adventurecapitalistremake.applogic.Investment;
import com.pytka.adventurecapitalistremake.utils.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;

//TODO: extract this into own entity

public class MainPageCenterController {

    @FXML
    Button runButton;

    @FXML
    Button buyButton;

    @FXML
    ProgressBar investmentProgress;

    private String investmentName;

    @FXML
    private void initialize(){

        //TODO: pass the investment name to controller
        investmentName = "Modes";

        var investment = Game.getInstance().getInvestment(investmentName);

        if(investment == null){
            return;
        }

        Game.getInstance().putBarAndBindProgress(investmentName, investmentProgress);

    }

    public void runButtonPressed(ActionEvent event){
        Game.getInstance().startInvestment(investmentName);
    }

    public void buyButtonPressed(ActionEvent event){
        try{
            Game.getInstance().addTask(investmentName, new Task(Investment.class.getMethod("addItems", int.class), new Object[]{10}));
        }
        catch (NoSuchMethodException e){
            e.printStackTrace();
        }
    }


}

