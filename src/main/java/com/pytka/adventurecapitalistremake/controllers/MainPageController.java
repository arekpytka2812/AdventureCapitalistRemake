package com.pytka.adventurecapitalistremake.controllers;

import com.pytka.adventurecapitalistremake.applogic.Game;
import com.pytka.adventurecapitalistremake.applogic.Main;
import com.pytka.adventurecapitalistremake.guicomponents.ParentLoader;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class MainPageController {

    @FXML
    BorderPane mainPage;

    private static Map<String, ProgressBar> progressBarsMap;

    @FXML
    private void initialize(){

        progressBarsMap = Game.getProgressBarsMap();

        mainPage.setCenter(ParentLoader.loadParent(Main.class, "scenes/MainPageCenter.fxml"));
      //  mainPage.setLeft(ParentLoader.loadParent(Main.class, "scenes/MainPageLeft.fxml"));
      //  mainPage.setTop(ParentLoader.loadParent(Main.class, "scenes/MainPageTop.fxml"));

//
    }




}
