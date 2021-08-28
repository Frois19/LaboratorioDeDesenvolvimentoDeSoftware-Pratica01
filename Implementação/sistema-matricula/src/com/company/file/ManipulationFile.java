package com.company.file;

import com.company.classroom.Classroom;
import com.company.classroom.SemesterType;
import com.company.classroom.ShiftType;
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

    //Search arrays
    public int searchMatter(String id, ArrayList<Matter> matters){
        int position = -1;
        int i = 0;
        for(Matter m: matters){
            if (m.getId().equals(id)){
                position = i;
            }
            i++;
        }
        return position;
    }

    public int searchStudent(String registry, ArrayList<Student> students){
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

    public int searchClassroom(String id, ArrayList<Classroom> classrooms){
        int position = -1;
        int i = 0;
        for(Classroom c: classrooms){
            if (c.getId().equals(id)){
                position = i;
            }
            i++;
        }
        return position;
    }

    public int searchTeacher(String registry, ArrayList<Teacher> teachers){
        int position = -1;
        int i = 0;
        for(Teacher t: teachers){
            if (t.getRegistry().equals(registry)){
                position = i;
            }
            i++;
        }
        return position;
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

    public void readStudentFile(ArrayList<Matter> matters, ArrayList<Student> students) throws IOException {
        String name = "";
        String email = "";
        String password = "";
        String registry = "";
        String status = "";
        String idMatter = "";
        ArrayList<Matter> mattersTemp = new ArrayList();
        int index = 0;
        //Clear array
        students.clear();
        BufferedReader buffRead = new BufferedReader(new FileReader("student.txt"));
        int counter = 0;
        int character = buffRead.read();
        while (character != -1) {
            if (";".equals(String.valueOf(character))) {
                counter++;
            } else if ("\n".equals(String.valueOf(character))) {
                Student student = new Student(name, email, password, registry, status);
                for (Matter m: mattersTemp){
                    student.addMatter(m);
                }
                students.add(student);
                counter = 0;
            } else {
                switch (counter){
                    case 0:
                        registry.concat(String.valueOf(character));
                        break;
                    case 1:
                        name.concat(String.valueOf(character));
                        break;
                    case 2:
                        email.concat(String.valueOf(character));
                        break;
                    case 3:
                        password.concat(String.valueOf(character));
                        break;
                    case 4:
                        status.concat(String.valueOf(character));
                        break;
                    case 5:
                        if( ",".equals(String.valueOf(character))){
                            index = searchMatter(idMatter, matters);
                            mattersTemp.add(matters.get(index));
                            idMatter = "";
                        } else {
                            idMatter.concat(String.valueOf(character));
                        }
                        break;
                }
            }
            character = buffRead.read();
        }
        buffRead.close();
    }

    public void readClassroomFile(ArrayList<Matter> matters, ArrayList<Student> students, ArrayList<Classroom> classrooms) throws IOException {
        String idMatter = "";
        Matter matter;
        String id = "";
        String name = "";
        String semester = "";
        String shift = "";
        String idStudent = "";
        ArrayList<Student> studentsTemp = new ArrayList();
        int index = 0;
        //Clear array
        classrooms.clear();
        BufferedReader buffRead = new BufferedReader(new FileReader("classroom.txt"));
        int counter = 0;
        int character = buffRead.read();
        while (character != -1) {
            if (";".equals(String.valueOf(character))) {
                counter++;
            } else if ("\n".equals(String.valueOf(character))) {
                index = searchMatter(idMatter, matters);
                matter = matters.get(index);
                Classroom classroom = new Classroom(matter, id, name, semester, shift);
                for (Student s: studentsTemp){
                    classroom.addStudent(s);
                }
                classrooms.add(classroom);
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
                        idMatter.concat(String.valueOf(character));
                        break;
                    case 3:
                        semester.concat(String.valueOf(character));
                        break;
                    case 4:
                        shift.concat(String.valueOf(character));
                        break;
                    case 5:
                        if( ",".equals(String.valueOf(character))){
                            index = searchStudent(idStudent, students);
                            studentsTemp.add(students.get(index));
                            idStudent = "";
                        } else {
                            idStudent.concat(String.valueOf(character));
                        }
                        break;
                }
            }
            character = buffRead.read();
        }
        buffRead.close();
    }

    public void readTeacherFile(ArrayList<Classroom> classrooms, ArrayList<Teacher> teachers) throws IOException {
        String name = "";
        String email = "";
        String password = "";
        String registry = "";
        String status = "";
        String idClassroom = "";
        ArrayList<Classroom> classroomsTemp = new ArrayList();
        int index = 0;
        //Clear array
        teachers.clear();
        BufferedReader buffRead = new BufferedReader(new FileReader("teacher.txt"));
        int counter = 0;
        int character = buffRead.read();
        while (character != -1) {
            if (";".equals(String.valueOf(character))) {
                counter++;
            } else if ("\n".equals(String.valueOf(character))) {
                Teacher teacher = new Teacher(name, email, password, registry, status);
                for (Classroom c: classroomsTemp){
                    teacher.addClassroom(c);
                }
                teachers.add(teacher);
                counter = 0;
            } else {
                switch (counter){
                    case 0:
                        registry.concat(String.valueOf(character));
                        break;
                    case 1:
                        name.concat(String.valueOf(character));
                        break;
                    case 2:
                        email.concat(String.valueOf(character));
                        break;
                    case 3:
                        password.concat(String.valueOf(character));
                        break;
                    case 4:
                        status.concat(String.valueOf(character));
                        break;
                    case 5:
                        if( ",".equals(String.valueOf(character))){
                            index = searchClassroom(idClassroom, classrooms);
                            classroomsTemp.add(classrooms.get(index));
                            idClassroom = "";
                        } else {
                            idClassroom.concat(String.valueOf(character));
                        }
                        break;
                }
            }
            character = buffRead.read();
        }
        buffRead.close();
    }

    //Delete files and fill lists
}
