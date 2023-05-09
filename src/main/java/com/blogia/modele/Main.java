package com.blogia.modele;

import com.blogia.hibernate.HibernateDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Collections;
import java.util.List;


public class Main {
    public static void main(String[] args) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("Conf.xml");
        HibernateDAO db = context.getBean(HibernateDAO.class);
    }
}
