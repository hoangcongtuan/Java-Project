package com.tuanhc.model;

import java.awt.*;

public class State {
    public int [][] data;
    public int g;
    public int h;

    public State parent;


    public State(int[][] data, int g) {
        this.data = new int[3][3];
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++)
                this.data[i][j] = data[i][j];
        }
        this.g = g;
        invalidate();
    }

    public State() {
        this.data = new int[3][3];
    }

    public int funcH(int [][] state) {
        int res = 0;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if (state[i][j] == 0)
                    continue;
                if (!isCorrectPos(state[i][j], i, j)) {
                    Point p = correctPos(state[i][j]);
                    res += Math.abs(p.x - i) + Math.abs(p.y - j);
                }
            }
        }
        return res;
    }

    public State clone() {
        State res = new State();
        res.h = this.h;
        res.g = this.g;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                res.data[i][j] = this.data[i][j];
            }
        }
        return res;
    }

    private boolean isCorrectPos(int a, int i, int j) {
        return (a == 3 * i + j + 1);
    }

    private Point correctPos(int a) {
        int i, j;
        i = (a - 1) / 3;
        j = a - 1 - 3 * i;
        return new Point(i, j);
    }

    public State moveLeft() {
        int[][] a = left(this.data);
        if (a == null)
            return null;
        State res = new State(a, this.g + 1);
        return res;
    }

    public State moveRight() {
        int[][] a = right(this.data);
        if (a == null)
            return null;
        State res = new State(a, this.g + 1);
        return res;
    }

    public State moveUp() {
        int[][] a = up(this.data);
        if (a == null)
            return null;
        State res = new State(a, this.g + 1);
        return res;
    }

    public State moveDown() {
        int[][] a = down(this.data);
        if (a == null)
            return null;
        State res = new State(a, this.g + 1);
        return res;
    }

    private int[][] left(int[][] state) {
        int[][] res = new int[3][3];
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                res[i][j] = state[i][j];
            }
        }

        Point p = getEmptyPosition(state);
        if (p.y == 2)
            return null;
        res[p.x][p.y] = res[p.x][p.y + 1];
        res[p.x][p.y + 1] = 0;
        return res;
    }

    private int[][] right(int[][] state) {
        int[][] res = new int[3][3];
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                res[i][j] = state[i][j];
            }
        }

        Point p = getEmptyPosition(state);
        if (p.y == 0)
            return null;
        res[p.x][p.y] = res[p.x][p.y - 1];
        res[p.x][p.y - 1] = 0;
        return res;
    }

    private int[][] up(int[][] state) {
        int[][] res = new int[3][3];
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                res[i][j] = state[i][j];
            }
        }

        Point p = getEmptyPosition(state);
        if (p.x == 2)
            return null;
        res[p.x][p.y] = res[p.x + 1][p.y];
        res[p.x + 1][p.y] = 0;
        return res;
    }

    private int[][] down(int[][] state) {
        int[][] res = new int[3][3];
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                res[i][j] = state[i][j];
            }
        }

        Point p = getEmptyPosition(state);
        if (p.x == 0)
            return null;
        res[p.x][p.y] = res[p.x - 1][p.y];
        res[p.x - 1][p.y] = 0;
        return res;
    }

    private Point getEmptyPosition(int state[][]) {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if (state[i][j] == 0)
                    return new Point(i, j);
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String res = "";
        for(int i = 0; i< 3; i++) {
            for (int j = 0; j < 3; j++) {
                res += data[i][j] + ", ";
            }
            res += '\n';
        }
        return res;
    }

    public void println() {
        if(this.parent != null)
            this.parent.println();
        System.out.println(this.toString());
    }

    public boolean equals(State state) {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if (this.data[i][j] != state.data[i][j])
                    return false;
            }
        }
        return true;
    }

    public boolean isGoal() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if (i == 2 && j == 2)
                    continue;
                if (this.data[i][j] != (3 * i + j + 1) % 9)
                    return false;
            }
        }
        return true;
    }

    public void invalidate() {
        this.h = funcH(this.data);
    }

    public int getFx() {
        return this.g + this.h;
    }


}
