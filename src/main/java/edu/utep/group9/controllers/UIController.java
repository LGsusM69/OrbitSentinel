package edu.utep.group9.controllers;

import edu.utep.group9.models.SpaceObject;
import edu.utep.group9.util.MenuOptionsLoader;
import edu.utep.group9.view.ConsoleUI;

import java.io.Console;
import java.util.List;

public class UIController {

    MenuOptionsLoader loader;
    ScientistController scientist;
    ConsoleUI ui;

    public UIController(ScientistController scientist) {
        this.scientist = scientist;
        loader = new MenuOptionsLoader();
        ui = new ConsoleUI(this);
        ui.setMenu(loader.load());
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
                break;
            case "scientist1":
                ui.trackSpaceMenu();
                break;
            case "scientist2":
                ui.assessOrbitMenu();
                break;
            case "scientist0":
                ui.mainMenu();
                break;
            case "track-space1":
                ui.printData(scientist.track("ROCKET BODY", "ALL"));
                ui.trackSpaceMenu();
                break;
            case "track-space2":
                ui.printData(scientist.track("DEBRIS", "ALL"));
                ui.trackSpaceMenu();
                break;
            case "track-space3":
                ui.printData(scientist.track("PAYLOAD", "ALL"));
                ui.trackSpaceMenu();
                break;
            case "track-space4":
                ui.printData(scientist.track("UNKNOWN", "ALL"));
                ui.trackSpaceMenu();
                break;
            case "track-space0":
                ui.scientistMenu();
                break;
            case "assess-orbit1":
                ui.printData(scientist.track("ALL", "LEO"));
                ui.assessOrbitMenu();
                break;
            case "assess-orbit2":
                scientist.assessDebre();
                ui.assessOrbitMenu();
                break;
            case "assess-orbit0":
                ui.scientistMenu();
                break;
        }
    }
}
