package spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MarketApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MarketConfig.class);

        ProductService productService = context.getBean("productService", ProductService.class);
        List<Product> products = productService.getAllProducts();
        System.out.println(products);

        Cart cart = context.getBean("cart", Cart.class);
        List<Product> firstCart = cart.getCartList();
        System.out.println("initial cart condition:");
        System.out.println(firstCart);
        cart.addToCart(1L);
        System.out.println("secondary cart condition:");
        System.out.println(firstCart);
        System.out.println("tertiary cart condition:");
        cart.addToCart(5L);
        System.out.println(firstCart);

    }
}
