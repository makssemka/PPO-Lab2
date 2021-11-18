package com.example.university;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class EditActivity extends AppCompatActivity {

    ArrayList<Student> students = new ArrayList<>();
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Bundle listStudents = getIntent().getExtras();

        if (listStudents != null)
        {
            position = listStudents.getInt("position");
            students = (ArrayList<Student>) listStudents.get("students");
        }
    }
    public void onClick(View v) {

        EditText nameText = findViewById(R.id.editName);
        EditText mark1Text = findViewById(R.id.editRusMark);
        EditText mark2Text = findViewById(R.id.editMathMark);
        EditText mark3Text = findViewById(R.id.editPhyMark);

        Student student = new Student(nameText.getText().toString(),
                mark1Text.getText().toString(),
                mark2Text.getText().toString(),
                mark3Text.getText().toString());

        students.set(position, student);

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("students", students);
        startActivity(intent);
    }
}