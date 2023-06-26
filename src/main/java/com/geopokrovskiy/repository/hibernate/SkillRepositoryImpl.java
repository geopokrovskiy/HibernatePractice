package com.geopokrovskiy.repository.hibernate;

import com.geopokrovskiy.model.Skill;
import com.geopokrovskiy.repository.SkillRepository;
import com.geopokrovskiy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class SkillRepositoryImpl implements SkillRepository {
    @Override
    public Skill addNew(Skill value) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            session.save(value);
            session.getTransaction().commit();
            return value;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new IllegalArgumentException("Incorrect argument!");
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    @Override
    public Skill getById(Long aLong) {
        try (Session session = HibernateUtil.getSession()) {
            Query query = session.createQuery("FROM Skill s WHERE s.id =: id AND s.status = 0").setParameter("id", aLong);
            return (Skill) query.uniqueResult();
        }
    }

    @Override
    public List<Skill> getAll() {
        try (Session session = HibernateUtil.getSession()) {
            Query query = session.createQuery("FROM Skill s");
            return (List<Skill>) query.list();
        }
    }

    @Override
    public Skill update(Skill value) {
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
            Query query = session.createQuery("UPDATE Skill S SET S.status = 1 WHERE S.id =: id")
                    .setParameter("id", aLong);
            query.executeUpdate();
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
