package org.lazyeager.hibernate.dal;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.lazyeager.hibernate.model.Course;
import org.lazyeager.hibernate.model.Instructor;
import org.lazyeager.hibernate.model.InstructorDetail;

public class EagerLazyLoadingData {

    public static void main(String args[]) {

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Course.class).addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class).buildSessionFactory();


        Session session = sessionFactory.getCurrentSession();


        try {

            session.beginTransaction();

            int instructorId = 1;

            Instructor instructor = session.get(Instructor.class, instructorId);

            System.out.println("Instructor: " + instructor);

            System.out.println("Courses: " + instructor.getCourses());

            session.getTransaction().commit();

        } finally {
            session.close();
            sessionFactory.close();
        }

    }

}
