package com.tuanhc;

import java.util.Random;

public class K_Mean {

    public static void main(String[] args) {
        int [][] x = new int[][] {
                {3, 1},
                {0, 3},
                {1, 4},
                {4, 1}
        };

        K_Mean k_mean = new K_Mean(x, 2);
        for(int i = 0; i < x.length; i++) {
            System.out.println(k_mean.id[i]);
        }
    }

    int [][] data;
    int k;
    int id[];
    int [][] c;
    Random random = new Random();

    public K_Mean(int[][] data, int k) {
        this.data = data;
        this.k = k;
        this.id = new int[data.length];
        this.c = new int[k][data[0].length];

        //buoc 1
        for(int i = 0; i < data.length; i++) {
            id[i] = random.nextInt(k);
        }

        //buoc 2 - 4
        while (true) {
            //buoc 2
            int [] count = new int [k];

            for(int i = 0; i < k; i++) {
                count[i] = 0;
                for(int j = 0; j < c[i].length; j++) {
                    c[i][j] = 0;
                }
            }

            for(int i = 0; i < data.length; i++) {
                count[id[i]]++;
                for(int j = 0; j < data[i].length; j++) {
                    c[id[i]][j] += data[i][j];
                }
            }

            for(int i = 0; i < k; i++) {
                if (count[i] != 0) {
                    for(int j = 0; j < c[i].length; j++) {
                        c[i][j] /= count[i];
                    }
                } else {
                    int x = random.nextInt(data.length);
                    for(int j = 0; j < c[i].length; j++) {
                        c[i][j] = data[x][j];
                    }
                }
            }

            //buoc 3
            boolean thayDoi = false;
            for(int i = 0; i < data.length; i++) {
                int newId = chiaLai(data[i]);
                if (id[i] != newId)
                    thayDoi = true;
                id[i] = newId;
            }

            //buoc 4
            if (!thayDoi)
                break;



        }

    }

    private int chiaLai(int[] x) {
        int id = 0;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < k; i++) {
            int d = distance(x, c[i]);
            if (min > d) {
                min = d;
                id = i;
            }

        }
        return id;
    }

    private int distance(int[] x, int[] y) {
        int dis = 0;
        for(int i = 0; i < x.length; i++) {
            dis += (x[i] - y[i]) * (x[i] - y[i]);
        }
        return dis;
    }
}
