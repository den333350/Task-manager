package ua.hillel;

import java.util.ArrayList;
import java.util.Scanner;

class Task{
    private String taskInfo;
    private boolean completed;

    Task(String taskInfo){
        this.taskInfo = taskInfo;
    }

    public void print(int i){
        String maked;
        if(completed){
            maked = "Perform";
        }else {
            maked = "Not perform";
        }
        System.out.println(i+". "+this.taskInfo+"\t\t"+ maked);
    }

    public void makeCompleted(){
        completed = true;
    }
    public void makeUncompleted(){
        completed = false;
    }
}

class Menu{
    ArrayList<Task> uncompletedTask = new ArrayList<>();
    ArrayList<Task> completedTask = new ArrayList<>();
    Scanner s = new Scanner(System.in);

    public void showTask(){
        System.out.println("List of uncompleted task:");
        if(uncompletedTask.isEmpty()) System.out.println("Empty");
        else {
            for (int i = 0; i < uncompletedTask.size() ; i++) {
                uncompletedTask.get(i).print(i+1);
            }
        }
    }


    public void start(){
        showTask();
        menu :while(true){
            System.out.println("1-Create new task\t 2-Make task completed\t 3-Show completed task\t 4-Show all task\t 0-Exit");
            switch (s.nextLine()){
                case("1"): //Create new task
                    System.out.print("Write description: ");
                    String task = s.nextLine();
                    Task newTask = new Task(task);
                    uncompletedTask.add(newTask);
                    showTask();
                    break;


                case("2"): //Make task completed
                    System.out.print("Choose task: ");
                    int choose = s.nextInt()-1;
                    uncompletedTask.get(choose).makeCompleted();
                    completedTask.add(uncompletedTask.get(choose));
                    uncompletedTask.remove(choose);
                    showTask();
                    break;


                case("3"): //Show completed task
                    while (true) {
                        System.out.println("List of uncompleted task:");
                        if (completedTask.isEmpty()) System.out.println("Empty");
                        else {
                            for (int i = 0; i < completedTask.size(); i++) {
                                completedTask.get(i).print(i + 1);
                            }
                        }
                        System.out.println("1-Make task uncompleted\t0-Back");
                        switch (s.nextLine()) {
                            case ("1"):
                                System.out.print("Choose task: ");
                                int choose1 = s.nextInt() - 1;
                                completedTask.get(choose1).makeUncompleted();
                                uncompletedTask.add(completedTask.get(choose1));
                                completedTask.remove(choose1);
                                break;
                            case ("0"):
                                continue menu;
                        }
                    }

                case("4"): //Show all task

                    for (int i = 0; i < completedTask.size() ; i++) {
                        completedTask.get(i).print(i+1);
                    }
                    showTask();
                    break;

                case("0"): //Exit
                    System.exit(0);

                    break;
            }

        }

    }
}



public class Main {

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.start();
    }
}