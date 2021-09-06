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
    public void writeFile(Object object) throws IOException {
        if (object instanceof Matter){
            BufferedWriter buffWrite = new BufferedWriter(new FileWriter("matter.txt", true));
            Matter matter = (Matter)object;
            buffWrite.append(matter.textFile());
            buffWrite.close();
        } else if (object instanceof Student){
            BufferedWriter buffWrite = new BufferedWriter(new FileWriter("student.txt", true));
            Student student = (Student)object;
            buffWrite.append(student.textFile());
            buffWrite.close();
        } else if (object instanceof Classroom){
            BufferedWriter buffWrite = new BufferedWriter(new FileWriter("classroom.txt", true));
            Classroom classroom = (Classroom)object;
            buffWrite.append(classroom.textFile());
            buffWrite.close();
        } else if (object instanceof Teacher){
            BufferedWriter buffWrite = new BufferedWriter(new FileWriter("teacher.txt", true));
            Teacher teacher = (Teacher)object;
            buffWrite.append(teacher.textFile());
            buffWrite.close();
        } else if (object instanceof Secretary){
            BufferedWriter buffWrite = new BufferedWriter(new FileWriter("secretary.txt", true));
            Secretary secretary = (Secretary)object;
            buffWrite.append(secretary.textFile());
            buffWrite.close();
        }
    }

    //Adding to lists and write in files
    public void addObject(Object object, ArrayList<Object> objects) throws IOException {
        if (object instanceof Matter){
            Matter matter = (Matter)object;
            writeFile(matter);
            objects.add(matter);
        } else if (object instanceof Student){
            Student student = (Student)object;
            writeFile(student);
            objects.add(student);
        } else if (object instanceof Classroom){
            Classroom classroom = (Classroom)object;
            writeFile(classroom);
            objects.add(classroom);
        } else if (object instanceof Teacher){
            Teacher teacher = (Teacher)object;
            writeFile(teacher);
            objects.add(teacher);
        } else if (object instanceof Secretary){
            Secretary secretary = (Secretary)object;
            writeFile(secretary);
            objects.add(secretary);
        }
    }
    //Search arrays
    public int searchObject(String id, ArrayList<Object> objects){
        int position = -1;
        if (objects.get(0) instanceof Matter){
            Matter matter = null;
            for (int i = 0; i < objects.size(); i++){
                matter = (Matter) objects.get(i);
                if (matter.getId().equals(id)){
                    position = i;
                }
            }
        } else if (objects.get(0) instanceof Student){
            Student student = null;
            for (int i = 0; i < objects.size(); i++){
                student = (Student) objects.get(i);
                if (student.getId().equals(id)){
                    position = i;
                }
            }
        } else if (objects.get(0) instanceof Classroom){
            Classroom classroom = null;
            for (int i = 0; i < objects.size(); i++){
                classroom = (Classroom) objects.get(i);
                if (classroom.getId().equals(id)){
                    position = i;
                }
            }
        } else if (objects.get(0) instanceof Teacher){
            Teacher teacher = null;
            for (int i = 0; i < objects.size(); i++){
                teacher = (Teacher) objects.get(i);
                if (teacher.getId().equals(id)){
                    position = i;
                }
            }
        } else if (objects.get(0) instanceof Secretary){
            Secretary secretary = null;
            for (int i = 0; i < objects.size(); i++){
                secretary = (Secretary) objects.get(i);
                if (secretary.getId().equals(id)){
                    position = i;
                }
            }
        }
        return position;
    }

    //Read files and fill lists
    public void readFiles(ArrayList<Object> matters, ArrayList<Object> students, ArrayList<Object> classrooms, ArrayList<Object> teachers, ArrayList<Object> secretaries) throws IOException{
        readMatterFile(matters);
        readStudentFile(matters, students);
        readClassroomFile(matters, students, classrooms);
        readTeacherFile(classrooms, teachers);
        readSecretaryFile(secretaries);
    }

    public void readMatterFile(ArrayList<Object> matters) throws IOException {
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

    public void readStudentFile(ArrayList<Object> matters, ArrayList<Object> students) throws IOException {
        ArrayList<String> name = new ArrayList();
        ArrayList<String> email = new ArrayList();
        ArrayList<String> password = new ArrayList();
        ArrayList<String> id = new ArrayList();
        ArrayList<String> status = new ArrayList();
        ArrayList<String> idMatter = new ArrayList();
        ArrayList<Object> mattersTemp = new ArrayList();
        mattersTemp.clear();


        int i = 0;


        String nameAux = "";
        String emailAux = "";
        String passwordAux = "";
        String idAux = "";
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
                Student student = new Student(name.get(i), email.get(i), password.get(i), id.get(i), status.get(i));
                for(String array: idMatter){
                    mattersTemp.add(matters.get(searchObject(array, matters)));
                }
                for (Object m: mattersTemp){
                    student.addMatter((Matter) m);
                }
                students.add(student);
                counter = 0;
                i++;
            }else if("*".equals(String.valueOf((char) character))){
                nameAux = "";
                emailAux = "";
                passwordAux = "";
                idAux = "";
                statusAux = "";
                idMatterAux = "";
                idMatter.clear();
                mattersTemp.clear();
            } else  {
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

    public void readClassroomFile(ArrayList<Object> matters, ArrayList<Object> students, ArrayList<Object> classrooms) throws IOException {
        ArrayList<String> idMatter = new ArrayList();
        ArrayList<Object> mattersTemp = new ArrayList();
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
                index = searchObject(idMatter.get(i), matters);
                mattersTemp.add(matters.get(0));
                Classroom classroom = new Classroom((Matter)mattersTemp.get(i), id.get(i), name.get(i), semester.get(i), shift.get(i));
                for (String array: idStudent){
                    studentsTemp.add((Student) students.get(searchObject(array,students)));
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

    public void readTeacherFile(ArrayList<Object> classrooms, ArrayList<Object> teachers) throws IOException {
        ArrayList<String> name = new ArrayList();
        ArrayList<String> email = new ArrayList();
        ArrayList<String> password = new ArrayList();
        ArrayList<String> id = new ArrayList();
        ArrayList<String> status = new ArrayList();
        ArrayList<String> idClassroom = new ArrayList();
        ArrayList<Classroom> classroomsTemp = new ArrayList();
        classroomsTemp.clear();

        int i = 0;

        String nameAux = "";
        String emailAux = "";
        String passwordAux = "";
        String idAux = "";
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
                Teacher teacher = new Teacher(name.get(i), email.get(i), password.get(i), id.get(i), status.get(i));
                for(String array : idClassroom){
                    classroomsTemp.add((Classroom) classrooms.get(searchObject(array,classrooms)));
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
                idAux = "";
                statusAux = "";
                idClassroomAux = "";
                classroomsTemp.clear();
                idClassroom.clear();
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

    public void readSecretaryFile(ArrayList<Object> secretaries) throws IOException {
        ArrayList<String> name = new ArrayList();
        ArrayList<String> email = new ArrayList();
        ArrayList<String> password = new ArrayList();
        ArrayList<String> id = new ArrayList();
        ArrayList<String> status = new ArrayList();

        int i = 0;

        String nameAux = "";
        String emailAux = "";
        String passwordAux = "";
        String idAux = "";
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
                Secretary secretary = new Secretary(name.get(i), email.get(i), password.get(i), id.get(i), status.get(i));
                secretaries.add(secretary);
                counter = 0;
                i++;
            }else if("*".equals(String.valueOf((char) character))){
                nameAux = "";
                emailAux = "";
                passwordAux = "";
                idAux = "";
                statusAux = "";
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
    public boolean deleteObject(String id, ArrayList<Object> objects) throws IOException {
        int index;
        int cont = 0;
        //delete file
        if (objects.get(0) instanceof Matter){
            if(deleteFile("matter.txt")){
                index = searchObject(id, objects);
                if (index != -1) {
                    objects.remove(index);
                    for (Object obj : objects) {
                        writeFile((Matter) obj);
                    }
                    return true;
                }else {
                    //System.out.println("Matter not found.");
                    return false;
                }
            }
        } else if (objects.get(0) instanceof Student){
            if(deleteFile("student.txt")){
                index = searchObject(id, objects);
                if (index != -1) {
                    objects.remove(index);
                    for (Object obj : objects) {
                        writeFile((Student) obj);
                    }
                    return true;
                }else {
                    //System.out.println("Matter not found.");
                    return false;
                }
            }
        } else if (objects.get(0) instanceof Classroom){
            if(deleteFile("classroom.txt")){
                index = searchObject(id, objects);
                if (index != -1) {
                    objects.remove(index);
                    for (Object obj : objects) {
                        writeFile((Classroom) obj);
                    }
                    return true;
                }else {
                    //System.out.println("Matter not found.");
                    return false;
                }
            }
        } else if (objects.get(0) instanceof Teacher){
            if(deleteFile("teacher.txt")){
                index = searchObject(id, objects);
                if (index != -1) {
                    objects.remove(index);
                    for (Object obj : objects) {
                        writeFile((Teacher) obj);
                    }
                    return true;
                }else {
                    //System.out.println("Matter not found.");
                    return false;
                }
            }
        } else if (objects.get(0) instanceof Secretary){
            if(deleteFile("secretary.txt")){
                index = searchObject(id, objects);
                if (index != -1) {
                    objects.remove(index);
                    for (Object obj : objects) {
                        writeFile((Secretary) obj);
                    }
                    return true;
                }else {
                    //System.out.println("Matter not found.");
                    return false;
                }
            }
        }
        return false;
    }
}
