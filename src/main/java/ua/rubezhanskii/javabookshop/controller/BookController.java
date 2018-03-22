package ua.rubezhanskii.javabookshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.rubezhanskii.javabookshop.datamanagement.repository.BookService;
import ua.rubezhanskii.javabookshop.datamanagement.repository.OrderService;
import ua.rubezhanskii.javabookshop.herokuspecific.HerokuHelper;
import ua.rubezhanskii.javabookshop.model.Book;
import ua.rubezhanskii.javabookshop.reports.ExcelFileBean;

import java.io.File;

@Controller
@RequestMapping("/welcome/admin")
public class BookController {

   private BookService bookService;

   private OrderService orderService;

   //private ExcelImportService excelImportService;

   @Autowired
   public BookController(BookService bookService, OrderService orderService) {
        this.bookService = bookService;
        this.orderService = orderService;
        //this.excelImportService = excelImportService;
    }

    //<=================================================get View with Books================================================>
    @RequestMapping(value = "/books",method = RequestMethod.GET)
    public String getBookPage(Model model) {
        Book book=new Book();
        ExcelFileBean excelFileBean=new ExcelFileBean();
        File inputFile=new File("C:\\Users\\Vitalii\\Desktop\\Books.xls");

        model.addAttribute("newBook", book);
        model.addAttribute("listBooks",bookService.getBooks());
        model.addAttribute("orders",orderService.getOrders());
        model.addAttribute("inputFile",excelFileBean);
       // model.setViewName("AdminPage");
        return "AdminPage";
}

  /*  @RequestMapping(value = "/books", method = RequestMethod.POST)
    public String upload(@ModelAttribute("inputFile") ExcelFileBean file, Model model) {

        excelImportService.importFile(file);
       // model.addAttribute("excelFileBean",excelFileBean);
       // model.addAttribute("filename",excelFileBean.getFile().getOriginalFilename());
        return "redirect:/welcome/admin/books";
    }

*/


    //<==========================================Add Book==========================================================>
    @RequestMapping(value = "/books/addBook", method = RequestMethod.POST)
    public ModelAndView saveOrUpdate(@ModelAttribute("newBook")Book book) {
       // bookService.save(book);
        HerokuHelper.save(book);
        return new ModelAndView("redirect:/welcome/admin/books");
    }
    //<==========================================Remove Category==========================================================>
    @RequestMapping(value = "/books/remove/{bookId}")
    public ModelAndView removeBook(@PathVariable("bookId") Integer bookId){
        bookService.delete(bookId);
        return new ModelAndView("redirect:/welcome/admin/books/");
    }
    //<==========================================Edit Category==========================================================>

    @RequestMapping("/books/edit/{bookId}")
    public ModelAndView editBook(@PathVariable("bookId") Integer bookId){
        ExcelFileBean excelFileBean=new ExcelFileBean();
        Book book=bookService.getBookById(bookId);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("newBook",book);
        modelAndView.addObject("inputFile",excelFileBean);
        modelAndView.setViewName("AdminPage");
        return modelAndView;
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    public ModelAndView editSave(@ModelAttribute("book") Book book){
        bookService.update(book);
        return new ModelAndView("redirect:/welcome/admin/books/");
    }

}
