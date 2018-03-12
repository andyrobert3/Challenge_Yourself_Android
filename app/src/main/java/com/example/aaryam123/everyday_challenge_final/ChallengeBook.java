package com.example.aaryam123.everyday_challenge_final;

import java.util.Random;
import java.util.Stack;

/**
 * Created by alexa on 1/27/2018.
 */

public class ChallengeBook {
    private String[] challenges = {
            "Eat less sugar",
            "Choose to walk instead of driving",
            "Call mom or dad, they miss you",
            "Catch up with a long lost old friend",
            "Start learning a new language",
            "Run for 30 minutes around the neighbourhood",
            "Learn a new skill such as programming or design",
            "Ask that girl/guy out that you like",
            "Travel to somewhere random that is not known to you",
            "Open doors for strangers and smile",
            "Cut back TV and social media (Yes including this phone)",
            "Eat a bowl of salad",
            "Only drink water for the day",
            "Start reading a book",
            "Do 20 Push-ups",
            "Try a vegan diet",
            "Get 7 hours of sleep tonight",
            "Say 'Hi!' to 5 strangers",
            "Make your bed"
    };
    private int challengesDone;

    ChallengeBook() {
        challengesDone = 0;
    }

    public String getRandomChallenge() {
        challengesDone++;
        Random randomGenerator = new Random();
        int randomNum = randomGenerator.nextInt(challenges.length);
        return challenges[randomNum];
    }

    public int getNumberChallenge() {
        return challengesDone;
    }

}