package org.jedi_bachelor.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;

import org.jedi_bachelor.model.Book;
import org.jedi_bachelor.model.Date;
import org.jedi_bachelor.utils.LocaleManager;
import org.jedi_bachelor.viewmodel.MainViewModel;

public class MainWindow extends View {
    private final MainViewModel mvm;
    private BorderPane root;
    private ObservableList<Book> data;

    public MainWindow(MainViewModel _mvm) {
        this.mvm = _mvm;
        setupUI();
    }

    private void setupUI() {
        root = new BorderPane();

        createTopPanel();
        createTableView();
        createButtonPanel();
        createBottomPanel();

        Scene scene = new Scene(root, 850, 600);
        //scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        root.getStyleClass().add("root");

        setTitle(LocaleManager.getString("APPLICATION_TITLE"));
        initStyle(StageStyle.UTILITY);
        setScene(scene);
        setResizable(false);
    }

    private void createTopPanel() {
        HBox topPanel = new HBox();
        topPanel.setPadding(new Insets(10));
        topPanel.setAlignment(Pos.TOP_RIGHT);

        Button settingsButton = new Button(LocaleManager.getString("SETTINGS_BUTTON"));
        settingsButton.setOnAction(e ->
                mvm.openSettingsWindow()
        );

        Button aboutButton = new Button(LocaleManager.getString("ABOUT_PROJECT_BUTTON"));
        aboutButton.setOnAction(e ->
                mvm.openAboutWindow()
        );

        topPanel.getChildren().addAll(settingsButton, aboutButton);
        root.setTop(topPanel);
    }

    private void createTableView() {
        TableView<Book> table = new TableView<>();

        TableColumn<Book, Integer> idColumn = new TableColumn<>(LocaleManager.getString("ID_BOOK_LABEL"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Book, String> titleColumn = new TableColumn<>(LocaleManager.getString("TITLE_BOOK"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("nameOfBook"));

        TableColumn<Book, String> authorColumn = new TableColumn<>("Авторство книги");
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("authorOfBook"));

        TableColumn<Book, Integer> pagesReadColumn = new TableColumn<>("Прочитано страниц");
        pagesReadColumn.setCellValueFactory(new PropertyValueFactory<>("completePages"));

        TableColumn<Book, Integer> totalPagesColumn = new TableColumn<>("Всего страниц");
        totalPagesColumn.setCellValueFactory(new PropertyValueFactory<>("allPages"));

        TableColumn<Book, Date> startDateColumn = new TableColumn<>("Дата начала чтения");
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));

        TableColumn<Book, Date> endDateColumn = new TableColumn<>("Дата окончания чтения");
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("finishDate"));

        TableColumn<Book, Float> procColumn = new TableColumn<>("% прочитан.");
        procColumn.setCellValueFactory(new PropertyValueFactory<>("procentOfReaded"));

        table.getColumns().add(idColumn);
        table.getColumns().add(titleColumn);
        table.getColumns().add(authorColumn);
        table.getColumns().add(pagesReadColumn);
        table.getColumns().add(totalPagesColumn);
        table.getColumns().add(startDateColumn);
        table.getColumns().add(endDateColumn);
        table.getColumns().add(procColumn);

        // Заполнение таблицы
        data = FXCollections.observableArrayList();
        mvm.fillingTable(data);
        table.setItems(data);

        root.setCenter(table);
    }

    private void createButtonPanel() {
        HBox buttonPanel = new HBox(10);
        buttonPanel.setPadding(new Insets(10));
        buttonPanel.setAlignment(Pos.CENTER);

        Button addButton = new Button(LocaleManager.getString("ADD_BOOK_BUTTON"));
        Button editButton = new Button(LocaleManager.getString("CHANGE_BOOK_BUTTON"));
        Button statMonthButton = new Button(LocaleManager.getString("STAT_READING_BUTTON"));
        Button statTempsButton = new Button(LocaleManager.getString("TEMPS_READING_BUTTON"));

        addButton.setOnAction(e -> {
            mvm.openInputDataWindow();
            mvm.fillingTable(data);});

        editButton.setOnAction(e -> {
            mvm.openInputIndexWindow();
            mvm.fillingTable(data);
        });

        buttonPanel.getChildren().addAll(addButton, editButton, statMonthButton, statTempsButton);

        VBox bottomContainer = new VBox(buttonPanel);
        root.setBottom(bottomContainer);
    }

    private void createBottomPanel() {
        HBox bottomPanel = new HBox();
        bottomPanel.setPadding(new Insets(10));
        bottomPanel.setAlignment(Pos.BOTTOM_RIGHT);

        Label booksCountLabel = new Label(LocaleManager.getString("ALL_READED_BOOKS_LABEL") + mvm.getCountCompleteBooks());
        bottomPanel.getChildren().add(booksCountLabel);

        ((VBox) root.getBottom()).getChildren().add(bottomPanel);
    }
}