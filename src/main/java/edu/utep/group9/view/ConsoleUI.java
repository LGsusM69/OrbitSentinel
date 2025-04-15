package edu.utep.group9.view;

import java.util.Scanner;

public class ConsoleUI {
    
    private Scanner sc;
    private boolean running;
    
    public ConsoleUI() {
        running = false;
        sc = new Scanner(System.in);
    }
    
    public void run() {
        running = true;
        int input;
        System.out.println("welcome to Orbit Sentinel.");
        mainMenu();
        while(running) {
            getInput();
            processInput();
        }
    }
    public void mainMenu() {
        System.out.println("Main menu:\n\t" +
                "1) Login\n\t" +
                "2) Exit");
    }
    public void getInput() {}
    public void processInput() {}
}
