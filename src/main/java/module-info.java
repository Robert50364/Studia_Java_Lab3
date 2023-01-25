module com.robsonteam.lab3fxapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.robsonteam.lab3fxapp to javafx.fxml;
    exports com.robsonteam.lab3fxapp;
}