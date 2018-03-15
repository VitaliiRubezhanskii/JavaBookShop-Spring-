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
import ua.rubezhanskii.javabookshop.datamanagement.jdbc.BookJdbcTemplate;
import ua.rubezhanskii.javabookshop.datamanagement.jdbc.CartJdbcTemplate;
import ua.rubezhanskii.javabookshop.datamanagement.jdbc.CategoryJdbcTemplate;
import ua.rubezhanskii.javabookshop.model.BookSearchCriteria;

@Controller
@EnableWebMvc

public class HomeController {


    @Autowired
    private BookJdbcTemplate bookJdbcTemplate;
    @Autowired
    private CategoryJdbcTemplate categoryJdbcTemplate;
    @Autowired
    private CartJdbcTemplate cartJdbcTemplate;

    // <==========================================Home==================================================================>
    @RequestMapping(value = {"/", "/welcome/home"}, method = RequestMethod.GET)
    public ModelAndView homePage() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("FrontPage");
        modelAndView.addObject("bookSearchCriteria", new BookSearchCriteria());
        modelAndView.addObject("SpringBooks", bookJdbcTemplate.getBooksFromCategory(1));
        modelAndView.addObject("HibernateBooks", bookJdbcTemplate.getBooksFromCategory(5));
        modelAndView.addObject("JavaEEbooks", bookJdbcTemplate.getBooksFromCategory(6));
        modelAndView.addObject("JavaSEbooks", bookJdbcTemplate.getBooksFromCategory(3));
        modelAndView.addObject("OOPbooks", bookJdbcTemplate.getBooksFromCategory(2));
        modelAndView.addObject("MultiBooks", bookJdbcTemplate.getBooksFromCategory(4));
        modelAndView.addObject("ScalaBooks", bookJdbcTemplate.getBooksFromCategory(8));
        modelAndView.addObject("RESTbooks", bookJdbcTemplate.getBooksFromCategory(9));
        modelAndView.addObject("AllBooks", bookJdbcTemplate.getBooks());
        modelAndView.addObject("countCartItems", cartJdbcTemplate.countItems(System.getProperty("user.name")));
        //   modelAndView.addObject("user");
        modelAndView.addObject("countCartItems", cartJdbcTemplate.countItems(System.getProperty("user.name")));
        return modelAndView;
    }

    @RequestMapping(value = "/welcome/search/book", method = RequestMethod.GET)
    public ModelAndView getSearchResult(@ModelAttribute("bookSearchCriteria") BookSearchCriteria bookSearchCriteria) {
        ModelAndView modelAndView = new ModelAndView();
        //  BookSearchCriteria bookSearchCriteria=new BookSearchCriteria();
         bookSearchCriteria.setCriteria(bookSearchCriteria.getCriteria());
        modelAndView.addObject("searchResults", bookJdbcTemplate.search(bookSearchCriteria.getCriteria()));



        modelAndView.setViewName("SearchPage");
        return modelAndView;

    }
    //<=================================================Book info======================================================>

    @RequestMapping(value = "/welcome/book")
    public String getProductById(@RequestParam("ISBN") String ISBN, Model model) {
        model.addAttribute("book", bookJdbcTemplate.getBookByISBN(ISBN));
        //    model.addAttribute("countCartItems",cartJdbcTemplate.countItems(1));
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