package helpers;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.rubezhanskii.javabookshop.model.Book;
import ua.rubezhanskii.javabookshop.model.Category;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

import static junit.framework.Assert.*;

public class BookJdbcTest {

    private Connection connection;
    private Statement statement;


    @Before
    public void setUpDataBase() throws Exception{
        Class.forName("org.h2.Driver");
        connection= DriverManager.getConnection("jdbc:h2:~/testDb","sa","");
        statement=connection.createStatement();
        statement.execute(
                "CREATE TABLE book\n" +
                        "(\n" +
                        "  bookId         SERIAL PRIMARY KEY,\n" +
                        "  coverImage     VARCHAR(30)    NULL,\n" +
                        "  price          DOUBLE         NULL,\n" +
                        "  bookTitle      VARCHAR(250)   NULL,\n" +
                        "  categoryId     INT            NULL,\n" +
                        "  publisher      VARCHAR(30)    NULL,\n" +
                        "  ISBN           VARCHAR(30)    NULL,\n" +
                        "  lang           VARCHAR(20)    NULL,\n" +
                        "  details        VARCHAR(20000) NULL,\n" +
                        "  authorId       INT            NULL,\n" +
                        "  bookQuantity   INT            NULL,\n" +
                        "  inventoryStock INT            NULL\n" +
                        ");"
        );
        statement.execute(
                "INSERT INTO book (bookId, coverImage, price, bookTitle, categoryId, publisher, ISBN, lang, details, authorId, bookQuantity, inventoryStock) VALUES\n" +
                        "  (1, 'ISBN 978-1-4842-0793-2', 37.95, 'Pivotal Certified Spring Enterprise Integration Specialist Exam A Study Guide', 1, 'Apress', 'ISBN 978-1-4842-0793-2', 'English', ' ', 1, 1, NULL),\n" +
                        "  (2, 'ISBN 978-1-4842-1224-0', 29.99, 'Spring Boot Messaging Messaging APIs for Enterprise and Integration Solutions', 1, 'Apress', 'ISBN 978-1-4842-1224-0', 'English', ' ', 2, 1, NULL),\n" +
                        "  (3, 'ISBN 978-1-4842-1240-0', 37.95, 'Spring Cloud Data Flow Native Cloud Orchestration Services for Microservice Applications on Modern Runtimes', 1, 'Apress', 'ISBN 978-1-4842-1240-0', 'English', ' ', 3, 1, NULL),\n" +
                        "  (4, 'ISBN 978-1-4842-1431-2', 33.95, 'Pro Spring Boot', 1, 'Apress', 'ISBN 978-1-4842-1431-2', 'English', ' ', 4, 1, NULL),\n" +
                        "  (5, 'ISBN 978-1-4842-3165-4', 15.46, 'Learn Microservices with Spring Boot A Practical Approach to RESTful Services using RabbitMQ, Eureka, Ribbon, Zuul and Cucumber', 1, 'Apress', 'ISBN 978-1-4842-3165-4', 'English', ' ', 5, 1, NULL),\n" +
                        "  (6, 'ISBN 978-1-4842-1802-0', 24.99, 'Java Design Patterns', 2, 'Apress', 'ISBN 978-1-4842-1802-0', 'English', ' ', 6, 1, NULL),\n" +
                        "  (7, 'ISBN 978-1-4842-2544-8', 20.22, 'Interactive Object Oriented Programming in Java Learn and Test Your Skills', 2, 'Apress', 'ISBN 978-1-4842-2544-8', 'English', ' ', 7, 1, NULL),\n" +
                        "  (8, 'ISBN 978-1-4842-2979-8', 36.99, 'Troubleshooting Java Performance Detecting Anti-Patterns with Open Source Tools', 2, 'Apress', 'ISBN 978-1-4842-2979-8', 'English', ' ', 8, 1, NULL),\n" +
                        "  (9, 'ISBN 978-1-4842-3153-1', 29.74, 'Software Development, Design and Coding With Patterns, Debugging, Unit Testing, and Refactoring', 2, 'Apress', 'ISBN 978-1-4842-3153-1', 'English', ' ', 9, 1, NULL),\n" +
                        "  (10, 'ISBN 978-1-4302-5999-2', 37.95, 'Java Closures and Lambda', 3, 'Apress', 'ISBN 978-1-4302-5999-2', 'English', ' ', 10, 1, NULL),\n" +
                        "  (11, 'ISBN 978-1-4842-0641-6', 44.95, 'Pro Java 8 Programming', 3, 'Apress', 'ISBN 978-1-4842-0641-6', 'English', ' ', 11, 1, NULL),\n" +
                        "  (12, 'ISBN 978-1-4842-0980-6', 18.95, 'Stripes by Example', 3, 'Apress', 'ISBN 978-1-4842-0980-6', 'English', ' ', 12, 1, NULL),\n" +
                        "  (13, 'ISBN 978-1-4842-1142-7', 44.95, 'Learn JavaFX 8 Building User Experience and Interfaces with Java 8', 3, 'Apress', 'ISBN 978-1-4842-1142-7', 'English', ' ', 13, 1, NULL),\n" +
                        "  (14, 'ISBN 978-1-4842-1565-4', 39.99, 'Java I/O, NIO and NIO.2', 3, 'Apress', 'ISBN 978-1-4842-1565-4', 'English', ' ', 14, 1, NULL),\n" +
                        "  (15, 'ISBN 978-1-4842-1976-8', 35.69, 'Java 9 Recipes, A Problem-Solution Approach', 3, 'Apress', 'ISBN 978-1-4842-1976-8', 'English', ' ', 15, 1, NULL),\n" +
                        "  (16, 'ISBN 978-1-4842-2487-8', 14.99, 'Java Lambdas and Parallel Streams', 3, 'Apress', 'ISBN 978-1-4842-2487-8', 'English', ' ', 16, 1, NULL),\n" +
                        "  (17, 'ISBN 978-1-4842-2592-9', 29.74, 'Java 9 Revealed', 3, 'Apress', 'ISBN 978-1-4842-2592-9', 'English', ' ', 17, 1, NULL),\n" +
                        "  (18, 'ISBN 978-1-4842-2713-8', 24.99, 'Java 9 Modularity Revealed Project Jigsaw and Scalable Java Applications', 3, 'Apress', 'ISBN 978-1-4842-2713-8', 'English', ' ', 18, 1, NULL),\n" +
                        "  (19, 'ISBN 978-1-4842-2902-6', 32.99, 'Beginning Java 9 Fundamentals Arrays, Objects, Modules, JShell, and Regular Expressions', 3, 'Apress', 'ISBN 978-1-4842-2902-6', 'English', ' ', 19, 1, NULL),\n" +
                        "  (20, 'ISBN 978-1-4842-3042-8', 35.69, 'Pro JavaFX 9 A Definitive Guide to Building Desktop, Mobile, and Embedded Java Clients', 3, 'Apress', 'ISBN 978-1-4842-3042-8', 'English', ' ', 20, 1, NULL),\n" +
                        "  (21, 'ISBN 978-1-4842-1700-9', 19.99, 'Java Threads and the Concurrency Utilities', 4, 'Apress', 'ISBN 978-1-4842-1700-9', 'English', ' ', 21, 1, NULL),\n" +
                        "  (22, 'ISBN 978-1-4842-1428-2', 16.99, 'Reactive Java Programming', 6, 'Apress', 'ISBN 978-1-4842-1428-2', 'English', ' ', 22, 1, NULL),\n" +
                        "  (23, 'ISBN 978-1-4842-1916-4', 24.99, 'Java XML and JSON', 6, 'Apress', 'ISBN 978-1-4842-1916-4', 'English', ' ', 23, 1, NULL),\n" +
                        "  (24, 'ISBN 978-1-4842-2520-2', 32.12, 'Building Trading Bots Using Java', 6, 'Apress', 'ISBN 978-1-4842-2520-2', 'English', ' ', 24, 1, NULL),\n" +
                        "  (25, 'ISBN 978-1-4842-2985-9', 29.74, 'Pro Java Clustering and Scalability Building Real-Time Apps with Spring, Cassandra, Redis, WebSocket and RabbitMQ', 6, 'Apress', 'ISBN 978-1-4842-2985-9', 'English', ' ', 25, 1, NULL),\n" +
                        "  (26, 'ISBN 978-1-4842-3030-5', 34.99, 'Practical JSF in Java EE 8 Web Applications \u200Bin Java for the Enterprise', 6, 'Apress', 'ISBN 978-1-4842-3030-5', 'English', ' ', 26, 1, NULL),\n" +
                        "  (27, 'ISBN 978-1-4842-3330-6', 26.99, 'Exploring Java 9 Build Modularized Applications in Java', 6, 'Apress', 'ISBN 978-1-4842-3330-6', 'English', ' ', 27, 1, NULL),\n" +
                        "  (28, 'ISBN 978-1-4842-2490-8', 15.46, 'Modern Programming Made Easy Using Java, Scala, Groovy, and JavaScript', 8, 'Apress', 'ISBN 978-1-4842-2490-8', 'English', ' ', 28, 1, NULL),\n" +
                        "  (29, 'ISBN 978-1-4842-3036-7', 35.69, 'Practical Scala DSLs Real-World Applications Using Domain Specific Languages', 8, 'Apress', 'ISBN 978-1-4842-3036-7', 'English', ' ', 29, 1, NULL),\n" +
                        "  (30, 'ISBN 978-1-4842-3108-1', 19.99, 'Scala for Java Developers A Practical Primer', 8, 'Apress', 'ISBN 978-1-4842-3108-1', 'English', ' ', 30, 1, NULL),\n" +
                        "  (31, 'ISBN 978-1-4842-0823-6', 37.95, 'Spring REST', 9, 'Apress', 'ISBN 978-1-4842-0823-6', 'English', ' ', 31, 1, NULL),\n" +
                        "  (32, 'ISBN 978-1-4842-2790-9', 44.99, 'Spring 5 Recipes A Problem-Solution Approach', 1, 'Apress', 'ISBN 978-1-4842-2790-9', 'English', ' ', 32, 1, NULL),\n" +
                        "  (33, 'ISBN 978-1-4842-2808-1', 35.69, 'Pro Spring 5 An In-Depth Guide to the Spring Framework and Its Tools', 1, 'Apress', 'ISBN 978-1-4842-2808-1', 'English', ' ', 33, 1, NULL),\n" +
                        "  (34, 'ISBN 978-1-4842-2931-6', 29.74, 'Beginning Spring Boot 2 Applications and Microservices with the Spring Framework', 1, 'Apress', 'ISBN 978-1-4842-2931-6', 'English', ' ', 34, 1, NULL),\n" +
                        "  (35, 'ISBN 978-1-4842-1961-4', 29.74, 'JavaFX 9 by Example', 3, 'Apress', 'ISBN 978-1-4842-1961-4', 'English', ' ', 35, 1, NULL),\n" +
                        "  (36, 'ISBN 978-1-4842-0127-5', 29.95, 'Hibernate Recipes A Problem-Solution Approach', 5, 'Apress', 'ISBN 978-1-4842-0127-5', 'English', ' ', 36, 1, NULL),\n" +
                        "  (37, 'ISBN 978-1-4842-0268-5', 29.99, 'Spring Persistence with Hibernate', 5, 'Apress', 'ISBN 978-1-4842-0268-5', 'English', ' ', 37, 1, NULL),\n" +
                        "  (38, 'ISBN 978-1-4842-3419-8', 46.99, 'Pro JPA 2 in Java EE 8 An In-Depth Guide to Java Persistence APIs', 5, 'Apress', 'ISBN 978-1-4842-3419-8', 'English', ' ', 38, 1, NULL),\n" +
                        "  (39, 'ISBN 978-1-4842-3195-1', 15.46, 'Java EE Web Application Primer Building Bullhorn: A Messaging App with JSP, Servlets, JavaScript, Bootstrap and Oracle', 6, 'Apress', 'ISBN 978-1-4842-3195-1', 'English', ' ', 39, 1, NULL),\n" +
                        "  (40, NULL, 50.2, 'test', 11, 'test', 'test', 'test', 'test', 42, NULL, NULL);"
        );
    }




