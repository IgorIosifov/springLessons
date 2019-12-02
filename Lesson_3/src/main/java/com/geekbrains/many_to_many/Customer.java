package com.geekbrains.many_to_many;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;


    @Column (name = "name")
    private String name;

    public Customer() {
    }

    @ManyToMany
    @JoinTable(
            name = "customers_products",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn (name = "product_id")

    )

    private List<Product> products;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("Customer [id = %d, name = %s]", id, name);
    }

    public static String  deleteCustomerFromDB (Long id) {
        return "DELETE FROM Customer  WHERE id ="+ id;
    }
}
