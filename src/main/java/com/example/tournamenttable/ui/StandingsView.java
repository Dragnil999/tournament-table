package com.example.tournamenttable.ui;

import com.example.tournamenttable.presentation.StandingsViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class StandingsView implements Initializable {

    private static final String SIZE_TITLE = "Введите кол-во команд";
    private static final String TABLE_TITLE = "Таблица рекордов";
    private static final String ERROR_TEXT = "Неверное кол-во";
    private static final String CREATE_BUTTON_TEXT = "Создать";
    private final StandingsViewModel viewModel = new StandingsViewModel();
    private StandingsTable table;
    @FXML
    private ScrollPane tableHolder;
    @FXML
    private Label tableTitle;
    @FXML
    private Label sizeTitle;
    @FXML
    private Label errorText;
    @FXML
    private TextField tableSizeTextField;
    @FXML
    private Button createTableButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initLabels();
        setObservers();
        setListeners();
        viewModel.setInitialState();
    }

    private void setListeners() {
        createTableButton.setOnAction(actionEvent -> {
            viewModel.setSize(tableSizeTextField.getText());
        });
    }

    private void setObservers() {
        viewModel.getState().addListener((observableValue, number, t1) -> {
            switch (t1) {
                case INITIAL:
                    renderInitialState();
                    break;
                case CONTENT:
                    renderTable();
                    break;
                case ERROR:
                    renderErrorState();
                    break;
            }
        });
    }

    private void renderInitialState() {
        sizeTitle.setVisible(true);
        tableSizeTextField.setVisible(true);
        createTableButton.setVisible(true);
        errorText.setVisible(false);
        tableTitle.setVisible(false);
        tableHolder.setVisible(false);
    }

    private void renderTable() {
        tableTitle.setVisible(true);
        tableHolder.setVisible(true);
        errorText.setVisible(false);
        sizeTitle.setVisible(false);
        tableSizeTextField.setVisible(false);
        createTableButton.setVisible(false);
        table = new StandingsTable(viewModel.getSize());
        tableHolder.setContent(table.getRoot());
    }

    private void renderErrorState() {
        errorText.setVisible(true);
    }

    private void initLabels() {
        createTableButton.setText(CREATE_BUTTON_TEXT);
        sizeTitle.setText(SIZE_TITLE);
        tableTitle.setText(TABLE_TITLE);
        errorText.setText(ERROR_TEXT);
    }
}
