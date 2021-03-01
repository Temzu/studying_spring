package com.temzu.jpa_hiber.repositories;

import com.temzu.jpa_hiber.entities.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
public class CustomerRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public CustomerRepository(EntityManagerFactory factory) {
        if(factory.unwrap(SessionFactory.class) == null){
            throw new NullPointerException("factory is not a hibernate factory");
        }
        this.sessionFactory = factory.unwrap(SessionFactory.class);
    }

    public void saveOrUpdateCustomer(Customer customer) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            if (customer != null)
                session.saveOrUpdate(customer);
            session.getTransaction().commit();
        }
    }

    public List<Customer> listCustomers() {
        List<Customer> customers;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            customers = session.createQuery("from Customer").getResultList();
            session.getTransaction().commit();
        }
        return customers;
    }

    public Customer getCustomerById(Long id) {
        Customer customer;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            customer = session.get(Customer.class, id);
            session.getTransaction().commit();
        }
        return customer;
    }

    public void deleteCustomer(Long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Customer customer = session.get(Customer.class, id);
            if (customer != null)
                session.delete(customer);
            session.getTransaction().commit();
        }
    }

}
