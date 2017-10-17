package com.apress.springrecipes.course;

import java.util.GregorianCalendar;

import com.apress.springrecipes.course.jpa.JpaCourseDao;


public class Main {
    public static void main(String[] args) {

        CourseDao courseDao = new JpaCourseDao();

        Course course = new Course();
        course.setTitle("Core Spring");
        course.setBeginDate(new GregorianCalendar(2007, 8, 1).getTime());
        course.setEndDate(new GregorianCalendar(2007, 9, 1).getTime());
        course.setFee(1000);

        System.out.println("\nCourse before persisting");
        System.out.println(course);

        Course persisted = courseDao.store(course);

        System.out.println("\nCourse after persisting");
        System.out.println(persisted);

        Long courseId = persisted.getId();
        Course courseFromDb = courseDao.findById(courseId);

        System.out.println("\nCourse fresh from database");
        System.out.println(courseFromDb);

        courseDao.delete(courseId);

        System.exit(0);
    }
}
