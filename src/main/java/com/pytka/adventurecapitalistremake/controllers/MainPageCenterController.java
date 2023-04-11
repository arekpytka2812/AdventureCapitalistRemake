package com.pytka.adventurecapitalistremake.controllers;

import com.pytka.adventurecapitalistremake.applogic.Game;
import com.pytka.adventurecapitalistremake.applogic.Investment;
import com.pytka.adventurecapitalistremake.utils.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

public class MainPageCenterController {

    @FXML
    Button runButton;

    @FXML
    Button buyButton;

    @FXML
    ProgressBar investmentProgress = null;

    private String investmentName;

    @FXML
    private void initialize(){
        investmentName = "Modes";

        var investment = Game.getInvestment(investmentName);

        if(investment == null){
            return;
        }

        investmentProgress = Game.getProgressBar(investmentName);
    }

    public void runButtonPressed(ActionEvent event){
        Game.startInvestment(investmentName);
    }

    public void buyButtonPressed(ActionEvent event){
        try{
            Game.addTask(investmentName, new Task(Investment.class.getMethod("addItems", int.class), new Object[]{10}));
        }
        catch (NoSuchMethodException e){
            e.printStackTrace();
        }
    }


}

