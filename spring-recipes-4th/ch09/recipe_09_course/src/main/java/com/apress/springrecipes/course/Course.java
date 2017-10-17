package com.apress.springrecipes.course;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "COURSE")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "TITLE", length = 100, nullable = false)
    private String title;
    @Column(name = "BEGIN_DATE")
    private Date beginDate;
    @Column(name = "END_DATE")
    private Date endDate;
    @Column(name = "FEE")
    private int fee;

    public Course() {
    }

    public Course(String title, Date beginDate, Date endDate, int fee) {
        this.title = title;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.fee = fee;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Course [" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", fee=" + fee +
                ']';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return fee == course.fee &&
                Objects.equals(title, course.title) &&
                Objects.equals(beginDate, course.beginDate) &&
                Objects.equals(endDate, course.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, beginDate, endDate, fee);
    }
}
