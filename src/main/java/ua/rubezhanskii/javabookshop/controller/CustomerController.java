package ua.rubezhanskii.javabookshop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.rubezhanskii.javabookshop.datamanagement.repository.CartService;
import ua.rubezhanskii.javabookshop.model.Customer;

@Controller
@RequestMapping("/welcome/rest")
public class CustomerController {


    @Autowired
    private CartService cartService;

    //<======================================getCheckout page==========================================================>
    @RequestMapping(value = "/checkout", method = RequestMethod.GET)
    public ModelAndView getCheckoutPage(ModelAndView model) {
        model.addObject("customer",new Customer());
        model.addObject("countCartItems",cartService.countItems(cartService.getLoggedUserName()));
        model.setViewName("Checkout");
        return model;
    }

    @RequestMapping(value = "/saveCustomer",method = RequestMethod.POST)
    public ModelAndView saveCustomer(@ModelAttribute("customer") Customer customer, ModelAndView modelAndView){
        cartService.saveOrder(customer);
        modelAndView.setViewName("redirect:/welcome/rest/success");
        return modelAndView;
    }

    @RequestMapping(value = "/success",method = RequestMethod.GET)
    public String getSuccessPage(){
        return "SuccesOrdered";



}



}
