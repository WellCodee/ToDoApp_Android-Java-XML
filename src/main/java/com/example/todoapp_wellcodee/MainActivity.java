package com.example.todoapp_wellcodee;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements
        ToDo_RecyclerAdapter.DeleteListener ,
        ToDo_RecyclerAdapter.SwitchListener{

    RecyclerView recyclerView;
    FloatingActionButton addbtn;
    ToDoService modelObject;
    ActivityResultLauncher<Intent> myLauncher;
    ToDo_RecyclerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        modelObject =  ((MyApp)getApplication()).myModel;
        recyclerView = findViewById(R.id.listoftodo);
        adapter = new ToDo_RecyclerAdapter(modelObject.list,this);
        adapter.switchListener = this;
        adapter.deleteListener = this;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        myLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result->{
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Intent intent = result.getData();
                        ToDo newTask = (ToDo)intent.getSerializableExtra("newTask");
                        modelObject.addNewTask(newTask);
                        recyclerView.post(()->{
                            adapter.notifyDataSetChanged();
                        });
                    }
        });
        addbtn = findViewById(R.id.add_newtask_btn);
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ToDoActivity.class);
                myLauncher.launch(intent);
            }
        });
    }

    @Override
    public void deleteClicked(int taskIndex) {
        modelObject.deleteOneTask(taskIndex);
        recyclerView.post(()->{
            adapter.notifyDataSetChanged();
        });
    }

    @Override
    public void urgentSwitchChanged(int taskIndex, Boolean vale) {
        modelObject.updateTask(taskIndex,vale);
        recyclerView.post(()->{
            adapter.notifyDataSetChanged();
        });
    }
}