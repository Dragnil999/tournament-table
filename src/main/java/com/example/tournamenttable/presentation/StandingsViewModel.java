package com.example.tournamenttable.presentation;

import javafx.beans.value.ObservableValue;
import javafx.beans.value.ObservableValueBase;
import javafx.scene.Node;

public class StandingsViewModel {
    private ObservableValue<String[][]> tableData;

    private void initTable() {
        tableData = new ObservableValueBase<>() {
            @Override
            public String[][] getValue() {
                return new String[1][1];
            }
        };
    }

    public void updateTableData(String newData, int row, int column) {
        if (tableData == null) initTable();

        //tableData[row][column] = newData;
    }

}