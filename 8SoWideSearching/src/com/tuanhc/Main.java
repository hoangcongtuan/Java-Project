package com.tuanhc;

import com.tuanhc.model.State;

import java.util.HashMap;
import java.util.Random;

public class Main {
    int start[][] = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 0}
    };
    Core core;

    public static void main(String[] args) {
	// write your code here
        new Main();
    }

    private Main() {
        int[][] a = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
        };

        Random random = new Random();
        State start = new State(a, 0);
        for(int i = 0; i < 100000; i++) {
            int randomNum = random.nextInt(4);
            State nextState = null;
            switch (randomNum) {
                case 0:
                    nextState = start.moveUp();
                    break;
                case 1:
                    nextState = start.moveLeft();
                    break;
                case 2:
                    nextState = start.moveDown();
                    break;
                case 3:
                    nextState = start.moveRight();
                    break;
            }

            if (nextState != null)
                start = nextState.clone();
        }

        System.out.println(start.toString());
        core = new Core();
        System.out.println("Start solve");
        core.start(start);
    }
}
