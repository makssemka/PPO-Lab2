package com.example.university;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class AddActivity extends AppCompatActivity {

    ArrayList<Student> students = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Bundle listStudents = getIntent().getExtras();

        if (listStudents != null)
        {
            students = (ArrayList<Student>) listStudents.get("students");
        }
    }
    public void onClick(View v) {

        EditText nameText = findViewById(R.id.addName);
        EditText mark1Text = findViewById(R.id.addRusMark);
        EditText mark2Text = findViewById(R.id.addMathMark);
        EditText mark3Text = findViewById(R.id.addPhyMark);

        Student student = new Student(nameText.getText().toString(),
                mark1Text.getText().toString(),
                mark2Text.getText().toString(),
                mark3Text.getText().toString());

        students.add(student);

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("students", students);
        startActivity(intent);
    }
}