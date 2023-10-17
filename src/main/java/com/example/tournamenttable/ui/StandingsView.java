package com.example.tournamenttable.ui;

import com.example.tournamenttable.presentation.StandingsViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class StandingsView implements Initializable {

    private final StandingsViewModel viewModel = new StandingsViewModel();
    private StandingsTable table;
    @FXML
    private AnchorPane root;
    @FXML
    private ScrollPane tableHolder;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        table = new StandingsTable();
        tableHolder.setContent(table.getRoot());
    }
}
