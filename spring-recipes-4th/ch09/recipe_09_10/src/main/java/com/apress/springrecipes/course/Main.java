package com.apress.springrecipes.course;

import java.util.GregorianCalendar;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.apress.springrecipes.course.config.CourseConfiguration;


public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(CourseConfiguration.class);
        CourseRepository courseDao = context.getBean(CourseRepository.class);

        Course course = new Course();
        course.setTitle("Core Spring");
        course.setBeginDate(new GregorianCalendar(2007, 8, 1).getTime());
        course.setEndDate(new GregorianCalendar(2007, 9, 1).getTime());
        course.setFee(1000);

        System.out.println("\nCourse before persisting");
        System.out.println(course);

        Course persisted = courseDao.save(course);

        System.out.println("\nCourse after persisting");
        System.out.println(persisted);

        Long courseId = persisted.getId();

        System.out.println("\nCourse fresh from database");
        courseDao.findById(courseId).ifPresent(System.out::println);

        courseDao.deleteById(courseId);

    }
}
