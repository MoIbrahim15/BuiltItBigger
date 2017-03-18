package com.joker;

import java.util.ArrayList;
import java.util.Random;

public class Joker {
    private ArrayList<String> jokes = new ArrayList<>();
    private Random random;

    public Joker() {
        jokes.add("Programmer is a machine that turns coffee into code.");
        jokes.add("Algorithm word is used by programmers when they do not  want to explain what they did.");
        jokes.add("Why do Java developers wear glasses? Because they can't C#");
        jokes.add("Real programmers count from 0.");
        random = new Random();
    }

    public ArrayList<String> getJokes() {
        return jokes;
    }

    public String getRandomJoke() {
        return jokes.get(random.nextInt(jokes.size()));
    }
}
