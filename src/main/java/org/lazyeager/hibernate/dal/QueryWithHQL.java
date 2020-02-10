package org.lazyeager.hibernate.dal;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.lazyeager.hibernate.model.Course;
import org.lazyeager.hibernate.model.Instructor;
import org.lazyeager.hibernate.model.InstructorDetail;

public class QueryWithHQL {

    public static void main(String args[]) {

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class).buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            int instructorId = 1;

            Query<Instructor> query = session.createQuery("select i from Instructor i "
                    + "JOIN FETCH i.courses "
                    + "where i.id=:instructorId");

            query.setParameter("instructorId", instructorId);

            Instructor instructor = query.getSingleResult();

            System.out.println("Insturctor: " + instructor);

            session.getTransaction().commit();

        } finally {
            session.close();
            sessionFactory.close();
        }

    }

}
