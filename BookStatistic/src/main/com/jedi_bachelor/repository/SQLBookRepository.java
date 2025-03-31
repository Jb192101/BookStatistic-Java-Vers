package main.com.jedi_bachelor.repository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.com.jedi_bachelor.model.Book;

import java.sql.*;
import java.time.LocalDate;

public class SQLBookRepository implements BookRepository {
    private static final String URL = "jdbc:sqlite:books.db";
    private final ObservableList<Book> books = FXCollections.observableArrayList();

    public SQLBookRepository() {
        initializeDatabase();
        loadBooksFromDatabase();

        //books.add(new Book("а", "б", 11, 12, LocalDate.now()));
    }

    /*
    Базы данных
     */

    private void initializeDatabase() {
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {

            // Создаём таблицу, если её нет
            String sql = """
                CREATE TABLE IF NOT EXISTS books (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    title TEXT NOT NULL,
                    author TEXT,
                    total_pages INTEGER DEFAULT 0,
                    read_pages INTEGER DEFAULT 0,
                    start_date TEXT
                )""";
            stmt.execute(sql);

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при инициализации БД", e);
        }
    }

    private void loadBooksFromDatabase() {
        books.clear();

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM books")) {

            while (rs.next()) {
                Book book = new Book();
                book.setNameOfBook(rs.getString("title"));
                book.setAuthorOfBook(rs.getString("author"));
                book.setAllPages(rs.getInt("total_pages"));
                book.setCompletePages(rs.getInt("read_pages"));
                book.setStartDate(LocalDate.parse(rs.getString("start_date")));

                books.add(book);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при загрузке книг из БД", e);
        }
    }

    /*
    Методы из интерфейса BookRepository
     */

    @Override
    public void addBook(Book book) {
        String sql = "INSERT INTO books (title, author, total_pages, read_pages, start_date) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, book.getNameOfBook());
            pstmt.setString(2, book.getAuthorOfBook());
            pstmt.setInt(3, book.getAllPages());
            pstmt.setInt(4, book.getCompletePages());
            pstmt.setString(5, book.getStartDate().toString());

            pstmt.executeUpdate();
            books.add(book);

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при добавлении книги", e);
        }
    }

    @Override
    public ObservableList<Book> getAllBooks() {
        return books;
    }

    @Override
    public void updateReadPages(Book book, int pages) {
        String sql = "UPDATE books SET read_pages = ? WHERE title = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, pages);
            pstmt.setString(2, book.getNameOfBook());
            pstmt.executeUpdate();

            book.setCompletePages(pages);

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при обновлении страниц", e);
        }
    }
}
