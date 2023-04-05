package com.pytka.adventurecapitalistremake.applogic;


import com.pytka.adventurecapitalistremake.utils.Task;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.SynchronousQueue;

public class InvestmentThread implements Runnable{

    private Investment investment;

    Queue<Task> taskQueue;

    public InvestmentThread(Investment investment){
        this.investment = investment;
        this.taskQueue = new LinkedList<>();
    }

    public void addTask(Task task){
        taskQueue.add(task);
    }

    @Override
    public void run(){

        while(true){

            //TODO: check and execute methods from taskQueue

            if(taskQueue.isEmpty()){
                continue;
            }

            var top = taskQueue.poll();

            try {
                System.out.println(top.getMethod().invoke(investment, top.getArgs()));
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

}
