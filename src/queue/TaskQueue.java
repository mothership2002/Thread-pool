package queue;

import java.util.LinkedList;
import java.util.Queue;

public class TaskQueue<T> {

    private final int maxSize;
    private final Queue<T> taskQueue;

    public TaskQueue(int maxSize) {
        this.taskQueue = new LinkedList<>();
        this.maxSize = maxSize;
    }

    public boolean isHaveElement() {
        return taskQueue.peek() != null;
    }

    public synchronized boolean isSuccessToAdd(T task) {
        if (maxSize <= taskQueue.size()) {
            return false;
        }
        taskQueue.add(task);
        return true;
    }

    public synchronized T getTask() {
        return taskQueue.poll();
    }

}
