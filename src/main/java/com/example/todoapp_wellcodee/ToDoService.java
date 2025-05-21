package com.example.todoapp_wellcodee;

import java.util.ArrayList;

public class ToDoService {
   ArrayList list = new ArrayList<>(0);

   ToDoService(){

   }

   public void addNewTask(ToDo task){
       list.add(task);
       //cloud storage
       // database
   }
   public void updateTask(int position, Boolean isUrgent){
       ((ToDo) list.get(position)).isUrget = isUrgent;

   }

   public void deleteOneTask(int position){
       list.remove(position);
   }
}
