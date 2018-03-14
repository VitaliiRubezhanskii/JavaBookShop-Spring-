/*package ua.rubezhanskii.javabookshop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ua.rubezhanskii.javabookshop.model.Cart;




@RestController
@RequestMapping(value = "/welcome/rest")
public class CartRestController {

    @Autowired
    private CartRepository cartRepository;


    @RequestMapping(value = "/cart",method = RequestMethod.POST)
    public ResponseEntity<Cart> addCartItem(@RequestBody Cart cart){

        cartRepository.save(new Cart());
        return new ResponseEntity<Cart>(cart, HttpStatus.OK);
    }

    @RequestMapping(value = "/cartItem/{isbn}")
    public void deleteCartItem(@PathVariable("isbn") String isbn){
            cartRepository.delete(isbn);

    }

    //Cart cart=new Cart("test","test","test",2.0,"test",null,33.0);
    @RequestMapping(value = "/cart/", method = RequestMethod.POST)
    public ResponseEntity<Void> createCart(@RequestBody Cart cart, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Book " + cart.getGlobalId());

        cartRepository.save(new Cart());

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/cartItem/{isbn}").buildAndExpand(cart.getGlobalId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    }*/




