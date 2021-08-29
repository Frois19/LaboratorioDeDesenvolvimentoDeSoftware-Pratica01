package com.company;

import com.company.menu.ManipulationMenu;
import com.company.user.StatusUser;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        ManipulationMenu menu = new ManipulationMenu();

        menu.menuSystem();
    }

}
