package com.pytka.adventurecapitalistremake.controllers;

import com.pytka.adventurecapitalistremake.applogic.Game;
import com.pytka.adventurecapitalistremake.Main;
import com.pytka.adventurecapitalistremake.guicomponents.ParentLoader;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;

import java.util.Map;

public class MainPageController {

    @FXML
    BorderPane mainPage;

    @FXML
    private void initialize(){


        mainPage.setCenter(ParentLoader.loadParent(Main.class, "scenes/MainPageCenter.fxml"));
      //  mainPage.setLeft(ParentLoader.loadParent(Main.class, "scenes/MainPageLeft.fxml"));
      //  mainPage.setTop(ParentLoader.loadParent(Main.class, "scenes/MainPageTop.fxml"));

//
    }




}
