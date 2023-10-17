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
    }
    public void updateTable(int width, int height) {
        table = new TextField[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                table[i][j] = new TextField();
            }
        }
        setTableToNode();
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
