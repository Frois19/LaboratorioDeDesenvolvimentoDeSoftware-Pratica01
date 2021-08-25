package com.company.user.type;

import com.company.matter.Matter;
import com.company.user.User;
import java.util.ArrayList;

public class Student extends User {

    protected ArrayList<Matter> matters = new ArrayList();

    public Student(String name, String email, String password, String registry) {
        super(name, email, password, registry);
    }

    public void addMatter(Matter matter){
        matters.add(matter);
    }

    public String listMatters(){
        String listMatter = "\n\t- Matters:";
        for(Matter m: matters){
            listMatter.concat("\n\t\t- " + m.getName());
        }
        return listMatter;
    }

    public int searchMatter(String id){
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

    public boolean deleteMatter(String id){
        if (searchMatter(id) != -1){
            matters.remove(searchMatter(id));
            System.out.println("Excluded matter.");
            return true;
        } else {
            System.out.println("Matter not found.");
            return false;
        }
    }

    @Override
    public String toString() {
        return "\nStudent Information:" +
                "\n\t- Name: " + getName() +
                "\n\t- Registry: " + getRegistry() +
                "\n\t- E-mail:" + getEmail() +
                "\n\t- Status: " + getStatus() +
                listMatters();
    }

}
