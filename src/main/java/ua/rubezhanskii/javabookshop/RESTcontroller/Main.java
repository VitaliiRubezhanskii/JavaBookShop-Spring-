/*package ua.rubezhanskii.javabookshop.RESTcontroller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.rubezhanskii.javabookshop.datamanagement.config.PersistenceContext;
import ua.rubezhanskii.javabookshop.datamanagement.service.BookService;
import ua.rubezhanskii.javabookshop.entity.product.Book;
public class Main{

    public static void main(String[] args) {
        ApplicationContext context=new AnnotationConfigApplicationContext(PersistenceContext.class);
        BookService bookService=context.getBean(BookService.class);
        Book book=new Book("ISBN-666", "TeastBook", "TeastBook", 666.66, "ISBN-666",
                "Devil", "Devil", "Devil", "Devil", 2012,  3, 6.66, 666.66, "Hell");

        System.out.println(bookService.getFirstSixBooks());

     //  System.out.println(bookService.getAllBooksWithinCategory("Spring"));
        //System.out.println(bookService.getCategoryOfBook("ISBN 978-1-4842-1224-0"));
       // System.out.println( bookService.getBookByIsbn("ISBN 978-1-4842-0823-6"));
       // System.out.println(bookService. getFirstSixBooks());
    }
    /*    Book book=new Book("ISBN-666", "TeastBook", "TeastBook", 666.66, "ISBN-666",
                "Devil", "Devil", "Devil", "Devil", 2012,  3, 6.66, 666.66, "Hell");
      new  BookJdbcUtil().addNewBook(book);
*/



