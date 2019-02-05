package com.assignment.todo;

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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText todo;
    private Button add;
    private RecyclerView rv;
    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        todo = findViewById(R.id.editText);
        add = findViewById(R.id.button);
        rv = findViewById(R.id.rv);
        list = new ArrayList<>();

        rv.setLayoutManager(new LinearLayoutManager(this));
        final ToDoAdapter adapter = new ToDoAdapter(this, list);
        rv.setAdapter(adapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (todo.getText() != null && todo.getText().toString().length() != 0){
                    list.add(todo.getText().toString());
                    adapter.notifyItemInserted(list.size()-1);
                    InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(todo.getWindowToken(), 0);
                    todo.setText("");
                }
                else {
                    Toast.makeText(MainActivity.this, "Type someting first.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
