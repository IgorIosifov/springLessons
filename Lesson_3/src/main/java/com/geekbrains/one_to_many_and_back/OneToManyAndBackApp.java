package com.geekbrains.one_to_many_and_back;

import com.geekbrains.PrepareDataApp;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class OneToManyAndBackApp {
    public static void main(String[] args) {
        PrepareDataApp.forcePrepareData();

        SessionFactory factory = new Configuration()
                .configure("configs/one_to_many_and_back/hibernate.cfg.xml")
                .buildSessionFactory();

        Session session = null;
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            University university = session.get(University.class, 1L);
            System.out.println(university);
            System.out.println("Students: ");
            for (Student s : university.getStudents()) {
                System.out.println(s.getName());
            }

//            Student student = new Student("Zahar", university);
//            session.save(student);
//            university.getStudents().add(student);
            session.getTransaction().commit();
        } finally {
            factory.close();
            if (session != null) {
                session.close();
            }
        }
    }
}
