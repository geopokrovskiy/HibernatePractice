package com.geopokrovskiy.repository.hibernate;

import com.geopokrovskiy.model.Developer;
import com.geopokrovskiy.repository.DeveloperRepository;
import com.geopokrovskiy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class DeveloperRepositoryImpl implements DeveloperRepository {
    @Override
    public Developer addNew(Developer value) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            session.save(value);
            session.getTransaction().commit();
            return value;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new IllegalArgumentException("Illegal argument!");
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    @Override
    public Developer getById(Long aLong) {
        try (Session session = HibernateUtil.getSession()) {
            Query query = session.createQuery("FROM Developer D LEFT JOIN FETCH D.skills " +
                            "WHERE D.id =: id AND D.status = 0" +
                            "AND D.speciality.status = 0")
                    .setParameter("id", aLong);
            return (Developer) query.getSingleResult();
        } catch (Exception e) {
            throw new IllegalArgumentException("Incorrect id!");
        }
    }

    @Override
    public List<Developer> getAll() {
        try (Session session = HibernateUtil.getSession()) {
            Query query = session.createQuery("SELECT DISTINCT D FROM Developer D " +
                    "LEFT JOIN FETCH D.skills ");
            return (List<Developer>) query.list();
        }
    }

    @Override
    public Developer update(Developer value) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            session.update(value);
            session.getTransaction().commit();
            return value;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new IllegalArgumentException("Illegal argument!");
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    @Override
    public boolean delete(Long aLong) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("UPDATE Developer D SET D.status = 1 WHERE D.id =: id")
                    .setParameter("id", aLong);
            query.executeUpdate();
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
