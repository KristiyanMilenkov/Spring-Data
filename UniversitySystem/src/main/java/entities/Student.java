package entities;

import jakarta.persistence.*;

import java.util.Set;


@Entity(name = "student")
public class Student extends Person{
    @Column(name = "average_grade", nullable = false)
    private float averageGrade;

    private int attendance;
    @ManyToMany
    @JoinTable(name = "students_courses",
            joinColumns =
                @JoinColumn(name = "student_id",referencedColumnName = "id"),
            inverseJoinColumns =
                @JoinColumn(name = "course_id",referencedColumnName = "id"))
    private Set<Course>courses;

    public Student(String firstName, String lastName, String phoneNumber,float averageGrade, int attendance) {
        super(firstName, lastName, phoneNumber);
        this.averageGrade = averageGrade;
        this.attendance = attendance;
    }

    public Student() {
        super();
    }

    public float getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(float averageGrade) {
        this.averageGrade = averageGrade;
    }

    public int getAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }
}
