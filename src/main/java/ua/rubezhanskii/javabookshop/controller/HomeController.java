package ua.rubezhanskii.javabookshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ua.rubezhanskii.javabookshop.datamanagement.repository.BookService;
import ua.rubezhanskii.javabookshop.datamanagement.repository.CartService;
import ua.rubezhanskii.javabookshop.model.BookSearchCriteria;

@Controller
@EnableWebMvc
public class HomeController {

    private BookService bookService;

    private CartService cartService;

    @Autowired
    public HomeController(BookService bookService, CartService cartService) {
        this.bookService = bookService;
        this.cartService = cartService;
    }

    // <==========================================Home==================================================================>
    @RequestMapping(value = {"/", "/welcome/home"}, method = RequestMethod.GET)
    public ModelAndView homePage() {
        ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("FrontPage");
            modelAndView.addObject("bookSearchCriteria", new BookSearchCriteria());
            modelAndView.addObject("SpringBooks", bookService.getBooksFromCategory(1));
            modelAndView.addObject("HibernateBooks", bookService.getBooksFromCategory(5));
            modelAndView.addObject("JavaEEbooks", bookService.getBooksFromCategory(6));
            modelAndView.addObject("JavaSEbooks", bookService.getBooksFromCategory(3));
            modelAndView.addObject("OOPbooks", bookService.getBooksFromCategory(2));
            modelAndView.addObject("MultiBooks", bookService.getBooksFromCategory(4));
            modelAndView.addObject("ScalaBooks", bookService.getBooksFromCategory(8));
            modelAndView.addObject("RESTbooks", bookService.getBooksFromCategory(9));
            modelAndView.addObject("AllBooks", bookService.getBooks());
            modelAndView.addObject("countCartItems", cartService.countItems(cartService.getLoggedUserName()));
        return modelAndView;
    }

    @RequestMapping(value = "/welcome/search/book", method = RequestMethod.GET)
    public ModelAndView getSearchResult(@ModelAttribute("bookSearchCriteria") BookSearchCriteria bookSearchCriteria) {
        ModelAndView modelAndView = new ModelAndView();
        bookSearchCriteria.setCriteria(bookSearchCriteria.getCriteria());
        modelAndView.addObject("searchResults", bookService.search(bookSearchCriteria.getCriteria()));
        modelAndView.setViewName("SearchPage");
        return modelAndView;
    }

    //<=================================================Book info======================================================>

    @RequestMapping(value = "/welcome/book")
    public String getProductById(@RequestParam("ISBN") String ISBN, Model model) {
          model.addAttribute("book", bookService.getBookByISBN(ISBN));
          model.addAttribute("countCartItems",cartService.countItems(cartService.getLoggedUserName()));
        boolean b=false;
        if (cartService.exists(bookService.getBookByISBN(ISBN).getBookId())) b=true;
            model.addAttribute("trigger",b);
            return "BookPage";
    }




  /*  @RequestMapping("/welcome/search/book")
    public String searchResult(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("searchResults",bookJdbcTemplate.search("Boot"));
       // modelAndView.addObject("criteria",criteria);


        return "SearchPage";

    }*/


    //<=================================================Browse by categories===========================================>
   /* @RequestMapping("/book/categories")
    public ModelAndView getBooksInCategory(@RequestParam("category") String category) {
        ModelAndView modelAndView=new ModelAndView("FrontPage");
        modelAndView.addObject("books",bookJdbcTemplate.getBooksFromCategory(category));
        modelAndView.addObject("category",category);
        return modelAndView;
    }
    @RequestMapping("/book/CategoriesAll")
    public ModelAndView getBooksInAllCategories() {
        ModelAndView modelAndView=new ModelAndView("FrontPage");
        modelAndView.addObject("books",bookJdbcTemplate.getBooks());
        return modelAndView;
    }*/

/*@ModelAttribute
    public BookSearchCriteria ctiteria(){
    return new BookSearchCriteria();
}

}*/

}