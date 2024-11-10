package v2.thread;

import v2.queue.TaskQueue;
import v2.thread.script.WorkTask;

public class Selector {

    private final TaskQueue<WorkTask> taskQueue;

    public Selector(TaskQueue<WorkTask> taskQueue) {
        this.taskQueue = taskQueue;
    }

    public void addTask() {
        while (!taskQueue.isSuccessToAdd(new WorkTask()));
    }

}
