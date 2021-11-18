package com.example.university;

import java.io.Serializable;

public class Student implements Serializable, Comparable<Student> {

    private String name;
    private String rusMark;
    private String mathMark;
    private String phyMark;

    public Student(String name, String rusMark, String mathMark, String phyMark)
    {
        this.name = name;
        this.rusMark = rusMark;
        this.mathMark = mathMark;
        this.phyMark = phyMark;
    }

    public Student(String str)
    {
        String[] data = str.split(", ");

        name = data[0];
        rusMark = data[1];
        mathMark = data[2];
        phyMark = data[3];
    }

    public String getName() {
        return this.name;
    }

    public String getMarks(String object) {
        if (object == "rus")
        {
            return "Русский язык: " + rusMark;
        }
        else if (object == "math")
        {
            return "Математика: " + mathMark;
        }
        else if (object == "phy")
        {
            return "Физика: " + phyMark;
        }
        else
        {
            return null;
        }
    }

    public int sumOfMarks() {
        return Integer.parseInt(this.rusMark) + Integer.parseInt(this.mathMark) + Integer.parseInt(this.phyMark);
    }
    @Override
    public int compareTo(Student another) {
        if (this.sumOfMarks() > another.sumOfMarks())
            return -1;
        if (this.sumOfMarks() < another.sumOfMarks())
            return 1;
        return 0;
    }
}
