package com.apress.springrecipes.course.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.apress.springrecipes.course.Course;
import com.apress.springrecipes.course.CourseDao;

public class HibernateCourseDao implements CourseDao {

    private final SessionFactory sessionFactory;

    public HibernateCourseDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Course store(Course course) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            session.saveOrUpdate(course);
            tx.commit();
            return course;
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public void delete(Long courseId) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            Course course = session.get(Course.class, courseId);
            session.delete(course);
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public Course findById(Long courseId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Course.class, courseId);
        }
    }

    public List<Course> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT c FROM Course c", Course.class).list();
        }
    }

}
