package com.company.menu;

import com.company.classroom.Classroom;
import com.company.file.ManipulationFile;
import com.company.matter.Matter;
import com.company.user.User;
import com.company.user.type.Secretary;
import com.company.user.type.Student;
import com.company.user.type.Teacher;

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
        Matter matter1 = new Matter("Math", "2", "50","MANDATORY");
        Matter matter2 = new Matter("Biology", "3", "30","MANDATORY");
        Matter matter3 = new Matter("Fisic", "4", "70","MANDATORY");
////        ArrayList <Student> students = new ArrayList<>();
////        ArrayList <Classroom> classrooms = new ArrayList<>();
////        ArrayList <Teacher> teachers = new ArrayList<>();
//        ArrayList <Secretary> secretaries = new ArrayList<>();

        ManipulationFile file = new ManipulationFile();

        file.addMatter(matter1, matters);
        file.addMatter(matter2, matters);
        file.addMatter(matter3, matters);

        System.out.println(matters);

        file.readMatterFile(matters);

        System.out.println("\n\n"+ matters);

//        file.readStudentFile(matters, students);
//
//        file.readClassroomFile(matters, students, classrooms);
//
//        file.readTeacherFile(classrooms, teachers);
//
//        file.readSecretaryFile(secretaries);
//
//        Scanner scan = new Scanner(System.in);
//
//        String username, password;
//
//        System.out.println("Enter your username");
//        username = scan.next();
//        System.out.println("Enter your password");
//        password = scan.next();
//
//        System.out.println(file.searchStudent(username, students));
//
//        if(userTypeMenu() == 1){
//
//        }else if( userTypeMenu()==2){
//
//        }else if( userTypeMenu()==3){
//
//        }else{
//            System.out.println("User type not found");
//            userTypeMenu();
//        }
    }



}
