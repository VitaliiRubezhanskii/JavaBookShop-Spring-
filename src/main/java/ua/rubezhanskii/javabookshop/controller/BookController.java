package ua.rubezhanskii.javabookshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ua.rubezhanskii.javabookshop.datamanagement.repository.BookService;
import ua.rubezhanskii.javabookshop.datamanagement.repository.OrderService;
import ua.rubezhanskii.javabookshop.herokuspecific.HerokuHelper;
import ua.rubezhanskii.javabookshop.model.Book;
import ua.rubezhanskii.javabookshop.reports.ExcelFileBean;
import ua.rubezhanskii.javabookshop.reports.ExcelImportService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/welcome/admin")
public class BookController {

   private BookService bookService;

   private OrderService orderService;

   private HerokuHelper herokuHelper;

   private ExcelImportService excelImportService;

   @Autowired
   public BookController(BookService bookService,HerokuHelper herokuHelper, ExcelImportService excelImportService, OrderService orderService) {
        this.bookService = bookService;
        this.orderService = orderService;
        this.herokuHelper=herokuHelper;
        this.excelImportService = excelImportService;
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


    @RequestMapping(value = "/uploadExcelFile",method = RequestMethod.POST)
    public ModelAndView uploadFile(ModelAndView model,@ModelAttribute("inputFile") ExcelFileBean excelFileBean) throws IOException {
      MultipartFile file= excelFileBean.getMultipartFile();
        InputStream in = file.getInputStream();
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
       String fileLocation = path.substring(0, path.length() - 1) + file.getOriginalFilename();
        FileOutputStream f = new FileOutputStream(fileLocation);
        int ch = 0;
        while ((ch = in.read()) != -1) {
            f.write(ch);
        }
        f.flush();
        f.close();
        excelImportService.importFile(fileLocation);
        model.addObject("message", "File: " + file.getOriginalFilename()+ " has been uploaded successfully!"+fileLocation);
        model.addObject("newBook",new Book());

        return new ModelAndView("redirect:/welcome/admin/books");
    }



    //<==========================================Add Book==========================================================>
    @RequestMapping(value = "/books/addBook", method = RequestMethod.POST)
    public ModelAndView saveOrUpdate(@ModelAttribute("newBook")Book book) {
       // bookService.save(book);
        herokuHelper.save(book);
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
