package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Hangman hangman = new Hangman();
        hangman.startGame();
        Scanner scanner = new Scanner(System.in);
        String answer;
        do {
            System.out.println("Again? Type Yes or No");
            answer = scanner.nextLine().toLowerCase();
            if (answer.startsWith("n")) {
                break;
            } else if (answer.startsWith("y")) {
                hangman.startGame();
            }
        } while (true);
        System.out.println("Ok, bye!");
    }
}