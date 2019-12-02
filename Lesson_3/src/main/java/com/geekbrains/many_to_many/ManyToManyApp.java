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
            session = factory.openSession();
            getInfoAboutCustomer(session, 1L);
            getInfoAboutCustomer(session, 2L);
            getInfoAboutProduct(session, 1L);
            getInfoAboutProduct(session, 2L);
            
            System.out.println(getAllCustomers(session)); // before delete customer from DB
            deleteCustomerFromDB(session, 3L);
            System.out.println(getAllCustomers(session)); // after delete customer from DB

            System.out.println(getAllProducts(session)); // before delete product from DB
            deleteProductFromDB(session, 5L);
            System.out.println(getAllProducts(session)); // after delete product from DB


        } finally {
            factory.close();
            if (session != null) {
                session.close();
            }
        }
    }

    private static void deleteProductFromDB(Session session, Long id) {
        session.beginTransaction();
        session.createQuery(Product.deleteProductFromDB(id)).executeUpdate();
        session.getTransaction().commit();
    }

    private static void deleteCustomerFromDB(Session session, Long id) {
        session.beginTransaction();
        session.createQuery(Customer.deleteCustomerFromDB(id)).executeUpdate();
        session.getTransaction().commit();
    }

    private static List<Customer> getAllCustomers(Session session) {
        session.beginTransaction();
        try {
            return (List<Customer>) session.createQuery("SELECT customer FROM Customer customer").getResultList();
        } finally {
            session.getTransaction().commit();
        }
    }

    private static List<Product> getAllProducts(Session session) {
        session.beginTransaction();
        try {
            return (List<Product>) session.createQuery("SELECT product FROM Product product").getResultList();
        } finally {
            session.getTransaction().commit();
        }
    }


    private static void getInfoAboutProduct(Session session, Long id) {
        session.beginTransaction();
        Product productFirst = session.get(Product.class, id);
        System.out.println("Title of " + id + " product is: " + productFirst.getTitle());
        System.out.println("Customers: ");
        for (Customer cst : productFirst.getCustomers()) {
            System.out.println(cst.getName());
        }
        session.getTransaction().commit();
    }

    private static void getInfoAboutCustomer(Session session, Long id) {

        session.beginTransaction();
        Customer customerFirst = session.get(Customer.class, id);
        System.out.println("Name of " + id + " customer is: " + customerFirst.getName());
        System.out.println("Products: ");
        for (Product pr : customerFirst.getProducts()) {
            System.out.println(pr.getTitle());
        }
        session.getTransaction().commit();
    }

}
