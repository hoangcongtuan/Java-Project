package com.tuanhc;

import java.lang.ref.PhantomReference;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        new Main();
    }

    public Main() {
        Scanner scanner = new Scanner(System.in);
        int player = 1;
        int turn = 0;
        int d = 5;
        State s = new State();

        while(true) {
            if (turn % 2 + 1 == player) {
                //User play
                State child = null;
                while (child == null) {
                    System.out.println("Please input your coordinate!@!@#$%^&*");
                    int x = scanner.nextInt();
                    int y = scanner.nextInt();
                    child = new Operator(x, y).move(s);
                }
                s = child;
                if (win(s)) {
                    System.out.println("Player won!");
                    break;

                }
            }
            else {
                //Bot play
                System.out.println("AI Turn");
                int min = Integer.MAX_VALUE;
                for(int i= 0; i < s.N; i++)
                    for(int j = 0 ; j < s.N; j++){
                    State child = new Operator(i, j).move(s);
                    if (child == null)
                        continue;
                    int tmp = minimax((child, 5, true);
                    System.out.println(i + ", " + j + ": " + tmp);
                    if (min > tmp) {
                        min = tmp;
                        minChild = child;
                    }
//                    minimax(s, 5);
                }
                s = child;
                if (win(s)) {
                    System.out.println("AI won!");
                    break;
                }
//                s = minimax(s, 5);
            }
            s.print();
            if (isEndNode(s)) {
                System.out.println("AI Draw");
                break;
            }
            turn++;
        }
    }

    private boolean win(State s) {


    }

    private int minimax(State s, int d, boolean mp) {
        return alphaBeta(s, d, Integer.MIN_VALUE, Integer.MAX_VALUE, mp);
    }

    private int alphaBeta(State s, int d, int a, int b, boolean mp) {
        if (isEndNode(s) || d == 0) {
            return value(s);
        }
        State minState = null;

lb:        for(int i = 0; i < s.N; i++) {
            for(int j = 0; j < s.N; j++) {
                State child = new Operator(i, j).move(s);
                if (child == null)
                    continue;
//                State tmp = alphaBeta(child, d - 1, -b, -a);
//
//                if (tmp  == null)
//                    continue;
//                if (a < tmp.value) {
//                    a = -tmp.value;
//                    minState = child;
//                    minState.value = a;
//                }
//
//                if (a >= b) {
//                    i = s.N;
//                    j = s.N;
//                }

                int tmp = alphaBeta(child, d - 1, a, b, !mp);
                if (mp) {
                    if (a < tmp )
                        a = tmp;
                }
                else {
                    if (b > tmp)
                        b = tmp;
                }
                if (a >= b)
                    break lb;
            }
            if (mp)
                return a;
            return b;
        }
        s.value = a;
        Long.MAX_VALUE
        return s;
    }

    private int value(State s) {
//        s.value = 1;
        if (win(s)) {
            if (myTurn(s))
                return 1;
            else return -1;
        }
        return 0;
    }

    private boolean myTurn(State s) {
        int count = 0;
        for(int i = 0; i < s.N; i++) {
            for(int j = 0; j <= s.N; j++) {
                if (s.a[i][j] == 0)
                    count++;
            }
        }
        if (count % 2 == 0)
            return true;
        return false;
    }

    private boolean isEndNode(State s) {
        for (int i = 0; i < s.N; i++) {
            if (s.a[i][0] != 0 && s.a[i][0] == s.a[i][1] && s.a[i][1] == s.a[i][2])
                return true;

            if (s.a[0][i] != 0 && s.a[0][i] == s.a[i][1] && s.a[1][i] == s.a[2][i])
                return true;
        }

        if (s.a[0][0] != 0 && s.a[0][0] == s.a[1][1] && s.a[1][1] == s.a[2][2])
            return true;

        if (s.a[0][2] != 0 && s.a[0][2] == s.a[1][1] && s.a[1][1] == s.a[2][2])
            return true;

        for(int i = 0; i< s.N; i++) {
            // TODO: 9/13/18

        }

        return false;
    }
}
