package com.temzu.hibernate.crud.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory factory;

    static {
        PrepareDataApp.forcePrepareData();
        factory = new Configuration()
                .configure("config/crud/hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public static SessionFactory getSessionFactory(){
        return factory;
    }

    public static void shutdown() {
        factory.close();
    }
}
