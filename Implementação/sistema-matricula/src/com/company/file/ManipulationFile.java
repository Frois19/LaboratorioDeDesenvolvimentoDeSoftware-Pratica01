package com.company.file;

import com.company.classroom.Classroom;
import com.company.matter.Matter;
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

    //Adding to lists and write in files
    public void addMatter(Matter matter, ArrayList<Matter> matters) throws IOException {
        writeMatter(matter);
        matters.add(matter);
    }

    public void addStudent(Student student, ArrayList<Student> students) throws IOException {
        writeStudent(student);
        students.add(student);
    }

    public void addClassroom(Classroom classroom, ArrayList<Classroom> classrooms) throws IOException {
        writeClassroom(classroom);
        classrooms.add(classroom);
    }

    public void addTeacher(Teacher teacher, ArrayList<Teacher> teachers) throws IOException {
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

    //Delete in files
    public boolean deleteMatter(String id, ArrayList<Matter> matters) throws IOException {
        int index;
        int cont = 0;
        //delete file
        File file = new File("matter.txt");
        if (file.exists()){
            file.delete();
        }
        index = searchMatter(id, matters);
        if (index != -1){
            for (Matter m: matters){
                if (cont != index){
                    writeMatter(m);
                } else {
                    System.out.println(m.toString() + "\nMatter was deleted.");
                }
            }
            return true;
        } else {
            System.out.println("Matter not found.");
            return false;
        }
    }

    public boolean deleteStudent(String registry, ArrayList<Student> students) throws IOException {
        int index;
        int cont = 0;
        //delete file
        File file = new File("student.txt");
        if (file.exists()){
            file.delete();
        }
        index = searchStudent(registry, students);
        if (index != -1){
            for (Student s: students){
                if (cont != index){
                    writeStudent(s);
                } else {
                    System.out.println(s.toString() + "\nStudent was deleted.");
                }
            }
            return true;
        } else {
            System.out.println("Student not found.");
            return false;
        }
    }

    public boolean deleteClassroom(String id, ArrayList<Classroom> classrooms) throws IOException {
        int index;
        int cont = 0;
        //delete file
        File file = new File("classroom.txt");
        if (file.exists()){
            file.delete();
        }
        index = searchClassroom(id, classrooms);
        if (index != -1){
            for (Classroom c: classrooms){
                if (cont != index){
                    writeClassroom(c);
                } else {
                    System.out.println(c.toString() + "\nClassroom was deleted.");
                }
            }
            return true;
        } else {
            System.out.println("Classroom not found.");
            return false;
        }
    }

    public boolean deleteTeacher(String registry, ArrayList<Teacher> teachers) throws IOException {
        int index;
        int cont = 0;
        //delete file
        File file = new File("teacher.txt");
        if (file.exists()){
            file.delete();
        }
        index = searchTeacher(registry, teachers);
        if (index != -1){
            for (Teacher t: teachers){
                if (cont != index){
                    writeTeacher(t);
                } else {
                    System.out.println(t.toString() + "\nTeacher was deleted.");
                }
            }
            return true;
        } else {
            System.out.println("Teacher not found.");
            return false;
        }
    }

    //Edit file



}
