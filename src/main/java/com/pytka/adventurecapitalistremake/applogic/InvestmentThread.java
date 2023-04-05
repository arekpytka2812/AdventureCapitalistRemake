package com.pytka.adventurecapitalistremake.applogic;


import com.pytka.adventurecapitalistremake.utils.Task;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.Queue;

public class InvestmentThread implements Runnable{

    private final Investment investment;

    private final Queue<Task> taskQueue;

    public InvestmentThread(Investment investment){
        this.investment = investment;
        this.taskQueue = new LinkedList<>();
    }

    public void addTask(Task task){

        synchronized (taskQueue){
            taskQueue.add(task);
        }
    }

    @Override
    public void run(){

        while(true){

            synchronized (taskQueue){

                if(taskQueue.isEmpty()){
                    continue;
                }

                Task top = taskQueue.poll();

                try {
                    top.getMethod().invoke(investment, top.getArgs());
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
