package edu.utep.group9.view;

import edu.utep.group9.controllers.UIController;
import edu.utep.group9.models.Menu;

import java.util.*;


/**This class is a console user interface.
 * Uses the default System input and output.
 * then it delegates actions to the controller.*/
public class ConsoleUI {
    UIController uiControl;

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
    public void stop() {
        running = false;
    }

    /*Prompts user for input and validates it
    before proceding.
     */
    private int getInput() {
        int input = -1;
        try {
            input = sc.nextInt();
        } catch (InputMismatchException e) {
            sc.nextLine();
        }
        return input;
    }

    private boolean isValidInput(int input) {
        List<Menu> options = menu.get(state);
        for (Menu option : options) {
            if (option.getValue() == input) {
                return true;
            }
        }
        System.out.println("Invalid input.");
        switch(state) {
            case "main": mainMenu(); break;
            case "scientist": scientistMenu(); break;
            case "track-space": trackSpaceMenu(); break;
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
}
