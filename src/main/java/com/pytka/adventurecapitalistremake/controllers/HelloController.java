package com.pytka.adventurecapitalistremake.controllers;

import com.pytka.adventurecapitalistremake.applogic.Game;
import com.pytka.adventurecapitalistremake.applogic.Investment;
import com.pytka.adventurecapitalistremake.utils.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HelloController {

    @FXML
    public Button ent1ButtonRun;

    @FXML
    public Button ent1ButtonBuy;

    private final String investmentName = "Modes";

    @FXML
    private void ent1ButtonRunPressed(ActionEvent event) {
        try{
            Game.addTask(investmentName, new Task(Investment.class.getMethod("setIsRunning", boolean.class), new Object[]{true}));
        }
        catch (NoSuchMethodException e){
            e.printStackTrace();
        }

        System.out.println("Investment Running");
    }

    @FXML
    private void ent1ButtonBuyPressed(ActionEvent event){
        try{
            Game.addTask(investmentName, new Task(Investment.class.getMethod("addItems", int.class), new Object[]{10}));
        }
        catch (NoSuchMethodException e){
            e.printStackTrace();
        }
        System.out.println("Items Added");
    }


}