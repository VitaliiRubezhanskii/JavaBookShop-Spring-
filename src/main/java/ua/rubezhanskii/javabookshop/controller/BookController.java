package ua.rubezhanskii.javabookshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.rubezhanskii.javabookshop.datamanagement.jdbc.BookJdbcTemplate;
import ua.rubezhanskii.javabookshop.model.Book;

@Controller
@RequestMapping("/welcome/admin/books")
public class BookController {


   @Autowired
   private BookJdbcTemplate bookJdbcTemplate;

//<=================================================get View with Books================================================>
@RequestMapping(value = "/", method = RequestMethod.GET)
public ModelAndView getBookPage(ModelAndView model) {
    Book book=new Book();
    model.addObject("newBook", book);
    model.addObject("listBooks",bookJdbcTemplate.getBooks());
    model.setViewName("AdminPage");
    return model;
}

    //<==========================================Add Book==========================================================>
    @RequestMapping(value = "/addBook", method = RequestMethod.POST)
    public ModelAndView saveOrUpdate(@ModelAttribute("book")Book book) {
        if(bookJdbcTemplate.exists(book.getBookId())){
            bookJdbcTemplate.update(book);
        }else {
            bookJdbcTemplate.save(book);
        }
        return new ModelAndView("redirect:/welcome/admin/books/");
    }
    //<==========================================Remove Category==========================================================>
    @RequestMapping(value = "/remove/{bookId}")
    private ModelAndView removeBook(@PathVariable("bookId") Integer bookId){
        bookJdbcTemplate.delete(bookId);
        return new ModelAndView("redirect:/welcome/admin/books/");
    }
    //<==========================================Edit Category==========================================================>

    @RequestMapping("/edit/{bookId}")
    public ModelAndView editBook(@PathVariable("bookId") Integer bookId){
        Book book=bookJdbcTemplate.getBookById(bookId);
        return new ModelAndView("AdminPage","newBook",book);

    }

    @RequestMapping(value = "/edit/save",method = RequestMethod.POST)
    public ModelAndView editSave(@ModelAttribute("book") Book book){
        bookJdbcTemplate.update(book);
        return new ModelAndView("redirect:/welcome/admin/books/");
    }

}
