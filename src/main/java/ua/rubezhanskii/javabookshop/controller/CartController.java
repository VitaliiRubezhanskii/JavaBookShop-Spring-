package ua.rubezhanskii.javabookshop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.rubezhanskii.javabookshop.datamanagement.jdbc.BookJdbcTemplate;
import ua.rubezhanskii.javabookshop.datamanagement.jdbc.CartJdbcTemplate;
import ua.rubezhanskii.javabookshop.model.Book;

@Controller
@RequestMapping(value = "/welcome/rest/cart")
public class CartController {

    @Autowired
    private CartJdbcTemplate cartJdbcTemplate;
    @Autowired
    private BookJdbcTemplate bookJdbcTemplate;


    //<======================================get View with Cart Items==================================================>
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getCartPage(@ModelAttribute("cartItem") Book book, ModelAndView model) {
       // Book book=new Book();
        model.addObject("listItems",cartJdbcTemplate.getCartItemsByMachineName(System.getProperty("user.name")));
       // model.addObject("trigger",false);
       model.addObject("cartItem",new Book());//TODO
        model.setViewName("cart");
        return model;
    }


    //<==========================================Add Category==========================================================>
    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public ModelAndView saveOrUpdate(@RequestParam("ISBN")String ISBN, ModelAndView modelAndView) {


            cartJdbcTemplate.save(bookJdbcTemplate.getBookByISBN(ISBN));

        return new ModelAndView("redirect:/welcome/rest/cart/");
    }

    //<==========================================Remove Category=======================================================>
    @RequestMapping(value = "/remove/{bookId}")
    private ModelAndView removeCartItem(@PathVariable("bookId") Integer bookId){
        cartJdbcTemplate.delete(bookId);
        return new ModelAndView("redirect:/welcome/rest/cart/");
    }

    @RequestMapping(value = "/remove")
    private ModelAndView clearCart(){
        cartJdbcTemplate.deleteAll();
        return new ModelAndView("redirect:/welcome/rest/cart/");
    }
    //<==========================================Edit Category=========================================================>

    @RequestMapping("/edit/{bookId}")
    public ModelAndView editBook(@PathVariable("bookId") Integer bookId){
        Book book=bookJdbcTemplate.getBookById(bookId);
        return new ModelAndView("cart","cartItem",book);

    }

    @RequestMapping(value = "/edit/save",method = RequestMethod.POST)
    public ModelAndView editSave(@ModelAttribute("category") Book book,Integer bookQuantity){
        cartJdbcTemplate.update(book);
        return new ModelAndView("redirect:/welcome/rest/cart/");
    }



    @RequestMapping(value = "/saveOrder", method = RequestMethod.POST)
    public ModelAndView saveOrder(ModelAndView modelAndView) {

       // cartJdbcTemplate.saveOrder();
        return new ModelAndView("redirect:/welcome/rest/cart/");
    }

    @ExceptionHandler(RuntimeException.class)
    public  ModelAndView handleRuntimeException(RuntimeException ex){
    ModelAndView modelAndView=new ModelAndView("redirect:/welcome/rest/cart/");
    //modelAndView.setViewName("cart");
    modelAndView.addObject("trigger",new Book());
    return  modelAndView;

    }




}
