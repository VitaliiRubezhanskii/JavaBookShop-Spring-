package ua.rubezhanskii.javabookshop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.rubezhanskii.javabookshop.datamanagement.jdbc.CartJdbcTemplate;
import ua.rubezhanskii.javabookshop.datamanagement.jdbc.CustomerJdbcTemplate;
import ua.rubezhanskii.javabookshop.datamanagement.jdbc.OrderJdbcTemplate;
import ua.rubezhanskii.javabookshop.model.Customer;

@Controller
@RequestMapping("/welcome/rest")
public class CustomerController {

    @Autowired
    private CustomerJdbcTemplate customerJdbcTemplate;
    @Autowired
    private CartJdbcTemplate cartJdbcTemplate;
    @Autowired
    private OrderJdbcTemplate orderJdbcTemplate;

    //<======================================getCheckout page==========================================================>
    @RequestMapping(value = "/checkout", method = RequestMethod.GET)
    public ModelAndView getCheckoutPage(ModelAndView model) {
        model.addObject("customer",new Customer());
        model.addObject("countCartItems",cartJdbcTemplate.countItems(System.getProperty("user.name")));
        model.setViewName("Checkout");
        return model;
    }
@RequestMapping(value = "/saveCustomer",method = RequestMethod.POST)
    public ModelAndView saveCustomer(@ModelAttribute("customer") Customer customer){
    //    customerJdbcTemplate.save(customer);
    cartJdbcTemplate.saveOrder(customer);
        return new ModelAndView("redirect:/welcome/rest/checkout");
}

}
