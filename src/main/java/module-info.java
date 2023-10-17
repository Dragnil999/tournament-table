module com.example.tournamenttable {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tournamenttable to javafx.fxml;
    exports com.example.tournamenttable;
    exports com.example.tournamenttable.presentation;
    opens com.example.tournamenttable.presentation to javafx.fxml;
    exports com.example.tournamenttable.ui to javafx.fxml;
    opens com.example.tournamenttable.ui;
}