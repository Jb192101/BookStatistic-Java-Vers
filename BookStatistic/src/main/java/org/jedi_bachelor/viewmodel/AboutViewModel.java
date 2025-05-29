package org.jedi_bachelor.viewmodel;

import org.jedi_bachelor.view.AboutWindow;
import org.jedi_bachelor.view.View;

public class AboutViewModel implements InteractWindowsInterface {
    private final View aboutWindow;

    public AboutViewModel() {
        this.aboutWindow = new AboutWindow();
    }

    @Override
    public void openWindow() {
        aboutWindow.show();
    }

    @Override
    public void closeWindow() {
        aboutWindow.close();
    }
}
