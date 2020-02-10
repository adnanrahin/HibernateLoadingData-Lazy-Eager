package org.lazyeager.hibernate.dal;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.lazyeager.hibernate.model.Course;
import org.lazyeager.hibernate.model.Instructor;
import org.lazyeager.hibernate.model.InstructorDetail;

import java.util.ArrayList;
import java.util.List;

public class InsturctorCourseInsertion {

    public static void main(String args[]) {

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Course.class).addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class).buildSessionFactory();


        Session session = sessionFactory.getCurrentSession();


        try {

            session.beginTransaction();

            List<Course> courses = new ArrayList<>();
            Course course1 = new Course("Machine Learning and Neural Network");
            Course course2 = new Course("Data Science and Machine Learning");
            courses.add(course1);
            courses.add(course2);

            Instructor instructor = session.get(Instructor.class, 1);
            course1.setInstructor(instructor);
            course2.setInstructor(instructor);

            instructor.setCourses(courses);

            session.save(course2);
            session.save(course1);

            session.getTransaction().commit();

        } finally {
            session.close();
            sessionFactory.close();
        }

    }

}
