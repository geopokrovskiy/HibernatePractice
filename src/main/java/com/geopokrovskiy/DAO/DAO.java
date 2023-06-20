package com.geopokrovskiy.DAO;

import com.geopokrovskiy.model.Developer;
import com.geopokrovskiy.model.Skill;
import com.geopokrovskiy.model.Speciality;
import com.geopokrovskiy.model.Status;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import java.util.List;

public class DAO {
    private static Session openedSession = null;

    public static void addObject(Object o) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            session.save(o);
            session.getTransaction().commit();
        } catch (ConstraintViolationException e) {
            session.getTransaction().rollback();
            throw new IllegalArgumentException("Entity already Exists");
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    public static Object getObjectById(Long id, Class className) {
        Session session = HibernateUtil.getSession();
        Criteria criteria = session.createCriteria(className).
                add(Restrictions.eq("status", Status.ACTIVE)).
                add(Restrictions.eq("id", id));
        Object obj = criteria.uniqueResult();
        openedSession = session;
        return obj;
    }

    public static List getAllObjects(Class className) {
        Session session = HibernateUtil.getSession();
        List list = session.createCriteria(className).
                add(Restrictions.eq("status", Status.ACTIVE)).list();
        openedSession = session;
        return list;
    }

    public static void updateObject(Object obj) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            session.update(obj);
            session.getTransaction().commit();
        } catch (ConstraintViolationException e) {
            session.getTransaction().rollback();
            throw new IllegalArgumentException("Entity already exists!");
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    public static void deleteObjectById(Long id, Class className) {
        Session session = HibernateUtil.getSession();
        SQLQuery sqlQuery = null;
        try {
            session.beginTransaction();
            if (className.equals(Developer.class)) {
                sqlQuery = session.createSQLQuery("UPDATE developers SET status = 1 WHERE developers.id = " + id);
            } else if (className.equals(Skill.class)) {
                sqlQuery = session.createSQLQuery("UPDATE skills SET status = 1 WHERE skills.id = " + id);
            } else if (className.equals(Speciality.class)) {
                sqlQuery = session.createSQLQuery("UPDATE specs SET status = 1 WHERE specs.id = " + id);
            }
            if (sqlQuery != null) {
                sqlQuery.executeUpdate();
                session.getTransaction().commit();
            }
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new IllegalArgumentException(e.getMessage());
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    public static void closeOpenedSession() {
        if (openedSession != null && openedSession.isOpen()) {
            openedSession.close();
        }
    }
}
