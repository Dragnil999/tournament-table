package com.example.tournamenttable.ui;

import com.example.tournamenttable.presentation.StandingsTableViewModel;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class StandingsTable {

    private static final String COMMAND_TEXT = "Команда";
    private static final String SUMMARY_SCORE_TEXT = "Сумма очков";
    private static final String LEADERBOARD_TEXT = "Место";
    public static final String PLACE_TEXT = "Нет места";
    private final StandingsTableViewModel viewModel = StandingsTableViewModel.newInstance();
    private final HBox root;
    private final VBox tableView;
    private final VBox summaryScoreView;
    private final VBox leaderboardView;
    private TextField[][] table;

    public StandingsTable() {
        root = new HBox();
        tableView = new VBox();
        updateTable(4);
        summaryScoreView = new VBox(getSummaryScoreView());
        leaderboardView = new VBox(getLeaderboardView());
        setListeners();
    }

    public void updateTable(int size) {
        table = new TextField[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
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
                final int line = i;
                final TextField score = (TextField) summaryScoreView.getChildren().get(i + 1);
                table[i][j].textProperty().addListener((observableValue, oldValue, newValue) -> {
                    field.setText(rotateString(newValue));
                    score.setText(getSummaryScore(line));
                    viewModel.saveScore(line, Integer.parseInt(score.getText()));
                    updateSummaryScore();
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
        HBox[] row = new HBox[table.length + 1];
        row[0] = new HBox(getCommandNumbersView());
        for (int i = 1; i < table.length + 1; i++) {
            row[i] = new HBox(table[i - 1]);
        }
        tableView.getChildren().addAll(row);
    }

    private TextField getEmptyUnactiveTextField() {
        TextField emptyField = new TextField();
        emptyField.setEditable(false);
        emptyField.setFocusTraversable(false);
        return emptyField;
    }

    private TextField[] getCommandNumbersView() {
        TextField[] commandNumbers = new TextField[table.length];
        for (int i = 0; i < table.length; i++) {
            commandNumbers[i] = new TextField(Integer.toString(i + 1));
            commandNumbers[i].setEditable(false);
            commandNumbers[i].setFocusTraversable(false);
        }
        return commandNumbers;
    }

    private TextField[] getCommandTitlesView() {
        TextField[] commandTitles = new TextField[table.length + 1];
        commandTitles[0] = getEmptyUnactiveTextField();
        commandTitles[0].setText(COMMAND_TEXT);
        for (int i = 1; i < table.length + 1; i++) {
            commandTitles[i] = new TextField();
        }
        return commandTitles;
    }

    private TextField[] getSummaryScoreView() {
        TextField[] summaryScore = new TextField[table.length + 1];
        summaryScore[0] = getEmptyUnactiveTextField();
        summaryScore[0].setText(SUMMARY_SCORE_TEXT);
        for (int i = 1; i < table.length + 1; i++) {
            summaryScore[i] = getEmptyUnactiveTextField();
            summaryScore[i].setText(Integer.toString(0));
        }
        return summaryScore;
    }

    private TextField[] getLeaderboardView() {
        TextField[] leaderboard = new TextField[table.length + 1];
        leaderboard[0] = getEmptyUnactiveTextField();
        leaderboard[0].setText(LEADERBOARD_TEXT);
        for (int i = 1; i < table.length + 1; i++) {
            leaderboard[i] = getEmptyUnactiveTextField();
            leaderboard[i].setText(PLACE_TEXT);
        }
        return leaderboard;
    }

    private String getSummaryScore(int line) {
        int sum = 0;
        for (int i = 0; i < table.length; i++) {
            if (table[line][i].getText().isEmpty()) {
                sum += 0;
            } else {
                sum += Integer.parseInt(table[line][i].getText().split(":")[0]);
            }
        }
        return Integer.toString(sum);
    }

    private void updateSummaryScore() {
        for (int i = 0; i < table.length; i++) {
            TextField score = (TextField) leaderboardView.getChildren().get(i + 1);
            score.setText(viewModel.calculatePlace(i));
        }
    }

    public HBox getRoot() {
        VBox commandNumbersColumn = new VBox();
        commandNumbersColumn.getChildren().add(getEmptyUnactiveTextField());
        commandNumbersColumn.getChildren().addAll(getCommandNumbersView());
        root.getChildren().addAll(commandNumbersColumn, new VBox(getCommandTitlesView()), tableView, summaryScoreView, leaderboardView);
        return root;
    }
}
