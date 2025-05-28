package org.jedi_bachelor.viewmodel;

import org.jedi_bachelor.view.View;

abstract public class LocalViewModel implements InteractViewModelInterface, InteractWindowsInterface {
    private View Window;

    @Override
    public void closeWindow() {
        Window.close();
    }
}
