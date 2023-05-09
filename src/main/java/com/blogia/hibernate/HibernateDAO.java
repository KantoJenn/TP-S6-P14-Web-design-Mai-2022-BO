package com.blogia.hibernate;
import com.blogia.modele.Article;
import org.hibernate.*;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class HibernateDAO {

    private SessionFactory sessionFactory;

    public int Connecter(String nom, String mdp){
        Session session = sessionFactory.openSession();
        String sql = "SELECT id FROM Administrateur where nom ='"+nom+"' and mdp = '"+mdp+"'";
        Query query = session.createSQLQuery(sql);
        int ans = (int) query.uniqueResult();
        return ans;
    }

    public List<Article> ArticleById(int id){
        Session session = sessionFactory.openSession();
        String sql = "SELECT * FROM Article where idarticle = "+id;
        Query query = session.createSQLQuery(sql);
        List<Article> ans = (List<Article>) query.list();
        return ans;
    }
    public void Modifier(String titre, String description, int id){
        try {
            Session session = sessionFactory.openSession();
            String sql = "update article SET titre = :titre, description = :description where idarticle = "+id;
            Query query = session.createNativeQuery(sql);
            query.setParameter("titre", titre);
            query.setParameter("description", description);
            query.executeUpdate();
        }catch (HibernateException e){
            e.printStackTrace();
        }
    }

    public void Ajouter(String titre, String description){
        try {
            Session session = sessionFactory.openSession();
            String sql = "insert into Article (titre, description) values (:titre, :description)";
            Query query = session.createNativeQuery(sql);
            query.setParameter("titre", titre);
            query.setParameter("description", description);
            query.executeUpdate();
        }catch (HibernateException e){
            e.printStackTrace();
        }
    }

    public <T> T create(T entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(entity);
        transaction.commit();
        session.close();
        return entity;
    }
    public<T> List<T> findQuery(Class<T> tClass,String query){
        Session session = sessionFactory.openSession();
        List<T> results = session.createNativeQuery(query,tClass).list();
        session.close();
        return results;
    }

    public <T> T findById(Class<T> clazz, Serializable id) {
        Session session = sessionFactory.openSession();
        T entity = (T) session.get(clazz, id);
        session.close();
        return entity;
    }

    public <T> List<T> findAll(Class<T> tClass) {
        Session session = sessionFactory.openSession();
        List<T> results = session.createCriteria(tClass).list();
        session.close();
        return results;
    }

    public <T> List<T> findWhere(T entity) {
        Session session = sessionFactory.openSession();
        Example example = Example.create(entity).ignoreCase();
        List<T> results = session.createCriteria(entity.getClass()).add(example).list();
        session.close();
        return results;
    }

    public <T> List<T> findField(Class<T> tClass, String field, String value) {
        Session session = sessionFactory.openSession();
        Criteria crit = session.createCriteria(tClass);
        crit.add(Restrictions.eq(field, value));
        List<T> results = crit.list();
        return results;
    }

    public <T> List<T> findField(Class<T> tClass, String field, Integer value) {
        Session session = sessionFactory.openSession();
        Criteria crit = session.createCriteria(tClass);
        crit.add(Restrictions.eq(field, value));
        List<T> results = crit.list();
        return results;
    }

    public <T> List<T> paginateWhere(T entity, int offset, int size) {
        Session session = sessionFactory.openSession();
        Example example = Example.create(entity).ignoreCase();
        List<T> results = session.createCriteria(entity.getClass())
            .add(example)
            .setFirstResult(offset)
            .setMaxResults(offset + size).list();
        session.close();
        return results;
    }

    public <T> List<T> paginate(Class<T> clazz, int offset, int size) {
        Session session = sessionFactory.openSession();
        List<T> results = session.createCriteria(clazz)
            .setFirstResult(offset)
            .setMaxResults(offset + size).list();
        session.close();
        return results;
    }

    public <T> List<T> paginate(Class<T> clazz, int offset, int size, String orderBy, boolean orderAsc) {
        Session session = sessionFactory.openSession();
        Order order = (orderAsc) ? Order.asc(orderBy) : Order.desc(orderBy);
        List<T> results = session.createCriteria(clazz)
            .addOrder(order)
            .setFirstResult(offset)
            .setMaxResults(offset + size).list();
        session.close();
        return results;
    }

    public void deleteById(Class tClass, Serializable id) {
        delete(findById(tClass, id));
    }

    public void delete(Object entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
        session.close();
    }

    public <T> T update(T entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(entity);
        transaction.commit();
        session.close();
        return entity;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
