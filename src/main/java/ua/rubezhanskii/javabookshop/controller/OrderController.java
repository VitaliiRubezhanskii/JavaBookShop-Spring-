package ua.rubezhanskii.javabookshop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.rubezhanskii.javabookshop.datamanagement.jdbc.OrderJdbcTemplate;
import ua.rubezhanskii.javabookshop.model.Order;

@Controller
@RequestMapping(value = "/welcome/rest/order")
public class OrderController {

    @Autowired
    private OrderJdbcTemplate orderJdbcTemplate;

    @RequestMapping(value = "/orderManager", method = RequestMethod.GET)
    public String login() {
        return "OrderManager";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getFilterByCustomer(Order order, ModelAndView model) {
        //Order order=new Order();
        // model.addObject("customerAttribute", );
        model.addObject("order",order);
        model.setViewName("OrderManager");
        return model;
    }

    @RequestMapping(value = "/searchResult", method = RequestMethod.GET)
    public ModelAndView getFilterByDate(Order order, ModelAndView model) {
        // Order order=new Order();
        model.addObject("listOrders", orderJdbcTemplate.getOrdersByCustomer("Rubezhanskii"));
       //  model.addObject("customerAttribute",customerAttribute);
        model.setViewName("OrderManager");
        return model;
    }




}
