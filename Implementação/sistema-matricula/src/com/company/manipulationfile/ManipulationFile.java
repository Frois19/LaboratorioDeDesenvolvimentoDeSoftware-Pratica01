package com.company.manipulationfile;

import com.company.classroom.Classroom;
import com.company.matter.Matter;
import com.company.user.type.Student;
import com.company.user.type.Teacher;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ManipulationFile {
    //Write in files
    public void writeMatter(Matter matter) throws IOException {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter("matter.txt"));
        buffWrite.append(matter.textFile());
        buffWrite.close();
    }

    public void writeStudent(Student student) throws IOException {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter("student.txt"));
        buffWrite.append(student.textFile());
        buffWrite.close();
    }

    public void writeTeacher(Teacher teacher) throws IOException {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter("teacher.txt"));
        buffWrite.append(teacher.textFile());
        buffWrite.close();
    }

    public void writeClassroom(Classroom classroom) throws IOException {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter("classroom.txt"));
        buffWrite.append(classroom.textFile());
        buffWrite.close();
    }

    //Adding to lists
    public void addListMatter(Matter matter, ArrayList<Matter> matters) throws IOException {
        writeMatter(matter);
        matters.add(matter);
    }

    public void addListStudent(Student student, ArrayList<Student> students) throws IOException {
        writeStudent(student);
        students.add(student);
    }

    public void addListTeacher(Teacher teacher, ArrayList<Teacher> teachers) throws IOException {
        writeTeacher(teacher);
        teachers.add(teacher);
    }

    public void addListClassroom(Classroom classroom, ArrayList<Classroom> classrooms) throws IOException {
        writeClassroom(classroom);
        classrooms.add(classroom);
    }

    //Read files and fill lists
}
