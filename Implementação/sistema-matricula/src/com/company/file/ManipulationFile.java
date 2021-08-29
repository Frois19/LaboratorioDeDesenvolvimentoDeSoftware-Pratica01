package com.company.file;

import com.company.classroom.Classroom;
import com.company.classroom.SemesterType;
import com.company.matter.Matter;
import com.company.user.type.Secretary;
import com.company.user.type.Student;
import com.company.user.type.Teacher;

import java.io.*;
import java.util.ArrayList;

public class ManipulationFile {

    //Write in files
    public void writeMatter(Matter matter) throws IOException {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter("matter.txt", true));
        buffWrite.append(matter.textFile());
        buffWrite.close();
    }

    public void writeStudent(Student student) throws IOException {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter("student.txt", true));
        buffWrite.append(student.textFile());
        buffWrite.close();
    }

    public void writeClassroom(Classroom classroom) throws IOException {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter("classroom.txt", true));
        buffWrite.append(classroom.textFile());
        buffWrite.close();
    }

    public void writeTeacher(Teacher teacher) throws IOException {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter("teacher.txt", true));
        buffWrite.append(teacher.textFile());
        buffWrite.close();
    }

    public void writeSecretary(Secretary secretary) throws IOException {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter("secretary.txt", true));
        buffWrite.append(secretary.textFile());
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

    public void addSecretary(Secretary secretary, ArrayList<Secretary> secretaries) throws IOException {
        writeSecretary(secretary);
        secretaries.add(secretary);
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

    public int searchSecretary(String registry, ArrayList<Secretary> secretaries){
        int position = -1;
        int i = 0;
        for(Secretary t: secretaries){
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
        int times = 1;
        int character = buffRead.read();
        while (character != -1) {
            if (";".equals(String.valueOf((char) character))) {
               counter++;
            } else if ("\n".equals(String.valueOf((char) character))) {
                Matter matter = new Matter(name, id, credits, type);
                matters.add(matter);
                counter = 0;
            } else {
                switch (counter){
                    case 0:
                        id = id.concat(String.valueOf((char) character));
                        break;
                    case 1:
                        name = name.concat(String.valueOf((char) character));
                        break;
                    case 2:
                        credits = credits.concat(String.valueOf((char) character));
                        break;
                    case 3:
                        type = type.concat(String.valueOf((char) character));
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
            if (";".equals(String.valueOf((char) character))) {
                counter++;
            } else if ("\n".equals(String.valueOf((char) character))) {
                Student student = new Student(name, email, password, registry, status);
                for (Matter m: mattersTemp){
                    student.addMatter(m);
                }
                students.add(student);
                counter = 0;
            } else {
                switch (counter){
                    case 0:
                        registry = registry.concat(String.valueOf((char) character));
                        break;
                    case 1:
                        name = name.concat(String.valueOf((char) character));
                        break;
                    case 2:
                        email = email.concat(String.valueOf((char) character));
                        break;
                    case 3:
                        password = password.concat(String.valueOf((char) character));
                        break;
                    case 4:
                        status = status.concat(String.valueOf((char) character));
                        break;
                    case 5:
                        if( ",".equals(String.valueOf((char) character))){
                            index = searchMatter(idMatter, matters);
                            mattersTemp.add(matters.get(index));
                            idMatter = "";
                        } else {
                            idMatter = idMatter.concat(String.valueOf((char) character));
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
            if (";".equals(String.valueOf((char) character))) {
                counter++;
            } else if ("\n".equals(String.valueOf((char) character))) {
                index = searchMatter(idMatter, matters);
                matter = matters.get(index);
                Classroom classroom = new Classroom(matter, id, name, semester, shift);
                for (Student s: studentsTemp){
                    classroom.addStudent(s);
                }
                classrooms.add(classroom);
                counter = 0;
//                idMatter = "";
//                id = "";
//                name = "";
//                semester = "";
//                shift = "";
//                idStudent = "";
            } else {
                switch (counter){
                    case 0:
                        id = id.concat(String.valueOf((char) character));
                        break;
                    case 1:
                        name = name.concat(String.valueOf((char) character));
                        break;
                    case 2:
                        idMatter = idMatter.concat(String.valueOf((char) character));
                        break;
                    case 3:
                        semester = semester.concat(String.valueOf((char) character));
                        break;
                    case 4:
                        shift = shift.concat(String.valueOf((char) character));
                        break;
                    case 5:
                        if( ",".equals(String.valueOf((char) character))){
                            index = searchStudent(idStudent, students);
                            studentsTemp.add(students.get(index));
                            idStudent = "";
                        } else {
                            idStudent = idStudent.concat(String.valueOf((char) character));
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
            if (";".equals(String.valueOf((char) character))) {
                counter++;
            } else if ("\n".equals(String.valueOf((char) character))) {
                Teacher teacher = new Teacher(name, email, password, registry, status);
                for (Classroom c: classroomsTemp){
                    teacher.addClassroom(c);
                }
                teachers.add(teacher);
                counter = 0;
            } else {
                switch (counter){
                    case 0:
                        registry = registry.concat(String.valueOf((char) character));
                        break;
                    case 1:
                        name = name.concat(String.valueOf((char) character));
                        break;
                    case 2:
                        email = email.concat(String.valueOf((char) character));
                        break;
                    case 3:
                        password = password.concat(String.valueOf((char) character));
                        break;
                    case 4:
                        status = status.concat(String.valueOf((char) character));
                        break;
                    case 5:
                        if( ",".equals(String.valueOf((char) character))){
                            index = searchClassroom(idClassroom, classrooms);
                            classroomsTemp.add(classrooms.get(index));
                            idClassroom = "";
                        } else {
                            idClassroom = idClassroom.concat(String.valueOf((char) character));
                        }
                        break;
                }
            }
            character = buffRead.read();
        }
        buffRead.close();
    }

    public void readSecretaryFile(ArrayList<Secretary> secretaries) throws IOException {
        String name = "";
        String email = "";
        String password = "";
        String registry = "";
        String status = "";
        int index = 0;
        //Clear array
        secretaries.clear();
        BufferedReader buffRead = new BufferedReader(new FileReader("secretary.txt"));
        int counter = 0;
        int character = buffRead.read();
        while (character != -1) {
            if (";".equals(String.valueOf((char) character))) {
                counter++;
            } else if ("\n".equals(String.valueOf((char) character))) {
                Secretary secretary = new Secretary(name, email, password, registry, status);
                secretaries.add(secretary);
                counter = 0;
            } else {
                switch (counter){
                    case 0:
                        registry = registry.concat(String.valueOf((char) character));
                        break;
                    case 1:
                        name = name.concat(String.valueOf((char) character));
                        break;
                    case 2:
                        email = email.concat(String.valueOf((char) character));
                        break;
                    case 3:
                        password = password.concat(String.valueOf((char) character));
                        break;
                    case 4:
                        status = status.concat(String.valueOf((char) character));
                        break;
                }
            }
            character = buffRead.read();
        }
        buffRead.close();
    }

    // verificar se exist arquivo
    public boolean checkFile (String path){
        File file = new File(path);
        if (file.exists()){
            return true;
        }
        return false;
    }

    //Delete full file
    public boolean deleteFile (String path){
        File file = new File(path);
        if (checkFile(path)){
            file.delete();
            return true;
        }
        return false;
    }

    //Delete in files
    public boolean deleteMatter(String id, ArrayList<Matter> matters) throws IOException {
        int index;
        int cont = 0;
        //delete file
        if(deleteFile("matter.txt")){
            index = searchMatter(id, matters);
            if (index != -1) {
                for (Matter m : matters) {
                    if (cont != index) {
                        writeMatter(m);
                    } else {
                        System.out.println(m.toString() + "\nMatter was deleted.");
                    }
                }
                return true;
            }else {
                System.out.println("Matter not found.");
                return false;
            }
        }
        System.out.println("File not found.");
        return false;
    }

    public boolean deleteStudent(String registry, ArrayList<Student> students) throws IOException {
        int index;
        int cont = 0;
        //delete file
        if(deleteFile("student.txt")) {
            index = searchStudent(registry, students);
            if (index != -1) {
                for (Student s : students) {
                    if (cont != index) {
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
        System.out.println("File not found.");
        return false;
    }

    public boolean deleteClassroom(String id, ArrayList<Classroom> classrooms) throws IOException {
        int index;
        int cont = 0;
        //delete file
        if(deleteFile("classroom.txt")) {
            index = searchClassroom(id, classrooms);
            if (index != -1) {
                for (Classroom c : classrooms) {
                    if (cont != index) {
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
        System.out.println("File not found.");
        return false;
    }

    public boolean deleteTeacher(String registry, ArrayList<Teacher> teachers) throws IOException {
        int index;
        int cont = 0;
        //delete file
        if(deleteFile("teacher.txt")) {
            index = searchTeacher(registry, teachers);
            if (index != -1) {
                for (Teacher t : teachers) {
                    if (cont != index) {
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
        System.out.println("File not found.");
        return false;
    }

    public boolean deleteSecretary(String registry, ArrayList<Secretary> secretaries) throws IOException {
        int index;
        int cont = 0;
        //delete file
        if(deleteFile("secretary.txt")) {
            index = searchSecretary(registry, secretaries);
            if (index != -1) {
                for (Secretary t : secretaries) {
                    if (cont != index) {
                        writeSecretary(t);
                    } else {
                        System.out.println(t.toString() + "\nSecretary was deleted.");
                    }
                }
                return true;
            } else {
                System.out.println("Secretary not found.");
                return false;
            }
        }
        System.out.println("File not found.");
        return false;
    }

    //Edit file
    public boolean editMatter(String id, Matter matter, ArrayList<Matter> matters) throws IOException {
        int index = searchMatter(id, matters);
        if (index == -1){
            System.out.println("Matter not found.");
            return false;
        }else {
            matters.remove(index);
            matters.add(matter);
            if(deleteMatter(id, matters)){
                writeMatter(matter);
                return true;
            }else{
                return false;
            }
        }
    }

    public boolean editStudent(String registry, Student student, ArrayList<Student> students) throws IOException {
        int index = searchStudent(registry, students);
        if (index == -1){
            System.out.println("Student not found.");
            return false;
        }else {
            students.remove(index);
            students.add(student);
            if(deleteStudent(registry, students)){
                writeStudent(student);
                return true;
            }else{
                return false;
            }
        }
    }

    public boolean editClassroom(String id, Classroom classroom, ArrayList<Classroom> classrooms) throws IOException {
        int index = searchClassroom(id, classrooms);
        if (index == -1){
            System.out.println("Classroom not found.");
            return false;
        }else {
            classrooms.remove(index);
            classrooms.add(classroom);
            if(deleteClassroom(id, classrooms)){
                writeClassroom(classroom);
                return true;
            }else{
                return false;
            }
        }
    }

    public boolean editTeacher(String registry, Teacher teacher, ArrayList<Teacher> teachers) throws IOException {
        int index = searchTeacher(registry, teachers);
        if (index == -1){
            System.out.println("Teacher not found.");
            return false;
        }else {
            teachers.remove(index);
            teachers.add(teacher);
            if(deleteTeacher(registry, teachers)){
                writeTeacher(teacher);
                return true;
            }else{
                return false;
            }
        }
    }


    public boolean editSecretary(String registry, Secretary secretary, ArrayList<Secretary> secretaries) throws IOException {
        int index = searchSecretary(registry, secretaries);
        if (index == -1){
            System.out.println("Secretary not found.");
            return false;
        }else {
            secretaries.remove(index);
            secretaries.add(secretary);
            if(deleteSecretary(registry, secretaries)){
                writeSecretary(secretary);
                return true;
            }else{
                return false;
            }
        }
    }
    
}
