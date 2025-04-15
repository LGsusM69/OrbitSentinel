package edu.utep.group9.view;

import java.util.Scanner;

public class ConsoleUI {
    
    Scanner sc;
    
    public ConsoleUI() {
        sc = new Scanner(System.in);
    }
    
    public void run() {
        String input;
        System.out.println("welcome to Orbit Sentinel");
        while(true) {
            System.out.println("What type of user are you?\n\t" +
                "1) Scientist\n\t 2) Space agency representative\n\t" +
                "3) Policymaker\n\t4) Administrator\n\t e) Exit");
            input = sc.nextLine();
            if(input.equals("e")) break;
        
        }
    }
}