    @Test
    public void update_Book_Should_update_7_Rows() throws Exception{

        QueryRunner runner=new QueryRunner();
        String testQuery="update book set bookTitle=? where bookId<=?";
        int updatedRows=runner.update(connection,testQuery,"testBook",7);
        assertEquals(updatedRows,7);
    }
    @Test
    public void delete_Book_Should_delete_Seven_Rows() throws Exception{

        QueryRunner runner=new QueryRunner();
        String testQuery="delete from book where bookId<=?";
        int deletedRows=runner.update(connection,testQuery,"7");
        assertEquals(deletedRows,7);
    }

    @Test
    public void getAllBooks_With_correct_Fields() throws Exception{

        QueryRunner runner=new QueryRunner();
        String query="select * from book";
        List<Book> books= (List<Book>)runner.query(connection,query,new BookRowHandler(connection));
        assertEquals((int)books.get(7).getBookId(),8);
        assertEquals(books.get(7).getISBN(),"ISBN 978-1-4842-2979-8");
        assertEquals(books.size(),40);
    }

    @Test
    public void save_Books_Should_save_Only_One_Row() throws Exception{

        QueryRunner runner=new QueryRunner();
        String testQuery="INSERT INTO book(coverImage, authorId, price, bookTitle, categoryId,  publisher, ISBN, lang, details,inventoryStock) VALUES (?,?,?,?,?,?,?,?,?,?)";
        Book book=new Book(50,"coverTest",5.0,"TestTitle",null,"testPub","testIsbn","testLang","testDetails",null,1,2);
        int rowsInserted=runner.update(connection,testQuery,book.getCoverImage(),15,book.getPrice(),book.getBookTitle(),89,book.getPublisher(),book.getISBN(),book.getLanguage(),book.getDetails(),book.getInventoryStock());
        assertEquals(rowsInserted,1);
    }
    @Test
    public void save_Books_Should_save_with_proper_ID() throws Exception{

        QueryRunner runner=new QueryRunner();
        String testQuery="INSERT INTO book(coverImage, authorId, price, bookTitle, categoryId,  publisher, ISBN, lang, details,inventoryStock) VALUES (?,?,?,?,?,?,?,?,?,?)";
        Book book=new Book(50,"coverTest",5.0,"TestTitle",null,"testPub","testIsbn","testLang","testDetails",null,1,2);
        int idExpected=runner.insert(connection,testQuery,new ScalarHandler<Integer>(),book.getCoverImage(),15,book.getPrice(),book.getBookTitle(),89,book.getPublisher(),book.getISBN(),book.getLanguage(),book.getDetails(),book.getInventoryStock());
        assertEquals(idExpected,41);
    }

