package ua.rubezhanskii.javabookshop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.rubezhanskii.javabookshop.datamanagement.repository.BookService;
import ua.rubezhanskii.javabookshop.datamanagement.repository.CartService;
import ua.rubezhanskii.javabookshop.exceptions.StockExhaustedException;
import ua.rubezhanskii.javabookshop.model.Book;

@Controller
@RequestMapping(value = "/welcome/rest/cart")
public class CartController {

    private CartService cartService;

    private BookService bookService;

    @Autowired
    public CartController(CartService cartService, BookService bookService) {
        this.cartService = cartService;
        this.bookService = bookService;
    }

    //<======================================get View with Cart Items==================================================>
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getCartPage(@ModelAttribute("cartItem") Book book, ModelAndView model) {
        model.addObject("listItems",cartService.getCartItemsByLogin(cartService.getLoggedUserName()));
        model.addObject("cartItem",new Book());//TODO
        model.setViewName("cart");
        return model;
    }

    //<==========================================Add Category==========================================================>
    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public ModelAndView saveOrUpdate(@RequestParam("ISBN")String ISBN, @ModelAttribute("book")Book book, ModelAndView modelAndView) {
        Book book1=bookService.getBookByISBN(ISBN);
        book1.setBookQuantity(book.getBookQuantity());
        if (book.getBookQuantity()>book1.getInventoryStock()){
            throw new StockExhaustedException("You choose quantity that exceeds books available in stock");
        }else {
            cartService.save(book1);
        }
            return new ModelAndView("redirect:/welcome/rest/cart/");
    }

    @ExceptionHandler(StockExhaustedException.class)
    public ModelAndView handleStockExhaustedException(StockExhaustedException exception){
        ModelAndView model=new ModelAndView();
        model.addObject("errorMessage",exception.getMessage());
        model.setViewName("StockExhaustedException");
        return model;
    }

    //<==========================================Remove Category=======================================================>
    @RequestMapping(value = "/remove/{bookId}")
    public ModelAndView removeCartItem(@PathVariable("bookId") Integer bookId){
        cartService.delete(bookId);
        return new ModelAndView("redirect:/welcome/rest/cart/");
    }

    @RequestMapping(value = "/remove")
    public ModelAndView clearCart(){
        cartService.deleteAll();
        return new ModelAndView("redirect:/welcome/rest/cart/");
    }

    //<==========================================Edit Category=========================================================>

    @RequestMapping("/edit/{bookId}")
    public ModelAndView editBook(@PathVariable("bookId") Integer bookId){
        Book book=bookService.getBookById(bookId);
        return new ModelAndView("cart","cartItem",book);
    }

    @RequestMapping(value = "/edit/save",method = RequestMethod.POST)
    public ModelAndView editSave(@ModelAttribute("category") Book book){
        cartService.update(book);
        return new ModelAndView("redirect:/welcome/rest/cart/");
    }



    @RequestMapping(value = "/saveOrder", method = RequestMethod.POST)
    public ModelAndView saveOrder(ModelAndView modelAndView) {

        //TODO
        return new ModelAndView("redirect:/welcome/rest/cart/");
    }






}
