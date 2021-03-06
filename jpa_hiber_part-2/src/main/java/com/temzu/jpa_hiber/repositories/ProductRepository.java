package com.temzu.jpa_hiber.repositories;

import com.temzu.jpa_hiber.entities.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
public class ProductRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public ProductRepository(EntityManagerFactory factory) {
        if(factory.unwrap(SessionFactory.class) == null){
            throw new NullPointerException("factory is not a hibernate factory");
        }
        this.sessionFactory = factory.unwrap(SessionFactory.class);
    }

    public void saveOrUpdateProduct(Product product) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            if (product != null)
                session.saveOrUpdate(product);
            session.getTransaction().commit();
        }
    }

    public List<Product> listProducts() {
        List<Product> products;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            products = session.createQuery("from Product").getResultList();
            session.getTransaction().commit();
        }
        return products;
    }

    public Product getProductById(Long id) {
        Product product;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            product = session.get(Product.class, id);
            session.getTransaction().commit();
        }
        return product;
    }

    public void deleteProduct(Long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            if (product != null)
                session.delete(product);
            session.getTransaction().commit();
        }
    }
}
