package com.company.menu;

import com.company.classroom.Classroom;
import com.company.classroom.SemesterType;
import com.company.classroom.ShiftType;
import com.company.file.ManipulationFile;
import com.company.matter.Matter;
import com.company.matter.MatterType;
import com.company.user.User;
import com.company.user.type.Secretary;
import com.company.user.type.Student;
import com.company.user.type.Teacher;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class ManipulationMenu {


    public int userTypeMenu() {
        int userType = 0;
        Scanner scan = new Scanner(System.in);
        while (userType < 1 || userType > 3) {
            System.out.println("Enter your user type: \n[1] - Student \n[2] - Teacher \n[3] - Secretary");
            userType = scan.nextInt();
        }
        return userType;
    }

    public void menuOptions() throws IOException {
        int userType = userTypeMenu();
        ManipulationFile file = new ManipulationFile();
        if (userType == 1) {
            if (file.checkFile("matter.txt")) {
                loginStudents();
            } else {
                System.out.println("Matter not found please register one");
            }
        } else if (userType == 2) {
            if (file.checkFile("matter.txt") && (file.checkFile("student.txt")) && (file.checkFile("classroom.txt"))) {
                loginTeachers();
            } else {
                System.out.println("Matter, student or classroom not found please register each one");
            }
        } else if (userType == 3) {
            if (file.checkFile("secretary.txt")) {
                loginSecretary();
            } else {
                System.out.println("Secretary not found please register each one");
            }
        }
    }

    public void loginSecretary() throws IOException {
        ArrayList<Secretary> secretaries = new ArrayList<>();
        ManipulationFile file = new ManipulationFile();
        Scanner scan = new Scanner(System.in);
        String registry, password;
        int checkSecretaryRegistry, checkSecretaryPassword;

        System.out.println("Enter your register");
        registry = scan.next();
        System.out.println("Enter your password");
        password = scan.next();

        file.readSecretaryFile(secretaries);
        checkSecretaryRegistry = file.searchSecretary(registry, secretaries);
        checkSecretaryPassword = file.searchSecretaryPassword(password, secretaries);

        if (checkSecretaryRegistry != -1 && checkSecretaryPassword != -1) {
            optionSecretary();
        } else {
            System.out.println("Register or password not found, incorrect");
        }

    }

    public void optionSecretary() throws IOException {
        ArrayList<Matter> matters = new ArrayList<>();
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Classroom> classrooms = new ArrayList<>();
        ArrayList<Teacher> teachers = new ArrayList<>();
        ArrayList<Secretary> secretaries = new ArrayList<>();


        ManipulationFile file = new ManipulationFile();
        file.readSecretaryFile(secretaries);
        file.readMatterFile(matters);
        file.readStudentFile(matters, students);
        file.readClassroomFile(matters, students, classrooms);
        file.readTeacherFile(classrooms, teachers);

        Scanner scan = new Scanner(System.in);
        int actionOption, actorOption, option;

        String name, email, password, registry, id, matterId;
        int  credits, positionMatter, checkRegister;
        int typeMatter =0;
        int typeShift= 0;
        int typeSemester = 0;

        actionOption = (action0ptionsSecretary() * 10);
        actorOption = actor0ptionsSecretary();
        option = actionOption + actorOption;


        switch (option) {
            case 11:
                System.out.println("Enter your name");
                name = scan.next();
                System.out.println("Enter your email");
                email = scan.next();
                System.out.println("Enter your password");
                password = scan.next();
                System.out.println("Enter your registry");
                registry = scan.next();
                checkRegister = file.searchStudent(registry, students);
                if(checkRegister==-1){
                    Student student = new Student(name, email, password, registry);
                    file.addStudent(student, students);
                    System.out.println("Successfully registered");
                }else{
                    System.out.println("Registry already exists, please use another");
                }
                break;
            case 12:
                System.out.println("Enter your name");
                name = scan.next();
                System.out.println("Enter your email");
                email = scan.next();
                System.out.println("Enter your password");
                password = scan.next();
                System.out.println("Enter your registry");
                registry = scan.next();
                checkRegister = file.searchTeacher(registry, teachers);
                if(checkRegister==-1){
                    Teacher teacher = new Teacher(name, email, password, registry);
                    file.addTeacher(teacher, teachers);
                    System.out.println("Successfully registered");
                }else{
                    System.out.println("Registry already exists, please use another");
                }
                break;
            case 13:
                System.out.println("Enter your name");
                name = scan.next();
                System.out.println("Enter your email");
                email = scan.next();
                System.out.println("Enter your password");
                password = scan.next();
                System.out.println("Enter your registry");
                registry = scan.next();
                checkRegister = file.searchSecretary(registry, secretaries);
                if(checkRegister==-1){
                    Secretary secretary = new Secretary(name, email, password, registry);
                    file.addSecretary(secretary, secretaries);
                    System.out.println("Successfully registered");
                }else{
                    System.out.println("Registry already exists, please use another");
                }
                break;
            case 14:
                System.out.println("Enter the name");
                name = scan.next();
                System.out.println("Enter the credits");
                credits = scan.nextInt();
                while (typeMatter < 1 || typeMatter > 2) {
                    System.out.println("Enter the type matter: \n[1] - Mandatory \n[2] - Optional");
                    typeMatter = scan.nextInt();
                }
                System.out.println("Enter the id");
                id = scan.next();
                checkRegister = file.searchMatter(id, matters);
                if(checkRegister==-1){
                    if(typeMatter==1){
                        Matter matter = new Matter(name, id, credits, MatterType.MANDATORY);
                        file.addMatter(matter, matters);
                    }else if(typeMatter==2){
                        Matter matter = new Matter(name, id, credits, MatterType.OPTIONAL);
                        file.addMatter(matter, matters);
                    }
                    System.out.println("Successfully registered");
                }else{
                    System.out.println("Registry already exists, please use another");
                }
                break;
            case 15:
                if (file.checkFile("matter.txt")){
                    System.out.println("Enter the matter id");
                    matterId = scan.next();
                    file.readMatterFile(matters);
                    positionMatter = file.searchMatter(matterId, matters);
                    if(positionMatter!=-1){
                        Matter matter = new Matter(matters.get(positionMatter).getName(),matters.get(positionMatter).getId(), matters.get(positionMatter).getCredits(), matters.get(positionMatter).getType());
                        System.out.println("Enter your name");
                        name = scan.next();
                        while(typeSemester < 1 || typeSemester > 2){
                            System.out.println("Enter your semester type: \n[1] - First \\n[2] - Second");
                            typeSemester = scan.nextInt();
                        }
                        while(typeShift < 1 || typeShift > 2){
                            System.out.println("Enter your shift type: \n[1] - DayTime \\n[2] - Night");
                            typeShift = scan.nextInt();
                        }
                        System.out.println("Enter your id");
                        id = scan.next();

                        checkRegister = file.searchClassroom(id, classrooms);
                        if(checkRegister==-1){
                            if(typeSemester==1 && typeShift==1){
                                Classroom classroom = new Classroom(matter, id, name, SemesterType.FIRST, ShiftType.DAYTIME);
                                file.addClassroom(classroom, classrooms);
                            }else if(typeSemester==1 && typeShift==2){
                                Classroom classroom = new Classroom(matter, id, name, SemesterType.FIRST, ShiftType.NIGHT);
                                file.addClassroom(classroom, classrooms);
                            }else if(typeSemester==2 && typeShift==1){
                                Classroom classroom = new Classroom(matter, id, name, SemesterType.SECOND, ShiftType.DAYTIME);
                                file.addClassroom(classroom, classrooms);
                            }else{
                                Classroom classroom = new Classroom(matter, id, name, SemesterType.SECOND, ShiftType.NIGHT);
                                file.addClassroom(classroom, classrooms);
                            }
                            System.out.println("Successfully registered");
                        }else{
                            System.out.println("Registry already exists, please use another");
                        }
                    }else {
                        System.out.println("Id matter not found");
                    }
                }else{
                    System.out.println("Matter not found please register one");
                }
                break;
            case 21:
                System.out.println("Enter your registry");
                registry = scan.next();
                if(file.deleteStudent(registry, students)){
                    System.out.println("Successfully deleted");
                }else{
                    System.out.println("Failed to delete");
                }
                break;
            case 22:
                System.out.println("Enter your registry");
                registry = scan.next();
                if(file.deleteTeacher(registry, teachers)){
                    System.out.println("Successfully deleted");
                }else{
                    System.out.println("Failed to delete");
                }
                break;
            case 23:
                System.out.println("Enter your registry");
                registry = scan.next();
                if(file.deleteSecretary(registry, secretaries)){
                    System.out.println("Successfully deleted");
                }else{
                    System.out.println("Failed to delete");
                }
                break;
            case 24:
                System.out.println("Enter the id");
                id = scan.next();
                if(file.deleteMatter(id, matters)){
                    System.out.println("Successfully deleted");
                }else{
                    System.out.println("Failed to delete");
                }
                break;
            case 25:
                System.out.println("Enter the id");
                id = scan.next();
                if(file.deleteClassroom(id, classrooms)){
                    System.out.println("Successfully deleted");
                }else{
                    System.out.println("Failed to delete");
                }
                break;
        }

    }

    public int action0ptionsSecretary() {
        int actionOption = 0;
        Scanner scan = new Scanner(System.in);
        System.out.println("What do you want to do: \n[1] - Register \n[2] - Remove");
        actionOption = scan.nextInt();
        while (actionOption < 1 || actionOption > 4) {
            System.out.println("What do you want to do: \n[1] - Register \n[2] - Remove");
            actionOption = scan.nextInt();
        }

        return actionOption;
    }


    public int actor0ptionsSecretary() {
        int actorOption = 0;
        Scanner scan = new Scanner(System.in);
        System.out.println("Who do you want: \n[1] - Student \n[2] - Teacher \n[3] - Secretary  \n[4] - Matter  \n[5] - Classroom");
        actorOption = scan.nextInt();
        while (actorOption < 1 || actorOption > 5) {
            System.out.println("Who do you want: \n[1] - Student \n[2] - Teacher \n[3] - Secretary  \n[4] - Matter  \n[5] - Classroom");
            actorOption = scan.nextInt();
        }

        return actorOption;
    }

    public void loginStudents() throws IOException {
        Scanner scan = new Scanner(System.in);
        ManipulationFile file = new ManipulationFile();
        ArrayList<Matter> matters = new ArrayList<>();
        file.readMatterFile(matters);
        ArrayList<Student> students = new ArrayList<>();
        file.readStudentFile(matters, students);
        Student student = null;
        String registry = null;
        String password = null;
        int aux = 0;
        while (aux == 0){
            System.out.println("Enter your register");
            registry = scan.next();
            int index = file.searchStudent(registry, students);
            if (index != -1) {
                student = students.get(index);
                System.out.println("Enter your password");
                password = scan.next();
                if (password.equals(student.getPassword())) {
                    aux = 1;
                } else {
                    System.out.println("Incorrect password");
                    loginStudents();
                }
            } else {
                System.out.println("Registry not found");
            }
        }
        optionsStudent(registry);
    }

    public int mainMenuStudent(){
        Scanner scan = new Scanner(System.in);
        int option = -1;
        while (option < 0 || option > 4) {
            System.out.print( "Students menu:\n\t" +
                    "Select options for:\n\t\t" +
                    "[1] - Check your information\n\t\t" +
                    "[2] - Subscribe in a matter\n\t\t" +
                    "[3] - Unsubscribe from a matter\n" +
                    "Your option is: "
            );
            option = scan.nextInt();
        }
        return option;
    }
    public void optionsStudent(String registry) throws IOException {
        Scanner scan = new Scanner(System.in);
        ManipulationFile file = new ManipulationFile();
        //read matters
        ArrayList<Matter> matters = new ArrayList<>();
        file.readMatterFile(matters);
        //read students
        ArrayList<Student> students = new ArrayList<>();
        file.readStudentFile(matters,students);
        //my profile
        Student myProfile = null;
        myProfile = students.get(file.searchStudent(registry,students));
        //call main menu
        int aux = 0;
        String matterId = null;
        switch (mainMenuStudent()){
            case 1:
                System.out.println(myProfile);
                optionsStudent(registry);
                break;
            case 2:
                while (aux == 0){
                    System.out.println("Insert matter id: ");
                    matterId = scan.next();
                    if(file.searchMatter(matterId, matters) != -1){
                        aux = 1;
                    } else {
                        System.out.println("Matter not found");
                    }
                }
                myProfile.addMatter(matters.get(file.searchMatter(matterId, matters)));
                file.deleteStudent(registry,students);
                file.writeStudent(myProfile);
                optionsStudent(registry);
                break;
            case 3:
                while (aux == 0){
                    System.out.println("Insert matter id: ");
                    matterId = scan.next();
                    if(file.searchMatter(matterId, matters) != -1){
                        aux = 1;
                    } else {
                        System.out.println("Matter not found");
                    }
                }
                myProfile.deleteMatter(matters.get(file.searchMatter(matterId, matters)).getId());
                file.deleteStudent(registry,students);
                file.writeStudent(myProfile);
                optionsStudent(registry);
                break;
            default:
                System.out.println("Select a valid option.");
                optionsStudent(registry);
                break;
        }
    }

    public void loginTeachers() throws IOException {
        Scanner scan = new Scanner(System.in);
        ManipulationFile file = new ManipulationFile();
        ArrayList<Matter> matters = new ArrayList<>();
        file.readMatterFile(matters);
        ArrayList<Student> students = new ArrayList<>();
        file.readStudentFile(matters, students);
        ArrayList<Classroom> classrooms = new ArrayList<>();
        file.readClassroomFile(matters, students, classrooms);
        ArrayList<Teacher> teachers = new ArrayList<>();
        file.readTeacherFile(classrooms, teachers);
        Teacher teacher = null;
        String registry = null;
        String password = null;
        int aux = 0;
        while (aux == 0){
            System.out.println("Enter your register");
            registry = scan.next();
            int index = file.searchTeacher(registry, teachers);
            if (index != -1) {
                teacher = teachers.get(index);
                System.out.println("Enter your password");
                password = scan.next();
                if (password.equals(teacher.getPassword())) {
                    aux = 1;
                } else {
                    System.out.println("Incorrect password");
                    loginTeachers();
                }
            } else {
                System.out.println("Registry not found");
            }
        }
        optionsTeacher(registry);
    }

    public int mainMenuTeacher(){
        Scanner scan = new Scanner(System.in);
        int option = -1;
        while (option < 0 || option > 4) {
            System.out.print( "Teachers menu:\n\t" +
                    "Select options for:\n\t\t" +
                    "[1] - Check your information\n\t\t" +
                    "[2] - Subscribe in a classroom\n\t\t" +
                    "[3] - Unsubscribe from a classroom\n" +
                    "Your option is: "
            );
            option = scan.nextInt();
        }
        return option;
    }
    public void optionsTeacher(String registry) throws IOException {
        Scanner scan = new Scanner(System.in);
        ManipulationFile file = new ManipulationFile();
        ArrayList<Matter> matters = new ArrayList<>();
        file.readMatterFile(matters);
        ArrayList<Student> students = new ArrayList<>();
        file.readStudentFile(matters, students);
        ArrayList<Classroom> classrooms = new ArrayList<>();
        file.readClassroomFile(matters, students, classrooms);
        ArrayList<Teacher> teachers = new ArrayList<>();
        file.readTeacherFile(classrooms, teachers);
        Teacher myProfile = null;
        myProfile = teachers.get(file.searchTeacher(registry,teachers));
        //call main menu
        int aux = 0;
        String classroomId = null;
        switch (mainMenuTeacher()){
            case 1:
                System.out.println(myProfile);
                optionsTeacher(registry);
                break;
            case 2:
                while (aux == 0){
                    System.out.println("Insert matter id: ");
                    classroomId = scan.next();
                    if(file.searchClassroom(classroomId, classrooms) != -1){
                        aux = 1;
                    } else {
                        System.out.println("Matter not found");
                    }
                }
                myProfile.addClassroom(classrooms.get(file.searchClassroom(classroomId, classrooms)));
                file.deleteTeacher(registry,teachers);
                file.writeTeacher(myProfile);
                optionsTeacher(registry);
                break;
            case 3:
                while (aux == 0){
                    System.out.println("Insert matter id: ");
                    classroomId = scan.next();
                    if(file.searchClassroom(classroomId, classrooms) != -1){
                        aux = 1;
                    } else {
                        System.out.println("Matter not found");
                    }
                }
                myProfile.deleteClassroom(classrooms.get(file.searchClassroom(classroomId, classrooms)).getId());
                file.deleteTeacher(registry,teachers);
                file.writeTeacher(myProfile);
                optionsTeacher(registry);
                break;
            default:
                System.out.println("Select a valid option.");
                optionsTeacher(registry);
                break;
        }
    }

}
