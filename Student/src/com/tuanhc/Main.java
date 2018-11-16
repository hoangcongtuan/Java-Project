package com.tuanhc;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	// write your code here
        new Main();
    }

    public Main() {
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("abc"));
        Student newStudent = students.get(0).clone();
        newStudent.name = "cde";
        System.out.println(students.get(0).name);
        System.out.println(newStudent.name);

    }

    class Student {
        String name;

        public Student() {

        }

        public Student(String name) {
            this.name = name;
        }


        public Student clone() {
            Student newInstance = new Student();
            newInstance.name = this.name;
            return newInstance;

        }
    }
}
