package com.geekbrains.many_to_many;


import javax.persistence.*;
import java.util.List;

@Entity
@Table (name="products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column (name = "title")
    private String title;

    @Column (name = "cost")
    private int cost;

    @ManyToMany
    @JoinTable(
            name = "products_customers",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn (name = "customer_id")

    )

    private List <Customer> customers;

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
    public List<Customer> getCustomers() {
        return customers;
    }



    public Product() {
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
    @Override
    public String toString() {
        return String.format("Product [id = %d, title = %s, cost = %d]", id, title, cost);
    }

    public static String  deleteProductFromDB (int id) {
        return "DELETE FROM Product  WHERE id ="+ id;
    }
}
