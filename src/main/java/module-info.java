module com.chessforge.chessforge {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.chessforge.chessforge to javafx.fxml;
    exports com.chessforge.chessforge;
}