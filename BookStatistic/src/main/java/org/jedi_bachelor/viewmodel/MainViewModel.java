package org.jedi_bachelor.viewmodel;

import org.jedi_bachelor.model.Book;
import org.jedi_bachelor.model.Model;

public class MainViewModel extends LocalViewModel {
    private Model model;

    @Override
    public Book getBook() {
        return null;
    }

    @Override
    public void setBook() {

    }

    @Override
    public void openWindow() {

    }

    @Override
    public void closeWindow() {

    }

    public void startApplication() {
        SplashViewModel svm = new SplashViewModel(this);
        svm.openWindow();
    }
}
