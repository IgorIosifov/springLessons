package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class Cart {

    private List<Product> cartList;

    @PostConstruct
    public void init() {
        cartList = new ArrayList<>();
    }


    @Autowired
    private ProductService productService;

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }


    public List<Product> getCartList() {
        return Collections.unmodifiableList(cartList);
    }

    public void addToCart(Long id) {
        if (productService.getProductByID(id) != null) {
            cartList.add(productService.getProductByID(id));
        } else {
            System.out.println("WARNING: There is no product with this id!");
        }
    }


}
