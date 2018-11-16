package com.tuanhc;

public class State {
    int N = 3;
    int a[][] = new int[N][N];
    int value;

    void print() {
        for (int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if (a[i][j] == 0)
                    System.out.print(" _ ");

                if (a[i][j] == 1)
                    System.out.print(" O ");

                if (a[i][j] == 2)
                    System.out.print(" X ");
            }
            System.out.println();
        }

        System.out.println("------------------");
    }

    public State clone() {
        State tmp = new State();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                tmp.a[i][j] = this.a[i][j];
            }
        }
        tmp.value = this.value;
        return tmp;
    }
}
