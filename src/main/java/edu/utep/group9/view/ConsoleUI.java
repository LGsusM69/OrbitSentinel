package edu.utep.group9.view;

import edu.utep.group9.controllers.ScientistController;
import edu.utep.group9.controllers.UIController;

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
    private HashMap<String, List<Integer>> options;
    
    public ConsoleUI(UIController uiControl) {
        this.uiControl = uiControl;
        options = new HashMap<>();
        options.put("main", new ArrayList<>(Arrays.asList(1, 0)));
        options.put("scientist", new ArrayList<>(Arrays.asList(1, 2, 0)));
        options.put("track-space", new ArrayList<>(Arrays.asList(1, 2, 3, 4, 0)));
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
            uiControl.handleInput(state, input);
        }
    }
    public void stop() {
        running = false;
    }

    /*The following methods display the
    appropriate menu options in the console
     */
    public void mainMenu() {
        state = "main";
        System.out.println("Main menu:\n\t" +
                "1) Login\n\t" +
                "0) Exit");
    }
    public void scientistMenu() {
        state = "scientist";
        System.out.println("username [type]\n Select an option:\n\t" +
                "1) Track objects in space\n\t" +
                "2) Assess Orbit Status\n\t" +
                "0) Back");
    }
    public void trackSpaceMenu() {
        state = "track-space";
        System.out.println("Object type:\n\t" +
                "1) Rocket body\n\t" +
                "2) Debris\n\t" +
                "3) Payload\n\t" +
                "4) Unknown\n\t" +
                "0) Back");
    }

    /*Prompts user for input and validates it
    before proceding.
     */
    private int getInput() {
        int input = -1;

        while(!options.get(state).contains(input)) {
            try {
                input = sc.nextInt();
            } catch (InputMismatchException e) {
                sc.nextLine();
            }
            if(options.get(state).contains(input)) break;
            System.out.println("Invalid input.");
            switch(state) {
                case "main": mainMenu(); break;
                case "scientist": scientistMenu(); break;
                case "track-space": trackSpaceMenu(); break;
            }
        }
        return input;
    }
}
