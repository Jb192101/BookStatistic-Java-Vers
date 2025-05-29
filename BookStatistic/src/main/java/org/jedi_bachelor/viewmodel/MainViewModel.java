package org.jedi_bachelor.viewmodel;

import org.jedi_bachelor.model.Book;
import org.jedi_bachelor.model.Model;
import org.jedi_bachelor.view.MainWindow;

public class MainViewModel extends LocalViewModel {
    private Model model;

    public MainViewModel() {
        this.window = new MainWindow();
    }

    @Override
    public Book getBook() {
        return null;
    }

    @Override
    public void setBook() {

    }

    public void startApplication() {
        SplashViewModel svm = new SplashViewModel(this);
        svm.openWindow();
    }
}
