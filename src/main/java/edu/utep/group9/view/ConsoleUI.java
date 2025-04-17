package edu.utep.group9.view;

import java.util.*;


/**This class is a console user interface.
 * Uses the default System input and output.
 * then it delegates actions to the controller.*/
public class ConsoleUI {
    
    private Scanner sc;
    private boolean running;
    private String state;
    private HashMap<String, List<Integer>> options;
    
    public ConsoleUI() {
        options = new HashMap<>();
        options.put("main", new ArrayList<>(Arrays.asList(1, 0)));
        running = false;
        sc = new Scanner(System.in);
    }
    
    public void run() {
        running = true;
        int input;
        System.out.println("welcome to Orbit Sentinel.");
        mainMenu();
        while(running) {
            input = getInput();
            processInput();
        }
    }
    public void mainMenu() {
        state = "main";
        System.out.println("Main menu:\n\t" +
                "1) Login\n\t" +
                "0) Exit");
    }
    public int getInput() {
        int input = sc.nextInt();
        while(!options.get(state).contains(input)) {
            System.out.println("Invalid option.");
            for(int option : options.get("main")) {
            
            }
        }
        return input;
    }
    public void processInput() {}
}
