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

    public int searchStudentPassword(String password, ArrayList<Student> students){
        int position = -1;
        int i = 0;
        for(Student s: students){
            if (s.getPassword().equals(password)){
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

    public int searchTeacherPassword(String password, ArrayList<Teacher> teachers){
        int position = -1;
        int i = 0;
        for(Teacher t: teachers){
            if (t.getPassword().equals(password)){
                position = i;
            }
            i++;
        }
        return position;
    }

    public int searchSecretary(String registry, ArrayList<Secretary> secretaries){
        int position = -1;
        int i = 0;
        for(Secretary s: secretaries){
            if (s.getRegistry().equals(registry)){
                position = i;
            }
            i++;
        }
        return position;
    }

    public int searchSecretaryPassword(String password, ArrayList<Secretary> secretaries){
        int position = -1;
        int i = 0;
        for(Secretary s: secretaries){
            if (s.getPassword().equals(password)){
                position = i;
            }
            i++;
        }
        return position;
    }

    //Read files and fill lists
    public void readMatterFile(ArrayList<Matter> matters) throws IOException {
        ArrayList<String> id = new ArrayList();
        ArrayList<String> name = new ArrayList();
        ArrayList<String> credits = new ArrayList();
        ArrayList<String> type = new ArrayList();

        int i = 0;

        String idAux= "";
        String nameAux = "";
        String creditsAux= "";
        String typeAux= "";


        //Clear array
        matters.clear();
        BufferedReader buffRead = new BufferedReader(new FileReader("matter.txt"));
        int counter = 0;
        int character = buffRead.read();
        while (character != -1) {
            if (";".equals(String.valueOf((char) character))) {
               counter++;
            } else if ("\n".equals(String.valueOf((char) character))) {
                Matter matter = new Matter(name.get(i), id.get(i), credits.get(i), type.get(i));
                matters.add(matter);
                counter = 0;
                i++;
            }else if("*".equals(String.valueOf((char) character))){
                idAux= "";
                nameAux = "";
                creditsAux= "";
                typeAux= "";
            } else {
                switch (counter){
                    case 0:
                        if(",".equals(String.valueOf((char) character))) {
                            id.add(idAux);
                        }else{
                            idAux = idAux.concat(String.valueOf((char) character));
                        }
                        break;
                    case 1:
                        if(",".equals(String.valueOf((char) character))){
                            name.add(nameAux);
                        } else {
                            nameAux = nameAux.concat(String.valueOf((char) character));
                        }
                        break;
                    case 2:
                        if(",".equals(String.valueOf((char) character))){
                            credits.add(creditsAux);
                        } else {
                            creditsAux = creditsAux.concat(String.valueOf((char) character));
                        }
                    case 3:
                        if(",".equals(String.valueOf((char) character))){
                            type.add(typeAux);
                        } else {
                            typeAux = typeAux.concat(String.valueOf((char) character));
                        }
                }
            }

            character = buffRead.read();
        }
        buffRead.close();
    }

    public void readStudentFile(ArrayList<Matter> matters, ArrayList<Student> students) throws IOException {
        ArrayList<String> name = new ArrayList();
        ArrayList<String> email = new ArrayList();
        ArrayList<String> password = new ArrayList();
        ArrayList<String> registry = new ArrayList();
        ArrayList<String> status = new ArrayList();
        ArrayList<String> idMatter = new ArrayList();
        ArrayList<Matter> mattersTemp = new ArrayList();
        mattersTemp.clear();


        int i = 0;


        String nameAux = "";
        String emailAux = "";
        String passwordAux = "";
        String registryAux = "";
        String statusAux = "";
        String idMatterAux = "";

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
                Student student = new Student(name.get(i), email.get(i), password.get(i), registry.get(i), status.get(i));
                for(String array: idMatter){
                    mattersTemp.add(matters.get(searchMatter(array, matters)));
                }
                for (Matter m: mattersTemp){
                    student.addMatter(m);
                }
                students.add(student);
                counter = 0;
                i++;
            }else if("*".equals(String.valueOf((char) character))){
                nameAux = "";
                emailAux = "";
                passwordAux = "";
                registryAux = "";
                statusAux = "";
                idMatterAux = "";
                idMatter.clear();
                mattersTemp.clear();
            } else  {
                switch (counter){
                    case 0:
                        if(",".equals(String.valueOf((char) character))){
                            registry.add(registryAux);
                        } else {
                            registryAux = registryAux.concat(String.valueOf((char) character));
                        }
                        break;
                    case 1:
                        if(",".equals(String.valueOf((char) character))){
                            name.add(nameAux);
                        } else {
                            nameAux = nameAux.concat(String.valueOf((char) character));
                        }
                        break;
                    case 2:
                        if(",".equals(String.valueOf((char) character))){
                            email.add(emailAux);
                        } else {
                            emailAux = emailAux.concat(String.valueOf((char) character));
                        };
                        break;
                    case 3:
                        if(",".equals(String.valueOf((char) character))){
                            password.add(passwordAux);
                        } else {
                            passwordAux = passwordAux.concat(String.valueOf((char) character));
                        };
                        break;
                    case 4:
                        if(",".equals(String.valueOf((char) character))){
                            status.add(statusAux);
                        } else {
                            statusAux = statusAux.concat(String.valueOf((char) character));
                        };
                        break;
                    case 5:
                        if(",".equals(String.valueOf((char) character))){
                           idMatter.add(idMatterAux);
                        } else if("#".equals(String.valueOf((char) character))) {
                            idMatterAux="";
                        }else{
                            idMatterAux = idMatterAux.concat(String.valueOf((char) character));
                        }
                        break;
                }
            }
            character = buffRead.read();
        }
        buffRead.close();
    }

    public void readClassroomFile(ArrayList<Matter> matters, ArrayList<Student> students, ArrayList<Classroom> classrooms) throws IOException {
        ArrayList<String> idMatter = new ArrayList();
        ArrayList<Matter> mattersTemp = new ArrayList();
        mattersTemp.clear();
        ArrayList<String> id = new ArrayList();
        ArrayList<String> name = new ArrayList();
        ArrayList<String> semester = new ArrayList();
        ArrayList<String> shift = new ArrayList();
        ArrayList<String> idStudent = new ArrayList();
        ArrayList<Student> studentsTemp = new ArrayList();
        studentsTemp.clear();
        int index = 0;
        int i = 0;
        String idAux= "";
        String idMatterAux= "";
        String nameAux= "";
        String semesterAux= "";
        String shiftAux= "";
        String idStudentAux= "";
        //Clear array
        classrooms.clear();
        BufferedReader buffRead = new BufferedReader(new FileReader("classroom.txt"));
        int counter = 0;
        int character = buffRead.read();
        while (character != -1) {
            if (";".equals(String.valueOf((char) character))) {
                counter++;
            } else if ("\n".equals(String.valueOf((char) character))) {
                index = searchMatter(idMatter.get(i), matters);
                mattersTemp.add(matters.get(0));
                Classroom classroom = new Classroom(mattersTemp.get(i), id.get(i), name.get(i), semester.get(i), shift.get(i));
                for (String array: idStudent){
                    studentsTemp.add(students.get(searchStudent(array,students)));
                }
                for (Student s: studentsTemp){
                    classroom.addStudent(s);
                }
                classrooms.add(classroom);
                counter = 0;
                i++;
            }else if ("*".equals(String.valueOf((char) character))){
                idAux = "";
                idMatterAux = "";
                nameAux = "";
                semesterAux = "";
                shiftAux = "";
                idStudentAux = "";
                studentsTemp.clear();
                idStudent.clear();
            } else {
                switch (counter){
                    case 0:
                        if(",".equals(String.valueOf((char) character))){
                            id.add(idAux);
                        } else {
                            idAux = idAux.concat(String.valueOf((char) character));
                        }
                        break;
                    case 1:
                        if(",".equals(String.valueOf((char) character))){
                            name.add(nameAux);
                        } else {
                            nameAux = nameAux.concat(String.valueOf((char) character));
                        }
                        break;
                    case 2:
                        if(",".equals(String.valueOf((char) character))){
                            idMatter.add(idMatterAux);
                        } else {
                            idMatterAux = idMatterAux.concat(String.valueOf((char) character));
                        }
                        break;
                    case 3:
                        if(",".equals(String.valueOf((char) character))){
                            semester.add(semesterAux);
                        } else {
                            semesterAux = semesterAux.concat(String.valueOf((char) character));
                        }
                        break;
                    case 4:
                        if(",".equals(String.valueOf((char) character))){
                            shift.add(shiftAux);
                        } else {
                            shiftAux = shiftAux.concat(String.valueOf((char) character));
                        }
                        break;
                    case 5:
                        if(",".equals(String.valueOf((char) character))){
                            idStudent.add(idStudentAux);
                        } else if ("#".equals(String.valueOf((char) character))){
                            idStudentAux="";
                        }else {
                            idStudentAux = idStudentAux.concat(String.valueOf((char) character));
                        }
                        break;
                }
            }
            character = buffRead.read();
        }
        buffRead.close();
    }

    public void readTeacherFile(ArrayList<Classroom> classrooms, ArrayList<Teacher> teachers) throws IOException {
        ArrayList<String> name = new ArrayList();
        ArrayList<String> email = new ArrayList();
        ArrayList<String> password = new ArrayList();
        ArrayList<String> registry = new ArrayList();
        ArrayList<String> status = new ArrayList();
        ArrayList<String> idClassroom = new ArrayList();
        ArrayList<Classroom> classroomsTemp = new ArrayList();
        classroomsTemp.clear();

        int i = 0;

        String nameAux = "";
        String emailAux = "";
        String passwordAux = "";
        String registryAux = "";
        String statusAux = "";
        String idClassroomAux = "";

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
                Teacher teacher = new Teacher(name.get(i), email.get(i), password.get(i), registry.get(i), status.get(i));
                for(String array : idClassroom){
                    classroomsTemp.add(classrooms.get(searchClassroom(array,classrooms)));
                }
                for (Classroom c: classroomsTemp){
                    teacher.addClassroom(c);
                }
                teachers.add(teacher);
                counter = 0;
                i++;
            }else if("*".equals(String.valueOf((char) character))){
                nameAux = "";
                emailAux = "";
                passwordAux = "";
                registryAux = "";
                statusAux = "";
                idClassroomAux = "";
                classroomsTemp.clear();
                idClassroom.clear();
            } else {
                switch (counter){
                    case 0:
                        if(",".equals(String.valueOf((char) character))){
                            registry.add(registryAux);
                        } else {
                            registryAux = registryAux.concat(String.valueOf((char) character));
                        }
                        break;
                    case 1:
                        if(",".equals(String.valueOf((char) character))){
                            name.add(nameAux);
                        } else {
                            nameAux = nameAux.concat(String.valueOf((char) character));
                        }
                        break;
                    case 2:
                        if(",".equals(String.valueOf((char) character))){
                            email.add(emailAux);
                        } else {
                            emailAux = emailAux.concat(String.valueOf((char) character));
                        }
                        break;
                    case 3:
                        if(",".equals(String.valueOf((char) character))){
                            password.add(passwordAux);
                        } else {
                            passwordAux = passwordAux.concat(String.valueOf((char) character));
                        }
                        break;
                    case 4:
                        if(",".equals(String.valueOf((char) character))){
                            status.add(statusAux);
                        } else {
                            statusAux = statusAux.concat(String.valueOf((char) character));
                        }
                        break;
                    case 5:
                        if( ",".equals(String.valueOf((char) character))){
                            idClassroom.add(idClassroomAux);
                        } else if("#".equals(String.valueOf((char) character))) {
                            idClassroomAux = "";
                        }else{
                            idClassroomAux = idClassroomAux.concat(String.valueOf((char) character));
                        }
                        break;
                }
            }
            character = buffRead.read();
        }
        buffRead.close();
    }

    public void readSecretaryFile(ArrayList<Secretary> secretaries) throws IOException {
        ArrayList<String> name = new ArrayList();
        ArrayList<String> email = new ArrayList();
        ArrayList<String> password = new ArrayList();
        ArrayList<String> registry = new ArrayList();
        ArrayList<String> status = new ArrayList();

        int i = 0;

        String nameAux = "";
        String emailAux = "";
        String passwordAux = "";
        String registryAux = "";
        String statusAux = "";

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
                Secretary secretary = new Secretary(name.get(i), email.get(i), password.get(i), registry.get(i), status.get(i));
                secretaries.add(secretary);
                counter = 0;
                i++;
            }else if("*".equals(String.valueOf((char) character))){
                nameAux = "";
                emailAux = "";
                passwordAux = "";
                registryAux = "";
                statusAux = "";
            } else {
                switch (counter){
                    case 0:
                        if(",".equals(String.valueOf((char) character))) {
                            registry.add(registryAux);
                        }else{
                            registryAux = registryAux.concat(String.valueOf((char) character));
                        }
                        break;
                    case 1:
                        if(",".equals(String.valueOf((char) character))) {
                            name.add(nameAux);
                        }else{
                            nameAux = nameAux.concat(String.valueOf((char) character));
                        }
                        break;
                    case 2:
                        if(",".equals(String.valueOf((char) character))) {
                            email.add(emailAux);
                        }else{
                            emailAux = emailAux.concat(String.valueOf((char) character));
                        }
                        break;
                    case 3:
                        if(",".equals(String.valueOf((char) character))) {
                            password.add(passwordAux);
                        }else{
                            passwordAux = passwordAux.concat(String.valueOf((char) character));
                        }
                        break;
                    case 4:
                        if(",".equals(String.valueOf((char) character))) {
                            status.add(statusAux);
                        }else{
                            statusAux = statusAux.concat(String.valueOf((char) character));
                        }
                        break;
                }
            }
            character = buffRead.read();
        }
        buffRead.close();
    }

    // check file
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
                matters.remove(index);
                for (Matter m : matters) {
                    writeMatter(m);
                }
                return true;
            }else {
                //System.out.println("Matter not found.");
                return false;
            }
        }
        //System.out.println("File not found.");
        return false;
    }

    public boolean deleteStudent(String registry, ArrayList<Student> students) throws IOException {
        int index;
        int cont = 0;
        //delete file
        if(deleteFile("student.txt")) {
            index = searchStudent(registry, students);
            if (index != -1) {
                students.remove(index);
                for (Student s : students) {
                    writeStudent(s);
                }
                return true;
            } else {
                //System.out.println("Student not found.");
                return false;
            }
        }
        //System.out.println("File not found.");
        return false;
    }

    public boolean deleteClassroom(String id, ArrayList<Classroom> classrooms) throws IOException {
        int index;
        int cont = 0;
        //delete file
        if(deleteFile("classroom.txt")) {
            index = searchClassroom(id, classrooms);
            if (index != -1) {
                classrooms.remove(index);
                for (Classroom c : classrooms) {
                    writeClassroom(c);
                }
                return true;
            } else {
                //System.out.println("Classroom not found.");
                return false;
            }
        }
        //System.out.println("File not found.");
        return false;
    }

    public boolean deleteTeacher(String registry, ArrayList<Teacher> teachers) throws IOException {
        int index;
        int cont = 0;
        //delete file
        if(deleteFile("teacher.txt")) {
            index = searchTeacher(registry, teachers);
            if (index != -1) {
                teachers.remove(index);
                for (Teacher t : teachers) {
                    writeTeacher(t);
                }
                return true;
            } else {
                //System.out.println("Teacher not found.");
                return false;
            }
        }
        //System.out.println("File not found.");
        return false;
    }

    public boolean deleteSecretary(String registry, ArrayList<Secretary> secretaries) throws IOException {
        int index;
        int cont = 0;
        //delete file
        if(deleteFile("secretary.txt")) {
            index = searchSecretary(registry, secretaries);
            if (index != -1) {
                secretaries.remove(index);
                for (Secretary t : secretaries) {
                    writeSecretary(t);
                }
                return true;
            } else {
                //System.out.println("Secretary not found.");
                return false;
            }
        }
        //System.out.println("File not found.");
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
            //System.out.println("Student not found.");
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
