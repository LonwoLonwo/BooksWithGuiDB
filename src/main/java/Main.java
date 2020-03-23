import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import javax.swing.*;
import java.sql.*;
import java.util.TreeMap;

public class Main {
    private static String url = "jdbc:mysql://localhost:3306/gui_books";
    private static String user = "root";
    private static String pass = "root02";
    private static TreeMap<String, Book> books = new TreeMap<>();
    static String authorName = "";
    static int pubYear = 0;

    public static void main(String[] args) {
        doVisible();
        try {
            getBooksFromDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static ResultSet getBooksFromDB() throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, user, pass);
             Statement statement = connection.createStatement()) {

            /*statement.execute("drop table IF EXISTS Books");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Books (id MEDIUMINT NOT NULL AUTO_INCREMENT, name CHAR(30) NOT NULL, author CHAR(40) NOT NULL, publishing_year INT, PRIMARY KEY (id))");
            statement.executeUpdate("insert into Books (name, author, publishing_year) values('Shantaram', 'Gregory David Roberts', 2003)");
            statement.executeUpdate("insert into Books (name, author, publishing_year) values('The Grapes of Wrath', 'John Steinbeck', 1939)");
            statement.executeUpdate("insert into Books (name, author, publishing_year) values('Steppenwolf', 'Hermann Hesse', 1927)");
            statement.executeUpdate("insert into Books (name, author, publishing_year) values('The Man Who Laughs', 'Victor Hugo', 1869)");
            statement.executeUpdate("insert into Books (name, author, publishing_year) values('Flowers for Algernon', 'Daniel Keyes', 1966)");*/

            ResultSet resultSet = statement.executeQuery("SELECT * FROM Books");
            while(resultSet.next()) {
                Book book = new Book();
                String bookName = resultSet.getString("name");
                book.setName(bookName);
                book.setAuthor(resultSet.getString("author"));
                book.setPublishingYear(resultSet.getInt("publishing_year"));
                books.put(bookName, book);
            }
            RowSetFactory factory = RowSetProvider.newFactory();
            return factory.createCachedRowSet();
        }
    }

    private static void doVisible(){
        JFrame frame = new JFrame();
        frame.setSize(400, 250);
        frame.add(new MainForm().getMainPanel());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    static void getResponseFromDB(String bookName){
        try {
            ResultSet resultSet = getBooksFromDB();
            CachedRowSet cachedRowSet = (CachedRowSet) resultSet;
            cachedRowSet.setUrl(url);
            cachedRowSet.setUsername(user);
            cachedRowSet.setPassword(pass);
            cachedRowSet.setCommand("SELECT * FROM Books WHERE name = ?");
            cachedRowSet.setString(1, bookName);
            cachedRowSet.execute();
            if (cachedRowSet.next()) {
                authorName = cachedRowSet.getString("author");
                pubYear = cachedRowSet.getInt("publishing_year");
            }
            else{
                authorName = "Not found";
                pubYear = -1;
            }
            cachedRowSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void booksFind(String bookName){
        if(books.containsKey(bookName)){
            Book book = books.get(bookName);
            authorName = book.getAuthor();
            pubYear = book.getPublishingYear();
        }
        else{
            authorName = "Not found";
            pubYear = 0;
        }
    }
}
