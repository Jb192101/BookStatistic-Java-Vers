package org.jedi_bachelor.viewmodel;

import org.jedi_bachelor.view.View;

abstract public class LocalViewModel implements InteractWindowsInterface {
    protected View window;

    @Override
    public void openWindow() {
        window.show();
    }

    @Override
    public void closeWindow() {
        window.close();
    }
}
