package thread;

import queue.TaskQueue;
import utils.Util;

import java.util.ArrayList;
import java.util.List;

public class ManageTask implements ThreadScript {

    private final TaskQueue<WorkTask> taskQueue;
    private final List<Thread> workerThreads = new ArrayList<>();

    public ManageTask(TaskQueue<WorkTask> taskQueue, int workerCount) {
        this.taskQueue = taskQueue;
        for (int i = 0; i < workerCount; i++) {
            Thread thread = new WorkThread();
            thread.setName("WorkerThread-" + i);
            thread.start();
            this.workerThreads.add(thread);
        }
    }

    @Override
    public void run() {
        System.out.println(Util.getPrefix("MANAGE", "Start"));
        while (true) {
            if (taskQueue.isHaveElement()) {
                WorkThread freeWorkerThread = getFreeWorkerThread();
                if (freeWorkerThread == null) {
                    continue;
                }
                WorkTask task = taskQueue.getTask();
                freeWorkerThread.setTask(task);
            }
        }
    }

    private WorkThread getFreeWorkerThread() {
        for (Thread workerThread : workerThreads) {
            if (((WorkThread) workerThread).getTask() == null) {
                return ((WorkThread) workerThread);
            }
        }
        return null;
    }
}
