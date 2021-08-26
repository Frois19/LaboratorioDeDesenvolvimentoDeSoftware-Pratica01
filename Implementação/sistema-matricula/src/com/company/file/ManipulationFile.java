package com.company.file;

import com.company.classroom.Classroom;
import com.company.matter.Matter;
import com.company.matter.MatterType;
import com.company.user.type.Student;
import com.company.user.type.Teacher;

import java.io.*;
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

    public void writeClassroom(Classroom classroom) throws IOException {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter("classroom.txt"));
        buffWrite.append(classroom.textFile());
        buffWrite.close();
    }

    public void writeTeacher(Teacher teacher) throws IOException {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter("teacher.txt"));
        buffWrite.append(teacher.textFile());
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

    public void addListClassroom(Classroom classroom, ArrayList<Classroom> classrooms) throws IOException {
        writeClassroom(classroom);
        classrooms.add(classroom);
    }

    public void addListTeacher(Teacher teacher, ArrayList<Teacher> teachers) throws IOException {
        writeTeacher(teacher);
        teachers.add(teacher);
    }

    //Read files and fill lists
    public void readMatterFile(ArrayList<Matter> matters) throws IOException {
        String name = "";
        String id= "";
        String credits= "";
        String type= "";
        //Clear array
        matters.clear();
        BufferedReader buffRead = new BufferedReader(new FileReader("matter.txt"));
        int counter = 0;
        int character = buffRead.read();
        while (character != -1) {
            if (";".equals(String.valueOf(character))) {
               counter++;
            } else if ("\n".equals(String.valueOf(character))) {
                Matter matter = new Matter(name, id, credits, type);
                matters.add(matter);
                counter = 0;
            } else {
                switch (counter){
                    case 0:
                        id.concat(String.valueOf(character));
                        break;
                    case 1:
                        name.concat(String.valueOf(character));
                        break;
                    case 2:
                        credits.concat(String.valueOf(character));
                        break;
                    case 3:
                        type.concat(String.valueOf(character));
                        break;
                }
            }
            character = buffRead.read();
        }
        buffRead.close();
    }

    //Delete files and fill lists
}
