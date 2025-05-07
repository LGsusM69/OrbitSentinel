package edu.utep.group9.controllers;

import edu.utep.group9.io.Logger;
import edu.utep.group9.io.Logger.Action;
import edu.utep.group9.io.UserManager;
import edu.utep.group9.models.user.User;
import edu.utep.group9.util.MenuOptionsLoader;
import edu.utep.group9.view.ConsoleUI;

public class UIController {
    MenuOptionsLoader loader;
    TrackingSystem tracker;
    ConsoleUI ui;
    String choice;
    String data;
    Logger logger;
    String username;
    String type;

    public UIController() {
        this.tracker = new TrackingSystem();
        loader = new MenuOptionsLoader();
        this.logger = new Logger();
        ui = new ConsoleUI(this);
        ui.setMenu(loader.load());
        ui.run();
    }

    public UIController(TrackingSystem tracker) {
        this.tracker = tracker;
        loader = new MenuOptionsLoader();
        this.logger = new Logger();
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
            case "login1": {
                String error = login(choice);
                if(!error.equals("")) {
                    error = "Failed to login: " + error;
                    ui.printData(error);
                    ui.loginMenu();
                    break;
                }
                System.out.println("[" + username + "] Logged in as [" + type + "]");
                logger.login(username);
                ui.scientistMenu();
                break;
            }
            case "login2": {
                String error = login(choice);
                if(!error.equals("")) {
                    error = "Failed to login: " + error;
                    ui.printData(error);
                    ui.loginMenu();
                    break;
                }
                System.out.println("[" + username + "] Logged in as [" + type + "]");
                logger.login(username);
                ui.representativeMenu();
                break;
            }
            case "login3": {
                /*String error = login(choice);
                if(!error.equals("")) {
                    error = "Failed to login: " + error;
                    ui.printData(error);
                    ui.loginMenu();
                    break;
                }
                System.out.println("[" + username + "] Logged in as [" + type + "]");
                logger.update(Action.LOGIN, username);
                //ui.policymakerMenu();*/
                ui.loginMenu();
                break;
            }
            case "login4": {
                String error = login(choice);
                if(!error.equals("")) {
                    error = "Failed to login: " + error;
                    ui.printData(error);
                    ui.loginMenu();
                    break;
                }
                System.out.println("[" + username + "] Logged in as [" + type + "]");
                logger.login(username);
                ui.adminMenu();
                break;
            }
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
                logger.logout(username);
                break;
            case "track-space1":
                ui.printData(tracker.track("ROCKET BODY", "ALL"));
                Logger.readFile(username, "rso_metrics.csv");
                Logger.queryData(username, "ROCKET BODY", "ALL");
                ui.trackSpaceMenu();
                break;
            case "track-space2":
                ui.printData(tracker.track("DEBRIS", "ALL"));
                Logger.readFile(username, "rso_metrics.csv");
                Logger.queryData(username, "DEBRIS", "ALL");
                ui.trackSpaceMenu();
                break;
            case "track-space3":
                ui.printData(tracker.track("PAYLOAD", "ALL"));
                Logger.readFile(username, "rso_metrics.csv");
                Logger.queryData(username, "PAYLOAD", "ALL");
                ui.trackSpaceMenu();
                break;
            case "track-space4":
                ui.printData(tracker.track("UNKNOWN", "ALL"));
                Logger.readFile(username, "rso_metrics.csv");
                Logger.queryData(username,"UNKNOWN", "ALL");
                ui.trackSpaceMenu();
                break;
            case "track-space0":
                ui.scientistMenu();
                break;
            case "assess-orbit1":
                ui.printData(tracker.track("ALL", "LEO"));
                Logger.readFile(username, "rso_metrics.csv");
                Logger.queryData(username,"ALL", "LEO");
                ui.assessOrbitMenu();
                break;
            case "assess-orbit2":
                if(tracker.assessDebre()) {
                    ui.printData("Data updated, report file created");
                    Logger.readFile(username, "rso_metrics.csv");
                    Logger.queryData(username,"DEBRIS", "IN ORBIT");
                    Logger.generateData(username, "orbit-statuss-report.txt");
                    Logger.generateData(username, "data-out.csv");
                }
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
            case "admin3": {
                String username = ui.promptUser("Enter a username:");
                String user = AdminController.validateUsername(username);
                if(!user.equals("")) {
                    data = user;
                    ui.delete(user);
                } else {
                    System.out.println("User not found");
                    ui.adminMenu();
                }
                break;
            }
            case "admin0":
                logger.logout(username);
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
            case "create0":
                ui.adminMenu();
                break;
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
            case "update0":
                ui.adminMenu();
                break;
            case "type1", "type2", "type3", "type4": {
                String statuss = AdminController.updateType(data, input);
                ui.printData(statuss);
                ui.adminMenu();
                break;
            }
            case "delete1":
                AdminController.delete(data);
                ui.printData("User deleted");
                ui.adminMenu();
                break;
            case "delete2":
                ui.printData("Deletion aborted");
                ui.adminMenu();
                break;
            case "representative1":
                ui.printData("Long term impact:");
                ui.printData(tracker.analyzeImpact());
                ui.representativeMenu();
                break;
            case "representative2":
                ui.printData("Setting longitude range.");
                double lower = Double.valueOf(ui.promptUser("Enter lower bound:"));
                double upper = Double.valueOf(ui.promptUser("Enter upper bound:"));
                String data = tracker.densityReport(lower, upper);
                int index = data.indexOf('\n');
                String count = data.substring(0, index);
                data = data.substring(index+1);
                ui.printData("There are " + count + " objects in orbit\n" +
                        "in the range specified[" + lower + " - " + upper +
                        "]:");
                ui.printData(data);
                ui.representativeMenu();
                break;
            case "representative0":
                logger.logout(username);
                ui.mainMenu();
                break;
        }
    }
    private String login(String type) {
        String username = ui.promptUser("Enter your username:");
        String password = ui.promptUser("Enter your password:");
        String[] user = UserManager.authenticate(username, password, type).split(",");
        if(user.length > 1) {
            this.username = username;
            this.type = type;
            return "";
        }
        return user[0];
    }
    public void setChoice(String choice){this.choice = choice;}
}
