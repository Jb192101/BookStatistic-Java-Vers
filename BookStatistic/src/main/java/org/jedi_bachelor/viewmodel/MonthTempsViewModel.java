package org.jedi_bachelor.viewmodel;

import org.jedi_bachelor.model.Date;
import org.jedi_bachelor.view.MonthTempsView;

import java.util.Map;

public class MonthTempsViewModel extends LocalViewModel {
    private final MainViewModel mvm;

    public MonthTempsViewModel(MainViewModel _mvm) {
        this.mvm = _mvm;

        this.window = new MonthTempsView();
    }

    public Map<Date, Integer> getStatistic() {
        return mvm.getStatisticTemps();
    }
}
