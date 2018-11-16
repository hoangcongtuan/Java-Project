package com.tuanhc;

import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
	// write your code here
        new Main();

    }

    int M = 100;
    int SIZE = 100; //kich thuoc quan the
    int numCity = 100; // so thanh pho
    int [][] distance = new int[numCity][numCity];
    Random rand = new Random();
    int [][] nghiem = new int[SIZE][numCity];
    int [] f = new int [SIZE];

    public Main() {
        for(int i = 0; i < numCity; i++) {
            for(int j = 0; j < numCity; j++) {
                distance[i][j] = rand.nextInt((int) 10000);
                distance[j][i] = distance[i][j];
            }
        }

        KhoiTao();
        for (int i = 0; i < 100; i++) {
            DanhGia();
            Print();
            LuaChon();
            LaiGhep();
            DotBien();

        }
    }

    private void KhoiTao() {
        for (int i = 0; i < numCity; i++) {
            nghiem[i] = chinhHop(numCity);
        }
    }

    private int[] chinhHop(int m) {
        int [] res = new int[numCity];
        for(int i = 0; i < numCity; i++) {
            res[i] = i;
        }

        for(int i = 0; i < 100; i++) {
            int x = rand.nextInt(numCity);
            int y = rand.nextInt(numCity);
            int tmp = res[x];
            res[x] = res[y];
            res[y] = tmp;
        }

        return res;
    }

    private void DanhGia() {
        for(int i = 0; i < SIZE; i++) {
            int sum = 0;
            for(int j = 0; j < numCity - 1; j++) {
                sum += distance[nghiem[i][j]]
                [nghiem[i][j + 1]];
            }
            sum += distance[nghiem[i][numCity - 1]]
                    [nghiem[i][0]];
            f[i] = sum;
        }
    }

    private void Print() {
//        int min = 0;
//        for(int i = 0; i < SIZE; i++) {
//            if (f[min] < f[i])
//                min = i;
//        }

        int [] tmp = f.clone();
        Arrays.sort(tmp);
        System.out.println(tmp[0]);

//        for(int i = 0; i < numCity; i++) {
//            System.out.print(nghiem[min][i] + ", ");
//        }
    }

    private void DotBien() {

    }

    boolean isExist(int a, int[] arr) {
        for (int i = 0; i < numCity; i++) {
            if (a == arr[i])
                return true;
        }
        return false;
    }

    private void LaiGhep() {
        for(int i = 0; i < 30 / 100 * SIZE; i++) {
            int cha = rand.nextInt(SIZE);
            int me = rand.nextInt(SIZE);

//            int choice = Math.max(f[cha], f[me]);
            int[] child = new int[numCity];
            int[] child2 = new int[numCity];
            int start = rand.nextInt(numCity);

            laiGhep(child, nghiem[cha], nghiem[me], start);
            laiGhep(child2, nghiem[me], nghiem[cha], start);
            nghiem[cha] = child;
            nghiem[me] = child2;
        }
    }

    public void laiGhep(int[] child, int [] cha, int [] me, int start) {
        for(int i = 0; i < start; i++) {
            child[i] = cha[i];
        }
        for(int i = start; i < numCity; i++) {
            if (isExist(me[i], child))
                child[i] = -1;
            else child[i] = me[i];
        }

        //fill
        for(int i = start; i < numCity; i++) {
            if (child[i] == -1) {
                for(int j = 0; j < numCity; j++)
                    if (!isExist(j, child)) {
                        child[i] = j;
                        break;
                    }
            }
        }
    }

    private void LuaChon() {
        int[] tmp = f.clone();
        Arrays.sort(tmp);
        int nguong = tmp[(int) (SIZE * 80.f / 100)];
        for(int i = 0; i < SIZE; i++) {
            if (f[i] > nguong)
                nghiem[i] = nghiem[rand.nextInt(SIZE)].clone();
        }
    }

}

