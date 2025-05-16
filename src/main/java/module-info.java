module com.chessforge.chessforge {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;

    opens com to javafx.fxml;
    opens com.controllers to javafx.fxml;
    opens com.utils to javafx.fxml;
    opens com.scenes to javafx.fxml;

    exports com;
    exports com.controllers;
    exports com.utils;
    exports com.scenes;
}
