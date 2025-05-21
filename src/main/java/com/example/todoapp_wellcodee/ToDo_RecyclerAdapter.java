package com.example.todoapp_wellcodee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ToDo_RecyclerAdapter
        extends RecyclerView.Adapter<ToDo_RecyclerAdapter.ToDoViewHolder> {
    Context appContext;
    ArrayList<ToDo> listOfToDos;
    // list of tasks

    interface SwitchListener {
        void urgentSwitchChanged(int taskIndex, Boolean vale);
    }
    interface DeleteListener {
        void deleteClicked(int taskIndex);
    }

    SwitchListener switchListener;
    DeleteListener deleteListener;

    ToDo_RecyclerAdapter(ArrayList<ToDo> list, Context context){
        listOfToDos = list;
        appContext= context;
    }

    class ToDoViewHolder extends RecyclerView.ViewHolder{

        TextView taskText;
        TextView dateText;
        ImageButton delete;
        Switch todoSwitch;

        public ToDoViewHolder(@NonNull View itemView) {
            super(itemView);
            taskText = itemView.findViewById(R.id.todotask);
            dateText = itemView.findViewById(R.id.datetext);
            delete = itemView.findViewById(R.id.delete_btn);
            todoSwitch = itemView.findViewById(R.id.switch_btn);

        }
    }

    @NonNull
    @Override
    public ToDoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(appContext).inflate(R.layout.todo_row,parent,false);
        return new ToDoViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull ToDoViewHolder holder, int position) {
        holder.taskText.setText(listOfToDos.get(position).task);
        holder.dateText.setText(listOfToDos.get(position).date);
        holder.todoSwitch.setChecked(listOfToDos.get(position).isUrget);
        if (listOfToDos.get(position).isUrget) {
            holder.itemView.setBackgroundResource(R.color.red);
        } else {
            holder.itemView.setBackgroundResource(R.color.green);
        }
        holder.todoSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                switchListener.urgentSwitchChanged(holder.getAdapterPosition(),b);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteListener.deleteClicked(holder.getAdapterPosition());
            }
        });


    }

    @Override
    public int getItemCount() {
        return listOfToDos.size();
    }

}