    @Test
    public  void get_Book_By_Id_Must_Return_book_with_correct_id() throws Exception{

        QueryRunner runner=new QueryRunner();
        String query="select * from book where bookId=?";
        List<Book> book= (List<Book>)runner.query(connection,query,new BookRowHandler(connection),37);
        assertTrue(book.get(0) instanceof Book);
        assertNotNull(book.get(0));
        assertEquals((int)book.get(0).getBookId(),37);
    }

    @Test
    public  void test_If_Book_with_given_Title_exists() throws Exception{

        QueryRunner runner=new QueryRunner();
        String query="select * from book where bookTitle=?";
        List<Book> book= (List<Book>)runner.query(connection,query,new BookRowHandler(connection),new Object[]{"Java Lambdas and Parallel Streams"});
        assertNotNull(book.get(0));
        assertTrue(book.get(0) instanceof Book);
    }

    @Test
    public  void test_If_Book_with_given_Title_NOT_exists() throws Exception{

        QueryRunner runner=new QueryRunner();
        String query="select * from book where bookTitle=?";
        List<Book> book= (List<Book>)runner.query(connection,query,new BookRowHandler(connection),new Object[]{"Concurrency"});

        assertEquals(book.size(),0);
    }
                    @After
                    public void tearDown() throws Exception{
                        statement.execute("DROP TABLE book");
                        DbUtils.closeQuietly(statement);
                        DbUtils.closeQuietly(connection);

                    }




}
