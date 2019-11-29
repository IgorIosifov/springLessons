package com.geekbrains.many_to_many;

import com.geekbrains.PrepareDataApp;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ManyToManyApp {
    public static void main(String[] args) {
        PrepareDataApp.forcePrepareData();

        SessionFactory factory = new Configuration()
                .configure("configs/many_to_many/hibernate.cfg.xml")
                .buildSessionFactory();

        Session session = null;
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            Customer customerFirst = session.get(Customer.class, 1L);
            System.out.println("First customer is: " + customerFirst.getName());
            System.out.println("Products: ");
            for (Product pr : customerFirst.getProducts()) {
                System.out.println(pr.getTitle());
            }

            Customer customerSecond = session.get(Customer.class, 2L);
            System.out.println("Second customer is: " + customerSecond.getName());
            System.out.println("Products: ");
            for (Product pr : customerSecond.getProducts()) {
                System.out.println(pr.getTitle());
            }

            Product productFirst = session.get(Product.class, 1L);
            System.out.println("First product is: " + productFirst.getTitle());
            System.out.println("Customers: ");
            for (Customer cst : productFirst.getCustomers()) {
                System.out.println(cst.getName());
            }

            Product productSecond = session.get(Product.class, 2L);
            System.out.println("Second product is: " + productSecond.getTitle());
            System.out.println("Customers: ");
            for (Customer cst : productSecond.getCustomers()) {
                System.out.println(cst.getName());
            }

            List <Customer> allCostumers = session.createQuery("SELECT customer FROM Customer customer").getResultList();
            System.out.println(allCostumers);
            session.createQuery(Customer.deleteCustomerFromDB(3)).executeUpdate();
            List <Customer> allCostumersAfterDelete = session.createQuery("SELECT customer FROM Customer customer").getResultList();
            System.out.println(allCostumersAfterDelete);
            List <Customer> allProducts= session.createQuery("SELECT product FROM Product product").getResultList();
            System.out.println(allProducts);
            session.createQuery(Product.deleteProductFromDB(5)).executeUpdate();
            List <Customer> allProductsAfterDelete = session.createQuery("SELECT product FROM Product product").getResultList();
            System.out.println(allProductsAfterDelete);
            session.getTransaction().commit();
        } finally {
            factory.close();
            if (session != null) {
                session.close();
            }
        }
    }

}
