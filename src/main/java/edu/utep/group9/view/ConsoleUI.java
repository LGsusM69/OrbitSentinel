package edu.utep.group9.view;

import java.util.*;


/**This class is a console user interface.
 * Uses the default System input and output.
 * then it delegates actions to the controller.*/
public class ConsoleUI {

    private Scanner sc;
    private boolean running;

    /** determines the current state of the menu.
     * options are displayed according to this.
     */
    private String state;

    /**All possible menu options are saved here.*/
    private HashMap<String, List<Integer>> options;
    
    public ConsoleUI() {
        options = new HashMap<>();
        options.put("main", new ArrayList<>(Arrays.asList(1, 0)));
        options.put("scientist", new ArrayList<>(Arrays.asList(1, 2, 0)));
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
            processInput(input);
        }
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

    /*Prompts user for input and validates it
    before proceding.
     */
    public int getInput() {
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
            }
        }
        return input;
    }

    /*decides the next action based on state and input*/
    public void processInput(int input) {
        switch(state + input) {
            case "main1":
                System.out.println("authenticating user[scientist]...");
                //authenticate user
                System.out.println("Logged in as scientist");
                scientistMenu();
                break;
            case "main0":
                System.out.println("Exiting...");

                running = false;
            case "scientist1":
                break;
        }
    }
}
