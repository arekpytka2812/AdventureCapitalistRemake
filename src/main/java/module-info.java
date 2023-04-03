module com.pytka.adventurecapitalistremake {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires lombok;

    exports com.pytka.adventurecapitalistremake.controllers;
    opens com.pytka.adventurecapitalistremake.controllers to javafx.fxml;

    exports com.pytka.adventurecapitalistremake.applogic;
    opens com.pytka.adventurecapitalistremake.applogic to javafx.fxml;

    exports com.pytka.adventurecapitalistremake.guicomponents;
    opens com.pytka.adventurecapitalistremake.guicomponents to javafx.fxml;

    exports com.pytka.adventurecapitalistremake.utils;
    opens com.pytka.adventurecapitalistremake.utils to javafx.fxml;
}