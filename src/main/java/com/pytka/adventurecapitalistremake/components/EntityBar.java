package com.pytka.adventurecapitalistremake.components;

import com.pytka.adventurecapitalistremake.applogic.Investment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;

public class EntityBar {

    private Investment investment;

    @FXML
    ProgressBar investmentProgress;

    @FXML
    Button runButton;

    @FXML
    Button buyButton;

    @FXML
    private void initialize(Investment investment){
        this.investment = investment;
    }

    @FXML
    private void runButtonPressed(ActionEvent event) {
        investment.setIsRunning(true);
        //debug print
        System.out.println("Entity Running");
    }

    @FXML
    private void buyButtonPressed(ActionEvent event){
        investment.addItems(10);
        //debug print
        System.out.println("Items Added");
    }
}
