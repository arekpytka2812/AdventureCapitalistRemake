package com.pytka.adventurecapitalistremake.guicomponents;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.Objects;

public class ParentLoader {

    public static <T> Parent loadParent(Class<T> clazz, String fileName){

        Parent parent = null;

        try{
            parent = FXMLLoader.load(Objects.requireNonNull(clazz.getResource(fileName)));
        }
        catch(IOException e){
            e.printStackTrace();
        }

        return parent;
    }
}
