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
        ManipulationFile file = new ManipulationFile();
        Scanner scan = new Scanner(System.in);

        ArrayList<Object> matters = new ArrayList<>();
        ArrayList<Object> students = new ArrayList<>();
        ArrayList<Object> classrooms = new ArrayList<>();
        ArrayList<Object> teachers = new ArrayList<>();
        ArrayList<Object> secretaries = new ArrayList<>();

        file.readFiles(matters, students, classrooms, teachers, secretaries);

        Secretary secretary = null;
        String id = null;
        String password = null;
        int aux = 0;

        while (aux == 0) {
            System.out.println("Enter your id");
            id = scan.next();
            int index = file.searchObject(id, secretaries);
            if (index != -1) {
                secretary = (Secretary) secretaries.get(index);
                System.out.println("Enter your password");
                password = scan.next();
                if (password.equals(secretary.getPassword())) {
                    aux = 1;
                } else {
                    System.out.println("Incorrect password");
                    loginSecretary();
                }
            } else {
                System.out.println("Registry not found");
            }
        }
        optionsSecretary();

    }

    public void optionsSecretary() throws IOException {
        int actionOption = 0;
        int actorOption = 0;
        Scanner scan = new Scanner(System.in);
        while (actionOption < 1 || actionOption > 2) {
            System.out.println("What do you want to do: \n[1] - Register \n[2] - Remove");
            actionOption = scan.nextInt();
        }
        while (actorOption < 1 || actorOption > 5) {
            System.out.println("Who do you want: \n[1] - Student \n[2] - Teacher \n[3] - Secretary  \n[4] - Matter  \n[5] - Classroom");
            actorOption = scan.nextInt();
        }
        if (actionOption == 1) {
            optionSecretaryRegister(actorOption);
        } else {
            optionSecretaryRemove(actorOption);
        }
    }

    public void optionSecretaryRegister(int actorOption) throws IOException {


        ManipulationFile file = new ManipulationFile();

        ArrayList<Object> matters = new ArrayList<>();
        ArrayList<Object> students = new ArrayList<>();
        ArrayList<Object> classrooms = new ArrayList<>();
        ArrayList<Object> teachers = new ArrayList<>();
        ArrayList<Object> secretaries = new ArrayList<>();

        file.readFiles(matters, students, classrooms, teachers, secretaries);


        Scanner scan = new Scanner(System.in);

        String name = null, email = null, password = null, id = null, matterId = null;
        int credits, positionMatter, checkRegister;
        int typeMatter = 0;
        int typeShift = 0;
        int typeSemester = 0;

        if (actorOption <= 3) {
            System.out.println("Enter your name");
            name = scan.next();
            System.out.println("Enter your email");
            email = scan.next();
            System.out.println("Enter your password");
            password = scan.next();
            System.out.println("Enter your id");
            id = scan.next();
        }
        switch (actorOption) {
            case 1:
                checkRegister = file.searchObject(id, students);
                if (checkRegister == -1) {
                    Student student = new Student(name, email, password, id);
                    file.addObject(student, students);
                    System.out.println("Successfully registered");
                } else {
                    System.out.println("Registry already exists, please use another");
                }
                break;
            case 2:
                checkRegister = file.searchObject(id, teachers);
                if (checkRegister == -1) {
                    Teacher teacher = new Teacher(name, email, password, id);
                    file.addObject(teacher, teachers);
                    System.out.println("Successfully registered");
                } else {
                    System.out.println("Registry already exists, please use another");
                }
                break;
            case 3:
                checkRegister = file.searchObject(id, secretaries);
                if (checkRegister == -1) {
                    Secretary secretary = new Secretary(name, email, password, id);
                    file.addObject(secretary, secretaries);
                    System.out.println("Successfully registered");
                } else {
                    System.out.println("Registry already exists, please use another");
                }
                break;
            case 4:
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
                checkRegister = file.searchObject(id, matters);
                if (checkRegister == -1) {
                    if (typeMatter == 1) {
                        Matter matter = new Matter(name, id, credits, MatterType.MANDATORY);
                        file.addObject(matter, matters);
                    } else if (typeMatter == 2) {
                        Matter matter = new Matter(name, id, credits, MatterType.OPTIONAL);
                        file.addObject(matter, matters);
                    }
                    System.out.println("Successfully registered");
                } else {
                    System.out.println("Registry already exists, please use another");
                }
                break;
            case 5:
                if (file.checkFile("matter.txt")) {
                    System.out.println("Enter the matter id");
                    matterId = scan.next();
                    file.readMatterFile(matters);
                    positionMatter = file.searchObject(matterId, matters);
                    if (positionMatter != -1) {
                        Matter matter = (Matter) matters.get(file.searchObject(matterId, matters));
                        //Matter matter = new Matter( (Matter) matters.get(positionMatter).getName(), matters.get(positionMatter).getId(), matters.get(positionMatter).getCredits(), matters.get(positionMatter).getType());
                        System.out.println("Enter your name");
                        name = scan.next();
                        while (typeSemester < 1 || typeSemester > 2) {
                            System.out.println("Enter your semester type: \n[1] - First \\n[2] - Second");
                            typeSemester = scan.nextInt();
                        }
                        while (typeShift < 1 || typeShift > 2) {
                            System.out.println("Enter your shift type: \n[1] - DayTime \\n[2] - Night");
                            typeShift = scan.nextInt();
                        }
                        System.out.println("Enter your id");
                        id = scan.next();

                        checkRegister = file.searchObject(id, classrooms);
                        if (checkRegister == -1) {
                            if (typeSemester == 1 && typeShift == 1) {
                                Classroom classroom = new Classroom(matter, id, name, SemesterType.FIRST, ShiftType.DAYTIME);
                                file.addObject(classroom, classrooms);
                            } else if (typeSemester == 1 && typeShift == 2) {
                                Classroom classroom = new Classroom(matter, id, name, SemesterType.FIRST, ShiftType.NIGHT);
                                file.addObject(classroom, classrooms);
                            } else if (typeSemester == 2 && typeShift == 1) {
                                Classroom classroom = new Classroom(matter, id, name, SemesterType.SECOND, ShiftType.DAYTIME);
                                file.addObject(classroom, classrooms);
                            } else {
                                Classroom classroom = new Classroom(matter, id, name, SemesterType.SECOND, ShiftType.NIGHT);
                                file.addObject(classroom, classrooms);
                            }
                            System.out.println("Successfully registered");
                        } else {
                            System.out.println("Registry already exists, please use another");
                        }
                    } else {
                        System.out.println("Id matter not found");
                    }
                } else {
                    System.out.println("Matter not found please register one");
                }
                break;
        }

    }

    public void optionSecretaryRemove(int actorOption) throws IOException {

        ManipulationFile file = new ManipulationFile();

        ArrayList<Object> matters = new ArrayList<>();
        ArrayList<Object> students = new ArrayList<>();
        ArrayList<Object> classrooms = new ArrayList<>();
        ArrayList<Object> teachers = new ArrayList<>();
        ArrayList<Object> secretaries = new ArrayList<>();

        file.readFiles(matters, students, classrooms, teachers, secretaries);

        Scanner scan = new Scanner(System.in);
        String id;

        switch (actorOption) {
            case 1:
                System.out.println("Enter your id");
                id = scan.next();
                if (file.deleteObject(id, students)) {
                    System.out.println("Successfully deleted");
                } else {
                    System.out.println("Failed to delete");
                }
                break;
            case 2:
                System.out.println("Enter your id");
                id = scan.next();
                if (file.deleteObject(id, teachers)) {
                    System.out.println("Successfully deleted");
                } else {
                    System.out.println("Failed to delete");
                }
                break;
            case 3:
                System.out.println("Enter your id");
                id = scan.next();
                if (file.deleteObject(id, secretaries)) {
                    System.out.println("Successfully deleted");
                } else {
                    System.out.println("Failed to delete");
                }
                break;
            case 4:
                System.out.println("Enter the id");
                id = scan.next();
                if (file.deleteObject(id, matters)) {
                    System.out.println("Successfully deleted");
                } else {
                    System.out.println("Failed to delete");
                }
                break;
            case 5:
                System.out.println("Enter the id");
                id = scan.next();
                if (file.deleteObject(id, classrooms)) {
                    System.out.println("Successfully deleted");
                } else {
                    System.out.println("Failed to delete");
                }
                break;
        }
    }

    public void loginStudents() throws IOException {
        Scanner scan = new Scanner(System.in);
        ManipulationFile file = new ManipulationFile();

        ArrayList<Object> matters = new ArrayList<>();
        ArrayList<Object> students = new ArrayList<>();
        ArrayList<Object> classrooms = new ArrayList<>();
        ArrayList<Object> teachers = new ArrayList<>();
        ArrayList<Object> secretaries = new ArrayList<>();

        file.readFiles(matters, students, classrooms, teachers, secretaries);

        Student student = null;
        String registry = null;
        String password = null;
        int aux = 0;
        while (aux == 0) {
            System.out.println("Enter your id");
            registry = scan.next();
            int index = file.searchObject(registry, students);
            if (index != -1) {
                student = (Student) students.get(index);
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

    public int mainMenuStudent() {
        Scanner scan = new Scanner(System.in);
        int option = -1;
        while (option < 0 || option > 4) {
            System.out.print("Students menu:\n\t" +
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

        ArrayList<Object> matters = new ArrayList<>();
        ArrayList<Object> students = new ArrayList<>();
        ArrayList<Object> classrooms = new ArrayList<>();
        ArrayList<Object> teachers = new ArrayList<>();
        ArrayList<Object> secretaries = new ArrayList<>();

        file.readFiles(matters, students, classrooms, teachers, secretaries);


        Student myProfile = null;
        myProfile = (Student) students.get(file.searchObject(registry, students));
        //call main menu
        int aux = 0;
        String matterId = null;
        switch (mainMenuStudent()) {
            case 1:
                System.out.println(myProfile);
                optionsStudent(registry);
                break;
            case 2:
                while (aux == 0) {
                    System.out.println("Insert matter id: ");
                    matterId = scan.next();
                    if (file.searchObject(matterId, matters) != -1) {
                        aux = 1;
                    } else {
                        System.out.println("Matter not found");
                    }
                }
                myProfile.addMatter((Matter) matters.get(file.searchObject(matterId, matters)));
                file.deleteObject(registry, students);
                file.writeFile(myProfile);
                optionsStudent(registry);
                break;
            case 3:
                while (aux == 0) {
                    System.out.println("Insert matter id: ");
                    matterId = scan.next();
                    if (file.searchObject(matterId, matters) != -1) {
                        aux = 1;
                    } else {
                        System.out.println("Matter not found");
                    }
                }
                Matter matter = (Matter) matters.get(file.searchObject(matterId, matters));
                myProfile.deleteMatter(matter.getId());
                
                file.deleteObject(registry, students);
                file.writeFile(myProfile);
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

        ArrayList<Object> matters = new ArrayList<>();
        ArrayList<Object> students = new ArrayList<>();
        ArrayList<Object> classrooms = new ArrayList<>();
        ArrayList<Object> teachers = new ArrayList<>();
        ArrayList<Object> secretaries = new ArrayList<>();

        file.readFiles(matters, students, classrooms, teachers, secretaries);

        Teacher teacher = null;
        String registry = null;
        String password = null;
        int aux = 0;
        while (aux == 0) {
            System.out.println("Enter your id");
            registry = scan.next();
            int index = file.searchObject(registry, teachers);
            if (index != -1) {
                teacher = (Teacher) teachers.get(index);
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

    public int mainMenuTeacher() {
        Scanner scan = new Scanner(System.in);
        int option = -1;
        while (option < 0 || option > 4) {
            System.out.print("Teachers menu:\n\t" +
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
        ArrayList<Object> matters = new ArrayList<>();
        ArrayList<Object> students = new ArrayList<>();
        ArrayList<Object> classrooms = new ArrayList<>();
        ArrayList<Object> teachers = new ArrayList<>();
        ArrayList<Object> secretaries = new ArrayList<>();

        file.readFiles(matters, students, classrooms, teachers, secretaries);

        Teacher myProfile = null;

        myProfile = (Teacher) teachers.get(file.searchObject(registry, teachers));
        //call main menu
        int aux = 0;
        String classroomId = null;
        switch (mainMenuTeacher()) {
            case 1:
                System.out.println(myProfile);
                optionsTeacher(registry);
                break;
            case 2:
                while (aux == 0) {
                    System.out.println("Insert matter id: ");
                    classroomId = scan.next();
                    if (file.searchObject(classroomId, classrooms) != -1) {
                        aux = 1;
                    } else {
                        System.out.println("Matter not found");
                    }
                }
                myProfile.addClassroom((Classroom) classrooms.get(file.searchObject(classroomId, classrooms)));
                file.deleteObject(registry, teachers);
                file.writeFile(myProfile);
                optionsTeacher(registry);
                break;
            case 3:
                while (aux == 0) {
                    System.out.println("Insert matter id: ");
                    classroomId = scan.next();
                    if (file.searchObject(classroomId, classrooms) != -1) {
                        aux = 1;
                    } else {
                        System.out.println("Matter not found");
                    }
                }
                Classroom classroom = (Classroom) classrooms.get(file.searchObject(classroomId, classrooms));
                myProfile.deleteClassroom(classroom.getId());
                file.deleteObject(registry, teachers);
                file.writeFile(myProfile);
                optionsTeacher(registry);
                break;
            default:
                System.out.println("Select a valid option.");
                optionsTeacher(registry);
                break;
        }
    }

}
