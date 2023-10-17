package com.example.tournamenttable.ui;

import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class StandingsTable {

    private VBox root;
    private TextField[][] table;

    public StandingsTable() {
        root = new VBox();
        updateTable(4, 4);
        setListeners();
    }

    public void updateTable(int width, int height) {
        table = new TextField[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                table[i][j] = new TextField();
                if (i == j) {
                    table[i][j].setDisable(true);
                }
            }
        }
        setTableToNode();
    }

    private void setListeners() {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                final TextField field = table[j][i];
                table[i][j].textProperty().addListener((observableValue, oldValue, newValue) -> {
                    field.setText(rotateString(newValue));
                });
            }
        }
    }

    private String rotateString(String text) {
        String[] buff = text.split(":");
        if (buff.length == 1) {
            return text;
        }
        return buff[1] + ":" + buff[0];
    }

    private void setTableToNode() {
        HBox[] row = new HBox[table.length];
        for (int i = 0; i < table.length; i++) {
            row[i] = new HBox(table[i]);
        }
        root.getChildren().addAll(row);
    }

    public VBox getRoot() {
        return root;
    }
}
