
import java.util.ArrayList;
import java.util.Scanner;

public class toDoList {
    private ArrayList<Task> tasks;
    private int size = 0;

    public toDoList() {
        this.tasks = new ArrayList<Task>();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        this.size++;
    }

    public void deleteTask(int index) {
        this.tasks.remove(index);
        this.size--;
    }

    public void markTaskComplete(int index) {
        this.tasks.get(index).setComplete(true);
    }

    public void displayTasks() {
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out
                    .println((i + 1) + ". " + this.tasks.get(i).getDescription()
                            + " (" + this.tasks.get(i).status() + ")");
        }
    }

    public int size() {
        return this.size;
    }

    public void editTask(int index, String newDescription) {
        this.tasks.get(index).setDescription(newDescription);
    }

    public void moveTaskUp(int index) {
        if (index > 1) {
            Task temp = this.tasks.get(index - 1);
            this.tasks.set(index - 1, this.tasks.get(index - 2));
            this.tasks.set(index - 2, temp);
        } else {
            System.err.println("Can't move higher!");
        }
    }

    public void moveTaskDown(int index) {
        if (index < this.tasks.size()) {
            Task temp = this.tasks.get(index - 1);
            this.tasks.set(index - 1, this.tasks.get(index));
            this.tasks.set(index, temp);
        } else {
            System.err.println("Can't move lower!");
        }
    }

}

class Task {
    private String description;
    private boolean isComplete;

    public Task(String description) {
        this.description = description;
        this.isComplete = false;
    }

    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    public String getDescription() {
        return this.description;
    }

    public void setComplete(boolean complete) {
        this.isComplete = complete;
    }

    public String status() {
        if (this.isComplete == false) {
            return "Incomplete";
        } else {
            return "Complete";
        }
    }

    public static void main(String[] args) {

        toDoList toDoList = new toDoList();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            toDoList.displayTasks();
            System.out.print(
                    "Enter a command (add, remove, setComplete, moveTaskUp, moveTaskDown, editTask, quit): ");
            String command = scanner.nextLine();
            if (command.equals("add")) {
                System.out.print("Enter the task to add:");
                String task = scanner.nextLine();
                Task task1 = new Task(task);
                toDoList.addTask(task1);
            } else if (command.equals("remove")) {
                System.out.print("Enter the position of the task to remove:");
                int index = scanner.nextInt();
                scanner.nextLine();
                toDoList.deleteTask(index - 1);
            } else if (command.equals("setComplete")) {
                System.out.print(
                        "Enter the position of the task to setComplete:");
                int index = scanner.nextInt();
                scanner.nextLine();
                toDoList.markTaskComplete(index - 1);
            } else if (command.equals("moveTaskUp")) {
                System.out.print("Which task do you want to move up?:");
                int index = scanner.nextInt();
                scanner.nextLine();
                toDoList.moveTaskUp(index);
            } else if (command.equals("moveTaskDown")) {
                System.out.print("Which task do you want to move down?:");
                int index = scanner.nextInt();
                scanner.nextLine();
                toDoList.moveTaskDown(index);
            } else if (command.equals("editTask")) {
                System.out.print("Which task do you want to edit?:");
                int index = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter a new description: ");
                String newDescription = scanner.nextLine();
                toDoList.editTask(index, newDescription);
            } else if (command.equals("quit")) {
                break;
            } else {
                System.out.println("Invalid command");
            }
        }

        scanner.close();
    }
}
