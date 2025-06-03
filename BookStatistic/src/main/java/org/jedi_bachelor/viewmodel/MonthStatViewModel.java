package org.jedi_bachelor.viewmodel;

import org.jedi_bachelor.model.Date;
import org.jedi_bachelor.view.MonthStatView;

import java.util.Map;

public class MonthStatViewModel extends LocalViewModel {
    private final MainViewModel mvm;

    public MonthStatViewModel(MainViewModel _mvm) {
        this.mvm = _mvm;

        this.window = new MonthStatView();
    }

    public Map<Date, Integer> getStatistic() {
        return mvm.getStatisticStat();
    }
}
