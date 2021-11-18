package com.example.university;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ArrayList<Student> students = new ArrayList<>();
    ListView studentsList;
    int requestCode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle listStudents = getIntent().getExtras();

        if (listStudents != null)
        {
            students = (ArrayList<Student>) listStudents.get("students");
            Update();
        }

        ListView lvMain = (ListView) findViewById(R.id.studentsList);
        registerForContextMenu(lvMain);
    }

    private void setInitialData(Uri uri){
        try {
            BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(this.getContentResolver().openInputStream(uri)));
            String line = bufferedReader.readLine();
            while (line != null) {
                Student student = new Student(line);
                students.add(student);
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (this.requestCode == requestCode && resultCode == Activity.RESULT_OK)
        {
            if (data == null)
            {
                return;
            }

            Uri uri = data.getData();
            setInitialData(uri);

            Update();
        }
    }

    public void Update() {
        studentsList = findViewById(R.id.studentsList);

        StudentAdapter studentAdapter = new StudentAdapter(this, R.layout.item, students);

        studentsList.setAdapter(studentAdapter);
    }

    public void

    openFileChooser(View view)
    {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.edit:
                editStudent(info.position); // метод, выполняющий действие при редактировании пункта меню
                return true;
            case R.id.delete:
                deleteItem(info.position); //метод, выполняющий действие при удалении пункта меню
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void deleteItem(int position) {
        students.remove(position);
        Update();
    }

    private void editStudent(int position) {
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra("position", position);
        intent.putExtra("students", students);
        startActivity(intent);
    }

    public void onClick(View v) {
        Intent intent = new Intent(this, AddActivity.class);
        intent.putExtra("students", students);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void Sort(View v)
    {
        ArrayList<Student> bestStudents = new ArrayList<>();
        float rating = 0;
        int personalRating = 0;
        for (Student student: students) {
            personalRating = student.sumOfMarks() ;
            rating += personalRating;
        }

        rating /= students.size();

        Toast toast = Toast.makeText(getApplicationContext(),
                "Средний балл по университету: " + rating/3, Toast.LENGTH_SHORT);
        toast.show();

        for (Student student: students) {
            personalRating = student.sumOfMarks();
            if (personalRating >= rating)
            {
                bestStudents.add(student);
            }
        }

        bestStudents.sort(Student::compareTo);

        studentsList = findViewById(R.id.studentsList);

        StudentAdapter studentAdapter = new StudentAdapter(this, R.layout.item, bestStudents);

        studentsList.setAdapter(studentAdapter);
    }
}