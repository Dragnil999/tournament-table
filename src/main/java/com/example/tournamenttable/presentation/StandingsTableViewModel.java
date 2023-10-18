package com.example.tournamenttable.presentation;

import com.example.tournamenttable.ui.StandingsTable;

import java.util.*;

public class StandingsTableViewModel {
    private static StandingsTableViewModel viewModel;
    private StandingsTableViewModel() {  }

    public static StandingsTableViewModel newInstance() {
        if (viewModel == null) {
            viewModel = new StandingsTableViewModel();
        }
        return viewModel;
    }

    private final Map<Integer, Integer> commandsScore = new HashMap<>();

    public void saveScore(int line, int value) {
        commandsScore.put(line, value);
    }

    public String calculatePlace(int line) {
        List<Integer> score = new ArrayList<>(commandsScore.values());
        Collections.sort(score);
        for (int i = score.size() - 1; i >= 0; i--) {
            if (score.get(i).equals(commandsScore.get(line))) {
                return Integer.toString(score.size() - i);
            }
        }
        return StandingsTable.PLACE_TEXT;
    }

}
