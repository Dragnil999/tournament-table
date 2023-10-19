package com.example.tournamenttable.presentation;

import com.example.tournamenttable.presentation.state.StandingsState;
import javafx.beans.property.SimpleObjectProperty;

public class StandingsViewModel {
    private final SimpleObjectProperty<StandingsState> _state = new SimpleObjectProperty<>();
    private int size;

    public SimpleObjectProperty<StandingsState> getState() {
        return _state;
    }

    public void setInitialState() {
        _state.setValue(StandingsState.INITIAL);
    }

    public int getSize() {
        return size;
    }

    public void setSize(String size) {
        try {
            int val = Integer.parseInt(size);
            if (val > 0) {
                this.size = val;
                _state.setValue(StandingsState.CONTENT);
            } else {
                _state.setValue(StandingsState.ERROR);
            }
        } catch (Exception e) {
            _state.setValue(StandingsState.ERROR);
        }
    }
}