package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    private List<String> words;
    private String word;
    private String hiddenWord = "";

    public void startGame() {
        loadListOfWords("src/main/resources/words.csv");
        randomlySelectPassword();
        prepareGameplay();
        play();
    }

    private void loadListOfWords(String fileName) {
        words = new ArrayList<>();
        BufferedReader bufferedReader;

        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));
            String line = bufferedReader.readLine();
            while (line != null) {
                line = line.replaceAll("[^[a-zA-Z]]", " ");
                String[] splitWords = line.split("[\\s]+");
                for (String w : splitWords) {
                    w = w.toLowerCase();
                    if (w.length() > 8 && !(words.contains(w))) {
                        words.add(w);
                    }
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void randomlySelectPassword() {
        if (!words.isEmpty()) {
            Random random = new Random();
            word = words.get(random.nextInt(words.size()));
        } else {
            throw new IllegalArgumentException("List of words is incorrect.");
        }
    }

    private void prepareGameplay() {
        StringBuilder stringBuilder = new StringBuilder("");
        for (int i = 0; i < word.length(); i++) {
            stringBuilder.append("-");

        }
        hiddenWord = stringBuilder.toString();
    }

    private void play() {
        Scanner scanner = new Scanner(System.in);
        int attempts = 10;
        System.out.printf("This hidden word has %s letters \n", word.length());
        while (attempts > 0 && hiddenWord.contains("-")) {
            System.out.printf("Attempts left: %s \n", attempts);
            System.out.println(hiddenWord);
            System.out.println("Type the letter and press enter.");
            String input = scanner.next().trim().substring(0, 1);
            char letter = input.charAt(0);
            if (Character.isLetter(letter) && (word.contains(input))) {
                System.out.println("The letter is correct.");
                placeCorrectLetter(letter);
            } else {
                attempts--;
                System.out.println("The letter is incorrect.");
            }
        }
        if (attempts == 0) {
            System.out.println("Game over.");
        } else {
            System.out.println("Congratulations.");
        }
        System.out.println("The hidden word was: " + word);
    }
    private void placeCorrectLetter(char letter) {
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                hiddenWord = hiddenWord.substring(0, i) + letter + hiddenWord.substring(i + 1);
            }
        }
    }
}
