module com.example.tournamenttable {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tournamenttable to javafx.fxml;
    exports com.example.tournamenttable;
}