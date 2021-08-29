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



    public int userTypeMenu (){
        int userType = 0;
        while(userType>= 1 && userType>=3){
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter your user type: \n[1] - Student \n[2] - Teacher \n[3] - Secretary");
            userType = scan.nextInt();
        }
        return userType;
    }

    public void menuSystem() throws IOException {
        ArrayList <Matter> matters = new ArrayList<>();
        ArrayList <Student> students = new ArrayList<>();
        ArrayList <Classroom> classrooms = new ArrayList<>();
        ArrayList <Teacher> teachers = new ArrayList<>();
        ArrayList <Secretary> secretaries = new ArrayList<>();

        ManipulationFile file = new ManipulationFile();

        file.deleteFile("matter.txt");
        file.deleteFile("student.txt");
        file.deleteFile("classroom.txt");
        file.deleteFile("teacher.txt");
        file.deleteFile("secretary.txt");

        Matter matter = new Matter("Math", "1245", 50, MatterType.MANDATORY);
        file.addMatter(matter, matters);

        Student student = new Student("Joao", "joao@email.com", "senha123", "123456");
        student.addMatter(matter);
        file.addStudent(student, students);

        Classroom classroom = new Classroom(matter, "789456123", "Engenharia", SemesterType.SECOND, ShiftType.DAYTIME);
        classroom.addStudent(student);
        file.addClassroom(classroom, classrooms);

        Teacher teacher = new Teacher("Lucas", "lucas@email.com", "senha123", "456789");
        teacher.addClassroom(classroom);
        file.addTeacher(teacher, teachers);

        Secretary secretary = new Secretary("Felipe", "felipe@email.com", "senha123", "987123");
        file.addSecretary(secretary,secretaries);

        System.out.println("\n\nPrint array after write");
        System.out.println(matters);
        System.out.println("-------");
        System.out.println(students);
        System.out.println("-------");
        System.out.println(classrooms);
        System.out.println("-------");
        System.out.println(teachers);
        System.out.println("-------");
        System.out.println(secretaries);
        System.out.println("-------");

        System.out.println("\n\nStart read files");
        file.readMatterFile(matters);
        file.readStudentFile(matters,students);
        file.readClassroomFile(matters, students, classrooms);
        file.readTeacherFile(classrooms, teachers);
        file.readSecretaryFile(secretaries);
        System.out.println("Finish read files");

        System.out.println("\n\nPrint array after read");
        System.out.println(matters);
        System.out.println("-------");
        System.out.println(students);
        System.out.println("-------");
        System.out.println(classrooms);
        System.out.println("-------");
        System.out.println(teachers);
        System.out.println("-------");
        System.out.println(secretaries);
        System.out.println("-------");
    }
}
