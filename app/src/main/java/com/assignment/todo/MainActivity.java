package com.assignment.todo;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText todo;
    private Button add;
    private RecyclerView rv;
    private ArrayList<Task> taskList;
    private DatabaseHelper db;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        todo = findViewById(R.id.editText);
        add = findViewById(R.id.button);
        rv = findViewById(R.id.rv);
        db = new DatabaseHelper(this);
        taskList = db.getAllData();

        rv.setLayoutManager(new LinearLayoutManager(this));
        final ToDoAdapter adapter = new ToDoAdapter(this, taskList);
        rv.setAdapter(adapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (todo.getText() != null && todo.getText().toString().trim().length() != 0){
                    Task newTask = new Task(todo.getText().toString(), getDate());
                    db.addTask(newTask);
                    taskList.add(newTask);
                    adapter.notifyDataSetChanged();
                    InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(todo.getWindowToken(), 0);
                    todo.setText("");
                }
                else {
                    Toast.makeText(MainActivity.this, "No task to add", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private String getDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd/MM/yy hh:mm a", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}
