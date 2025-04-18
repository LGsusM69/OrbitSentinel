package edu.utep.group9.controllers;

import edu.utep.group9.view.ConsoleUI;

import java.io.Console;

public class UIController {


    ScientistController scientist;
    ConsoleUI ui;

    public UIController(ScientistController scientist) {
        this.scientist = scientist;
        ui = new ConsoleUI(this);
        ui.run();
    }

    public void handleInput(String state, int input) {
        switch (state + input) {
            case "main1":
                System.out.println("authenticating user[scientist]...");
                //authenticate user
                System.out.println("Logged in as scientist");
                ui.scientistMenu();
                break;
            case "main0":
                System.out.println("Exiting...");

                ui.stop();
            case "scientist1":
                ui.trackSpaceMenu();
                break;
            case "scientist2":
                break;
            case "scientist3":
                break;
            case "track-space1":
                scientist.track("rocket", "all");
        }
    }
}
