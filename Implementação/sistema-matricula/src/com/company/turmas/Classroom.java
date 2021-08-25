package com.company.turmas;

import com.company.matter.Matter;
import com.company.user.type.Student;
import com.company.user.type.Teacher;

import java.util.ArrayList;

public class Classroom {
    protected Teacher teacher;
    protected Matter matter;
    protected ArrayList<Student> students = new ArrayList();
    protected String id;
    protected String name;
    protected String year;
    protected SemesterType semester;
    protected ShiftType shift;

    public Classroom(Teacher teacher, Matter matter, String id, String name, String year, SemesterType semester, ShiftType shift) {
        this.teacher = teacher;
        this.matter = matter;
        this.id = id;
        this.name = name;
        this.year = year;
        this.semester = semester;
        this.shift = shift;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Matter getMatter() {
        return matter;
    }

    public void setMatter(Matter matter) {
        this.matter = matter;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public SemesterType getSemester() {
        return semester;
    }

    public void setSemester(SemesterType semester) {
        this.semester = semester;
    }

    public ShiftType getShift() {
        return shift;
    }

    public void setShift(ShiftType shift) {
        this.shift = shift;
    }

    public void addStudent(Student student){
        students.add(student);
    }

    public String listStudents(){
        String listStudents = "\n\t- Students:";
        for(Student s: students){
            listStudents.concat("\n\t\t- " + s.getName());
        }
        return listStudents;
    }

    public int searchStudent(String registry){
        int position = -1;
        int i = 0;
        for(Student s: students){
            if (s.getRegistry().equals(registry)){
                position = i;
            }
            i++;
        }
        return position;
    }

    public boolean deleteStudent(String registry){
        if (searchStudent(registry) != -1){
            students.remove(searchStudent(registry));
            System.out.println("Excluded student.");
            return true;
        } else {
            System.out.println("Student not found.");
            return false;
        }
    }

}
