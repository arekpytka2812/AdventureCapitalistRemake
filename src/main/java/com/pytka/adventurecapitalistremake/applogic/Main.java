package com.pytka.adventurecapitalistremake.applogic;

import com.pytka.adventurecapitalistremake.utils.FileParser;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FileParser fileParser = new FileParser();

        SessionManager.setupManager(fileParser);
        SessionManager sessionManager = SessionManager.getInstance();
        sessionManager.openSession();

        var entitiesList = sessionManager.getEntities();

        EntityThread entityThread = new EntityThread(entitiesList.get(0));

        Thread thread = new Thread(entityThread);

        CountingThread countingThread = new CountingThread(entitiesList);

        Thread count = new Thread(countingThread);


        thread.start();
        count.start();




//        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}