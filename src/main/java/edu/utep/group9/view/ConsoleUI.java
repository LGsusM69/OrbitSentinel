package edu.utep.group9.view;

import edu.utep.group9.controllers.UIController;
import edu.utep.group9.models.Menu;

import java.util.*;


/**This class is a console user interface.
 * Uses the default System input and output.
 * then it delegates actions to the controller.*/
public class ConsoleUI {
    private UIController uiControl;
    private String data;

    private Scanner sc;
    private boolean running;

    /** determines the current state of the menu.
     * options are displayed according to this.
     */
    private String state;

    /**All possible menu options are saved here.*/
    private HashMap<String, List<Menu>> menu;


    public ConsoleUI(UIController uiControl) {
        this.uiControl = uiControl;
        running = false;
        sc = new Scanner(System.in);
    }

    /**This method controls the flow of the user interface*/
    public void run() {
        running = true;
        int input;
        System.out.println("welcome to Orbit Sentinel.");
        mainMenu();
        while(running) {
            input = getInput();
            if(isValidInput(input)) uiControl.handleInput(state, input);
        }
    }
    
    /**
     * Stops the main loop of the user interface
     */
    public void stop() {
        running = false;
    }
    
    /**
     * Flushes the scanner if needed and
     * takes input from the default system in.
     * @return user input.
     */
    private int getInput() {
        int input = -1;
        try {
            input = sc.nextInt();
            if(sc.hasNextLine()) sc.nextLine();
        } catch (InputMismatchException e) {
            sc.nextLine();
        }
        return input;
    }
    public String promptUser(String prompt) {
        System.out.println(prompt);
        return sc.nextLine();
    }
    
    /** Makes sure that the input is a valid choice
     * in the UI or returns the user to the same menu.
     * @param input user input.
     * @return true if input is valid.
     */
    private boolean isValidInput(int input) {
        List<Menu> options = menu.get(state);
        for (Menu option : options) {
            if(option.getValue() == input) {
                uiControl.setChoice(option.getLabel());
                return true;
            }
        }
        System.out.println("Invalid input.");
        switch(state) {
            case "main": mainMenu(); break;
            case "login": loginMenu(); break;
            case "scientist": scientistMenu(); break;
            case "track-space": trackSpaceMenu(); break;
            case "assess-orbit": assessOrbitMenu(); break;
            case "admin": adminMenu(); break;
            case "create": createMenu(); break;
            case "update": update(data); break;
            case "representative": representativeMenu(); break;
        }
        return false;
    }
    public void printData(String data) {
        System.out.println(data);
    }
    public void setMenu(HashMap<String, List<Menu>> menu) {
        this.menu = menu;
    }

    /*The following methods display the
    appropriate menu options in the console
     */
    public void mainMenu() {
        state = "main";
        System.out.println("Main menu:");
        for (Menu option : menu.get("main")) {
            System.out.println("\t" + option.getValue() + ") " + option.getLabel());
        }
    }
    public void loginMenu() {
        state = "login";
        System.out.println("Login as:");
        for (Menu option : menu.get("login")) {
            System.out.println("\t" + option.getValue() + ") " + option.getLabel());
        }
    }
    public void scientistMenu() {
        state = "scientist";
        System.out.println("username [type]\n Select an option:");
        for (Menu option : menu.get("scientist")) {
            System.out.println("\t" + option.getValue() + ") " + option.getLabel());
        }

    }
    public void trackSpaceMenu() {
        state = "track-space";
        System.out.println("Object type:");
        for (Menu option : menu.get("track-space")) {
            System.out.println("\t" + option.getValue() + ") " + option.getLabel());
        }
    }
    public void assessOrbitMenu() {
        state = "assess-orbit";
        System.out.println("username [type]\n Select an option:");
        for (Menu option : menu.get("assess-orbit")) {
            System.out.println("\t" + option.getValue() + ") " + option.getLabel());
        }
    }
    public void adminMenu() {
        state = "admin";
        System.out.println("username [type]\n Select an option:");
        for (Menu option : menu.get("admin")) {
            System.out.println("\t" + option.getValue() + ") " + option.getLabel());
        }
    }
    public void createMenu() {
        state = "create";
        System.out.println("Choose the type of user to create: ");
        for (Menu option : menu.get("create")) {
            System.out.println("\t" + option.getValue() + ") " + option.getLabel());
        }
    }
    public String manage() {
        System.out.println("User Management.\n");
        return promptUser("Enter a username: ");
    }
    public void update(String user) {
        state = "update";
        data = user;
        System.out.println("calamardo: " + user);
        String[] data = user.split(",");
        System.out.println("don cangrejo: " + data.length);
        System.out.println("Modifying user: " + data[0] + "\n" +
                "type: " + data[1]);
        for (Menu option : menu.get("update")) {
            System.out.println("\t" + option.getValue() + ") " + option.getLabel());
        }
    }
    public void typeMenu(String data) {
        state = "type";
        data = data;
        System.out.println("Select new type for [" + data.substring(0, data.indexOf(",")) + "]:");
        for (Menu option : menu.get("type")) {
            System.out.println("\t" + option.getValue() + ") " + option.getLabel());
        }
    }
    public void delete(String user) {
        state = "delete";
        String[] fields = user.split(",");
        System.out.println("User found:\n" +
                "Username: " + fields[0] + " type: " + fields[2] +
                "\n Sure you want to delete? ");
        for (Menu option : menu.get("delete")) {
            System.out.println("\t" + option.getValue() + ") " + option.getLabel());
        }
    }
    public void representativeMenu() {
        state = "representative";
        System.out.println("username [type]\n Select an option:");
        for (Menu option : menu.get("representative")) {
            System.out.println("\t" + option.getValue() + ") " + option.getLabel());
        }
    }
}
