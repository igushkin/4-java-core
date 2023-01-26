import Manager.*;
import Task.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = Managers.getDefault();
        addTasks(manager);

        ArrayList<Task> allTasks = manager.getAllTasks();
        ArrayList<Epic> allEpics = manager.getAllEpics();
        ArrayList<Subtask> allSubtasks = manager.getAllSubtasks();

        System.out.println(allTasks);
        System.out.println(allEpics);
        System.out.println(allSubtasks);


        // Test #1

        Task newTask = new Task(0, "Новая задача", "Новое описание", TaskStatus.DONE);
        manager.updateTask(newTask);
        Task task = manager.getTaskById(0);
        boolean isCorrect = manager.getAllTasks().size() == 2
                && task.getStatus() == newTask.getStatus()
                && task.getName() == newTask.getName()
                && task.getDescription() == newTask.getDescription()
                && manager.getHistory().size() == 1;

        System.out.println(isCorrect);


        // Test #2

        manager.deleteTaskById(0);
        isCorrect = manager.getAllTasks().size() == 1
                && manager.getAllTasks().get(0).getId() == 1
                && manager.getHistory().size() == 1;

        System.out.println(isCorrect);


        // Test #3

        manager.deleteAllTasks();
        isCorrect = manager.getAllTasks().size() == 0 && manager.getHistory().size() == 1;

        System.out.println(isCorrect);


        // Test #4

        Epic newEpic = new Epic(2, "Новый эпик", "Новое описание эпика", TaskStatus.NEW);
        manager.updateEpic(newEpic);
        task = manager.getEpicById(2);
        isCorrect = task.getName() == newEpic.getName()
                && task.getDescription() == newEpic.getDescription()
                && manager.getHistory().size() == 2;

        System.out.println(isCorrect);


        // Test #5

        manager.deleteSubtaskById(5);
        Epic epic = manager.getEpicById(2);
        isCorrect = epic.getStatus() == TaskStatus.NEW
                && epic.getSubtasks().size() == 1
                && epic.getSubtasks().get(4).getId() == 4
                && manager.getHistory().size() == 3;

        System.out.println(isCorrect);

        addTasks(manager);


        // Test #6

        Subtask newSubtask = new Subtask(4, "Новая подзадача", "Новое описание", TaskStatus.DONE);
        epic = manager.getEpicById(2);
        newSubtask.setEpic(epic);
        manager.updateSubtask(newSubtask);
        Subtask subtask = manager.getSubtaskById(newSubtask.getId());

        isCorrect = newSubtask.getName() == subtask.getName()
                && newSubtask.getDescription() == subtask.getDescription()
                && newSubtask.getEpic() == subtask.getEpic()
                && subtask.getEpic().getStatus() == TaskStatus.DONE
                && manager.getHistory().size() == 5
                && manager.getSubtasksByEpicId(2).size() == 1;

        System.out.println(isCorrect);


        // Test #7

        manager.deleteAllSubtasks();
        isCorrect = manager.getAllSubtasks().stream().allMatch(i -> i.getStatus() == TaskStatus.NEW)
                && manager.getAllEpics().stream().allMatch(i -> i.getSubtasks().size() == 0);

        System.out.println(isCorrect);


        // Tetst #8

        for (int i = 0; i < 11; i++) {
            manager.getTaskById(7);
        }
        isCorrect = manager.getHistory().size() == 10;

        System.out.println(isCorrect);

    }

    public static void addTasks(TaskManager manager) {
        Task task1 = new Task("Задача 1", "Описание 1", TaskStatus.NEW);
        Task task2 = new Task("Задача 2", "Описание 2", TaskStatus.NEW);

        Epic epic1 = new Epic("Эпик 1", "Описание 1", TaskStatus.NEW);
        Epic epic2 = new Epic("Эпик 2", "Описание 2", TaskStatus.NEW);

        manager.createTask(task1);
        manager.createTask(task2);
        manager.createEpic(epic1);
        manager.createEpic(epic2);

        manager.createSubtask(new Subtask(epic1, "Подзадача 1", "Описание подзадачи 1", TaskStatus.NEW));
        manager.createSubtask(new Subtask(epic1, "Подзадача 2", "Описание подзадачи 2", TaskStatus.NEW));
        manager.createSubtask(new Subtask(epic2, "Подзадача 1", "Описание подзадачи 1", TaskStatus.NEW));
    }
}