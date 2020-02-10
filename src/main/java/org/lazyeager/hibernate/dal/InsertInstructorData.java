package org.lazyeager.hibernate.dal;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.lazyeager.hibernate.model.Course;
import org.lazyeager.hibernate.model.Instructor;
import org.lazyeager.hibernate.model.InstructorDetail;

public class InsertInstructorData {

    public static void main(String args[]) {

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class).addAnnotatedClass(Course.class)
                .addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {

            session.beginTransaction();

            Instructor instructor[] = {
                    new Instructor("Optimus", "Prime", "OptimusPrime@email.com"),
                    new Instructor("Megatron", "101", "Megatron101@email.com")
            };

            InstructorDetail instructorDetail[] = {
                    new InstructorDetail("youtube.com/Optimus", "Driving"),
                    new InstructorDetail("youtube.com/Megatron", "Flying")
            };

            for (int i = 0; i < 2; i++) {

                instructor[i].setInstructorDetail(instructorDetail[i]);

                session.save(instructor[i]);

            }

            session.getTransaction().commit();

        } finally {
            session.close();
            sessionFactory.close();
        }

    }

}
