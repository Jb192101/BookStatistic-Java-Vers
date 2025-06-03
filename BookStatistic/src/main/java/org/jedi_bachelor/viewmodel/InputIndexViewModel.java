package org.jedi_bachelor.viewmodel;

import org.jedi_bachelor.view.InputIndexWindow;

public class InputIndexViewModel extends LocalViewModel {
    private final MainViewModel mvm;

    public InputIndexViewModel(MainViewModel _mvm) {
        this.mvm = _mvm;

        this.window = new InputIndexWindow(this);
    }

    public void setIndex(int _index) {
        mvm.openChangeWindow(_index);
    }
}
