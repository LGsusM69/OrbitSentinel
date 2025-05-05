package edu.utep.group9.controllers;

import edu.utep.group9.models.user.User;
import edu.utep.group9.util.MenuOptionsLoader;
import edu.utep.group9.view.ConsoleUI;

public class UIController {

    MenuOptionsLoader loader;
    ScientistController scientist;
    AdminController admin;
    ConsoleUI ui;
    String data;

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
                //authenticate user
                ui.loginMenu();
                break;
            case "main0":
                System.out.println("Exiting...");
                ui.stop();
                break;
            case "login1":
                System.out.println("Authenticating user[scientist]...");
                //authenticate user
                System.out.println("Logged in as scientist");
                ui.scientistMenu();
                break;
            case "login2":
                System.out.println("authenticating user[Admin]...");
                //authenticate user
                System.out.println("Logged in as admin");
                ui.adminMenu();
                break;
            case "login0":
                ui.mainMenu();
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
                if(scientist.assessDebre())
                    ui.printData("Data updated, report file created");
                ui.assessOrbitMenu();
                break;
            case "assess-orbit0":
                ui.scientistMenu();
                break;
            case "admin1":
                ui.createMenu();
                break;
            case "admin2": {
                String username = ui.manage();
                String user = AdminController.validateUsername(username);
                if(!user.equals("")) {
                    data = user;
                    ui.update(user);
                    break;
                } else {
                    ui.printData("User not found");
                    ui.adminMenu();
                    break;
                }
            }
            case "admin3":
                //delete user
                break;
            case "admin0":
                ui.mainMenu();
                break;
            case "create1", "create2", "create3", "create4": {
                String username = ui.promptUser("Create new username:");
                String password = ui.promptUser("Create new password:");
                User user = AdminController.createUser(username, password, input);
                if(user != null) ui.printData("User added to the system.\n" +
                        "Username: " + username + " Type: " + user.getType());
                else System.out.println("Failed to create new user");
                ui.adminMenu();
                break;
            }
            case "update1", "update2": {
                String field = "";
                switch (input) {
                    case 1: field = "username"; break;
                    case 2: field = "password"; break;
                }
                String value = ui.promptUser("Enter new " + field + " for [" + data.substring(0, data.indexOf(",")) + "] :");
                String statuss = AdminController.update(data, field, value);
                ui.printData(statuss);
                ui.adminMenu();
                break;
            }
            case "update3":
                ui.typeMenu(data);
                break;
            case "type1", "type2", "type3": {
                System.out.println("roastbeef: " + data);
                String statuss = AdminController.updateType(data, input);
                ui.printData(statuss);
                ui.adminMenu();
                break;
            }
        }
    }
}
