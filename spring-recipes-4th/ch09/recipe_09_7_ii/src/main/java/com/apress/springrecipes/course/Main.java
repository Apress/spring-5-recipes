package com.apress.springrecipes.course;

import java.util.GregorianCalendar;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.apress.springrecipes.course.config.CourseConfiguration;


public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(CourseConfiguration.class);
        CourseDao courseDao = context.getBean(CourseDao.class);

        Course course = new Course();
        course.setTitle("Core Spring");
        course.setBeginDate(new GregorianCalendar(2007, 8, 1).getTime());
        course.setEndDate(new GregorianCalendar(2007, 9, 1).getTime());
        course.setFee(1000);

        System.out.println("\nCourse before persisting");
        System.out.println(course);

        courseDao.store(course);

        System.out.println("\nCourse after persisting");
        System.out.println(course);

        Long courseId = course.getId();
        Course courseFromDb = courseDao.findById(courseId);

        System.out.println("\nCourse fresh from database");
        System.out.println(courseFromDb);

        courseDao.delete(courseId);

        ((ConfigurableApplicationContext) context).stop();

    }
}
