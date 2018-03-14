package ua.rubezhanskii.javabookshop.RestClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ua.rubezhanskii.javabookshop.model.Cart;


import java.util.Arrays;
import java.util.List;

public class CartRestServiceImpl implements CartRestService {

    @Autowired
    private RestTemplate restTemplate;

    final String ROOT_URI="";

    @Override
    public List<Cart> getAllCart() {
    ResponseEntity<Cart[]> response= restTemplate.getForEntity(ROOT_URI,Cart[].class);
        return Arrays.asList(response.getBody());
    }

    @Override
    public Cart getById(Integer id) {

        ResponseEntity<Cart> response = restTemplate.getForEntity(ROOT_URI, Cart.class);
        return response.getBody();
    }

    @Override
    public HttpStatus addCartItem(Cart item) {
        ResponseEntity<HttpStatus> response = restTemplate.postForEntity(ROOT_URI+"/emp/create", item, HttpStatus.class);
        return response.getBody();

    }

    @Override
    public void updateCartItem(Cart item) {
        restTemplate.put(ROOT_URI, item);
    }

    @Override
    public void deleteCartItem(Integer id) {
        restTemplate.delete(ROOT_URI+"emp/delete/" + id);
    }




}
