package ua.rubezhanskii.javabookshop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.rubezhanskii.javabookshop.datamanagement.repository.CartService;
import ua.rubezhanskii.javabookshop.datamanagement.repository.CustomerService;
import ua.rubezhanskii.javabookshop.datamanagement.repository.OrderService;
import ua.rubezhanskii.javabookshop.model.Order;

@Controller
@RequestMapping(value = "/welcome/rest")
public class OrderController {

    private OrderService orderService;
    private CartService cartService;
    private CustomerService customerService;

    @Autowired
    public OrderController(OrderService orderService, CartService cartService, CustomerService customerService) {
        this.orderService = orderService;
        this.cartService = cartService;
        this.customerService = customerService;
    }

    @RequestMapping(value = "/order/orderManager", method = RequestMethod.GET)
    public String login() {
        return "OrderManager";
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public ModelAndView getFilterByCustomer(Order order, ModelAndView model) {
        model.addObject("order",order);
        model.setViewName("OrderManager");
        return model;
    }

    @RequestMapping(value = "/order/searchResult", method = RequestMethod.GET)
    public ModelAndView getFilterByDate(Order order, ModelAndView model) {
        model.addObject("listOrders", orderService.getOrdersByCustomer("Rubezhanskii"));
        model.setViewName("OrderManager");
        return model;
    }

    @RequestMapping(value = "/orders",method = RequestMethod.GET)
    public ModelAndView getOrdersPage(ModelAndView modelAndView){
        modelAndView.addObject("listItems",orderService.getOrdersByCustomer(customerService.getCustomerByLogin(cartService.getLoggedUserName())));
        modelAndView.setViewName("Orders");
        return modelAndView;

    }
}
