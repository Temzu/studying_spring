package com.temzu.hibernate.crud.dao;

import com.temzu.hibernate.crud.util.HibernateUtil;
import com.temzu.hibernate.crud.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ProductDaoImpl implements ProductDao {

    private static final SessionFactory factory = HibernateUtil.getSessionFactory();

    @Override
    public void saveOrUpdateProduct(Product product) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            if (product != null)
                session.saveOrUpdate(product);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<Product> listProducts() {
        List<Product> products;
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            products = session.createQuery("from Product").getResultList();
            session.getTransaction().commit();
        }
        return products;
    }

    @Override
    public Product getProductById(Long id) {
        Product product;
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            product = session.get(Product.class, id);
            session.getTransaction().commit();
        }
        return product;
    }

    @Override
    public void deleteProduct(Long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            if (product != null)
                session.delete(product);
            session.getTransaction().commit();
        }
    }
}
