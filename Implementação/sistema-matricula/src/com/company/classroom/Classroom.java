package com.company.classroom;

import com.company.matter.Matter;
import com.company.matter.MatterType;
import com.company.user.type.Student;
import com.company.user.type.Teacher;

import java.util.ArrayList;

public class Classroom {
    protected Matter matter;
    protected ArrayList<Student> students = new ArrayList();
    protected String id;
    protected String name;
    protected SemesterType semester;
    protected ShiftType shift;

    public Classroom(Matter matter, String id, String name, SemesterType semester, ShiftType shift) {
        this.matter = matter;
        this.id = id;
        this.name = name;
        this.semester = semester;
        this.shift = shift;
    }

    public Classroom(Matter matter, String id, String name, String semester, String shift) {
        this.matter = matter;
        this.id = id;
        this.name = name;
        setTextSemester(semester);
        setTextShift(shift);
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

    public SemesterType getSemester() {
        return semester;
    }

    public String getTextSemester() {
        if(getSemester() == SemesterType.FIRST){
            return "FIRST";
        } else {
            //SemesterType.SECOND
            return "SECOND";
        }
    }

    public void setSemester(SemesterType semester) {
        this.semester = semester;
    }

    public void setTextSemester(String semester) {
        if(semester.equals("FIRST")){
            this.semester=SemesterType.FIRST;
        } else {
            this.semester=SemesterType.SECOND;
        }
    }

    public ShiftType getShift() {
        return shift;
    }

    public String getTextShift() {
        if(getShift() == ShiftType.DAYTIME){
            return "DAYTIME";
        } else {
            //ShiftType.NIGHT
            return "NIGHT";
        }
    }

    public void setShift(ShiftType shift) {
        this.shift = shift;
    }

    public void setTextShift(String shift) {
        if(shift.equals("DAYTIME")){
            this.shift=ShiftType.DAYTIME;
        } else {
            this.shift=ShiftType.NIGHT;
        }
    }

    public void addStudent(Student student){
        students.add(student);
        //System.out.println("Added student.");
    }

    public String listStudents(){
        String listStudents = "\n\t- Students:";
        for(Student s: students){
            listStudents=listStudents.concat("\n\t\t- " + s.getName());
        }
        return listStudents;
    }

    public String listStudentsFile(){
        String listStudents = "";
        int position = 0;
        for(Student s: students){
            listStudents=listStudents.concat(s.getId() + ",#");
            position++;
        }
        listStudents=listStudents.concat(";");
        return listStudents;
    }

    public int searchStudent(String registry){
        int position = -1;
        int i = 0;
        for(Student s: students){
            if (s.getId().equals(registry)){
                position = i;
            }
            i++;
        }
        return position;
    }

    public boolean deleteStudent(String registry){
        if (searchStudent(registry) != -1){
            students.remove(searchStudent(registry));
            //System.out.println("Excluded student.");
            return true;
        } else {
            //System.out.println("Student not found.");
            return false;
        }
    }

    public String textFile() {
        return "*" + getId() + ",;" +
                getName() + ",;" +
                getMatter().getId()+ ",;" +
                getTextSemester() + ",;" +
                getTextShift() + ",;" +
                listStudentsFile() + "\n";
    }

    @Override
    public String toString() {
        return "\nClassroom Information" +
                "\n\t- Id: " + getId() +
                "\n\t- Name: " + getName() +
                "\n\t- Matter: " + getMatter().getName() +
                "\n\t- Semester: " + getTextSemester() +
                "\n\t- Shift: " + getTextShift() +
                listStudents()+ "\n";
    }
}
