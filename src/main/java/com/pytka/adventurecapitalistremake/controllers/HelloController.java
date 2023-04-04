package com.pytka.adventurecapitalistremake.controllers;

import com.pytka.adventurecapitalistremake.applogic.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HelloController {

    @FXML
    public Button ent1ButtonRun;

    @FXML
    public Button ent1ButtonBuy;

    @FXML
    private void ent1ButtonRunPressed(ActionEvent event) {
        Main.SESSION.getEntities().get(0).setIsRunning(true);
        //debug print
        System.out.println("Entity Running");
    }

    @FXML
    private void ent1ButtonBuyPressed(ActionEvent event){
        Main.SESSION.getEntities().get(0).addItems(10);
        //debug print
        System.out.println("Items Added");
    }


}