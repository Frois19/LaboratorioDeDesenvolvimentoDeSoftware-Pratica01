package com.company.user.type;

import com.company.classroom.Classroom;
import com.company.matter.Matter;
import com.company.user.User;

import java.util.ArrayList;

public class Teacher extends User {

    protected ArrayList<Classroom> classrooms = new ArrayList();

    public Teacher(String name, String email, String password, String registry) {
        super(name, email, password, registry);
    }

    public void addClassroom(Classroom classroom){
        classrooms.add(classroom);
    }

    public String listClassrooms(){
        String listClassroom = "\n\t- Classrooms:";
        for(Classroom c: classrooms){
            listClassroom.concat("\n\t\t- " + c.getName());
        }
        return listClassroom;
    }

    public String listClassroomsFile(){
        String listMatter = "";
        int position = 0;
        for(Classroom c: classrooms){
            if (position<classrooms.size()-1) {
                listMatter.concat(c.getId() + ",");
            } else {
                listMatter.concat(c.getId() + ";");
            }
            position++;
        }
        return listMatter;
    }

    public int searchClassroom(String id){
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

    public boolean deleteClassroom(String id){
        if (searchClassroom(id) != -1){
            classrooms.remove(searchClassroom(id));
            System.out.println("Excluded classroom.");
            return true;
        } else {
            System.out.println("Classroom not found.");
            return false;
        }
    }

    public String listStudents(String id){
        if (searchClassroom(id) != -1){
            return classrooms.get(searchClassroom(id)).listStudents();
        } else {
            System.out.println("Classroom not found.");
            return "";
        }
    }

    public String textFile() {
        return getRegistry() + ";" +
                getName() + ";" +
                getEmail() + ";" +
                getPassword() + ";" +
                getTextStatus() + ";" +
                listClassroomsFile() + "\n";
    }

    @Override
    public String toString() {
        return "\nTeacher Information:" +
                "\n\t- Name: " + getName() +
                "\n\t- Registry: " + getRegistry() +
                "\n\t- E-mail:" + getEmail() +
                "\n\t- Status: " + getTextStatus() +
                listClassrooms();
    }
}


