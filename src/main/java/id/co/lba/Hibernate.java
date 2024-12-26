package id.co.lba;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Hibernate {

    private static Hibernate instance = new Hibernate();

    private SessionFactory sessionFactory;

    private Hibernate(){
        this.sessionFactory = buildSessionFactory();
    }

    private synchronized static SessionFactory buildSessionFactory() {
        return new Configuration().configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public static Hibernate getInstance() {
        if(instance == null){
            return new Hibernate();
        }
        return instance;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}