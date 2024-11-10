package v1.thread;

import v1.queue.TaskQueue;

public class Selector {

    private final TaskQueue<WorkTask> taskQueue;

    public Selector(TaskQueue<WorkTask> taskQueue) {
        this.taskQueue = taskQueue;
    }

    public void addTask() {
        while (!taskQueue.isSuccessToAdd(new WorkTask()));
    }

}
