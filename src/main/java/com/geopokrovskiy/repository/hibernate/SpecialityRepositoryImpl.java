package com.geopokrovskiy.repository.hibernate;

import com.geopokrovskiy.model.Skill;
import com.geopokrovskiy.model.Speciality;
import com.geopokrovskiy.repository.SpecialityRepository;
import com.geopokrovskiy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class SpecialityRepositoryImpl implements SpecialityRepository {
    @Override
    public Speciality addNew(Speciality value) {
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
    public Speciality getById(Long aLong) {
        try (Session session = HibernateUtil.getSession()) {
            Query query = session.createQuery("FROM Speciality s WHERE s.id =: id AND s.status = 0").setParameter("id", aLong);
            return (Speciality) query.uniqueResult();
        }
    }

    @Override
    public List<Speciality> getAll() {
        try (Session session = HibernateUtil.getSession()) {
            Query query = session.createQuery("FROM Speciality s");
            return (List<Speciality>) query.list();
        }
    }

    @Override
    public Speciality update(Speciality value) {
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
            Query query = session.createQuery("UPDATE Speciality S SET S.status = 1 WHERE S.id =: id")
                    .setParameter("id", aLong);
            query.executeUpdate();
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
