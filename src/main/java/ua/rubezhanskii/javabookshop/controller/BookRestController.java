/*package ua.rubezhanskii.javabookshop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ua.rubezhanskii.javabookshop.datamanagement.service2.serviceImpl.BookServiceImpl;
import ua.rubezhanskii.javabookshop.model.Book;
import java.util.List;


@RestController
@RequestMapping(value = "/welcome")
public class BookRestController {

    @Autowired
    BookServiceImpl bookService;  //Service which will do all data retrieval/manipulation work


    //-------------------Retrieve All Books--------------------------------------------------------

    @RequestMapping(value = "/book/", method = RequestMethod.GET)
    public ResponseEntity<List<Book>> listAllBooks() {
        List<Book> books = bookService.getAllBooks();
        if(books.isEmpty()){
            return new ResponseEntity<List<Book>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
    }



    //-------------------Retrieve Single Book--------------------------------------------------------

    @RequestMapping(value = "/book/{isbn}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> getBook(@PathVariable("isbn") String isbn) {
        System.out.println("Fetching book with isbn " + isbn);
        Book book = bookService.getBookByIsbn(isbn);
        if (book == null) {
            System.out.println("book with isbn " + isbn + " not found");
            return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }



    //-------------------Create a Book--------------------------------------------------------

    @RequestMapping(value = "/book/", method = RequestMethod.POST)
    public ResponseEntity<Void> createBook(@RequestBody Book book, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Book " + book.getISBN());

        bookService.saveOrUpdate(new Book());

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{isbn}").buildAndExpand(book.getISBN()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }


}*/
