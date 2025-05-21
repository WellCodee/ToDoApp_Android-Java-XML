package com.example.todoapp_wellcodee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class ToDoActivity  extends AppCompatActivity {

    Button cancelBut;
    Button saveBut;
    DatePicker datePicker;
    SwitchCompat urgentSwitch;
    EditText taskText;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_task);

        cancelBut = findViewById(R.id.cancelButton);
        saveBut = findViewById(R.id.saveButton);
        datePicker = findViewById(R.id.taskDatePicker);
        taskText = findViewById(R.id.taskText);
        urgentSwitch = findViewById(R.id.isUrgentSwitch);

        cancelBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        saveBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!taskText.getText().toString().isEmpty()){
                    String taskDate = datePicker.getYear() + "/" + datePicker.getMonth() +"/" + datePicker.getDayOfMonth();
                    ToDo newTask = new ToDo(taskText.getText().toString(), taskDate, urgentSwitch.isChecked());
                    // newTask should be sent to Main Activity
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("newTask", newTask);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
            }
        });
    }
}
