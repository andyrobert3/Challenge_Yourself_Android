package com.example.aaryam123.everyday_challenge_final;

/**
 * Created by alexa on 1/27/2018.
 */

public class Levels {
    public final static int TOTAL_LEVELS = 9;
    int currLevel;
    int mNumChallenges;
     // personal max level
    // set number of challenges for levelChallenges array
    int levelChallenges[] = {4,5,6,7,8,9,10,11,12};
    int doneActivites[] ={0, 0, 0, 0, 0, 0 ,0 ,0 ,0};

    Levels (int level, int numChallenges) {
        currLevel = level;
        mNumChallenges = numChallenges;
    }

    // call when one is done with challenge at a level (1, 2, ... )
    public void doneChallenge() {
        if (currLevel > TOTAL_LEVELS)
            throw new IllegalArgumentException("Level accessed is too high");

        if (doneActivites[currLevel-1] >= levelChallenges[currLevel-1]) {
            currLevel++;
        }
        doneActivites[(currLevel-1)%levelChallenges.length]++;
        mNumChallenges++;
    }


    public int getCurrLevel() {
        return currLevel;
    }

    public int getNumChallenges() {
        return mNumChallenges;
    }


}
