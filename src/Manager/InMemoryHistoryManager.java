package Manager;

import Task.Task;

import java.util.LinkedList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    private LinkedList<Task> taskViewHistory;
    private static final int MAX_HISTORY_SIZE = 10;

    public InMemoryHistoryManager() {
        taskViewHistory = new LinkedList<>();
    }

    @Override
    public void add(Task task) {
        if (task == null) {
            return;
        }
        taskViewHistory.add(task);
        if (taskViewHistory.size() > MAX_HISTORY_SIZE) {
            taskViewHistory.remove(0);
        }
    }

    @Override
    public List<Task> getHistory() {
        return this.taskViewHistory;
    }
}
