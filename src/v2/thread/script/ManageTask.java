package v2.thread.script;

import utils.Util;
import v2.queue.TaskQueue;
import v2.thread.WorkThread;

import java.util.ArrayList;
import java.util.List;

public class ManageTask implements ThreadScript {

    private final TaskQueue<WorkTask> taskQueue;
    private final List<WorkThread> workerThreads = new ArrayList<>();
    private volatile boolean isRunning = true;

    public ManageTask(TaskQueue<WorkTask> taskQueue, int workerCount) {
        this.taskQueue = taskQueue;
        for (int i = 0; i < workerCount; i++) {
            WorkThread thread = new WorkThread();
            thread.setName("WorkerThread-" + i);
            thread.start();
            this.workerThreads.add(thread);
        }
    }

    @Override
    public void run() {
        System.out.println(Util.getPrefix("MANAGE", "Start"));
        while (isRunning) {
            WorkThread freeWorkerThread = getFreeWorkerThread();
            if (freeWorkerThread == null) {
                continue;
            }
            WorkTask task = taskQueue.getTask();
            freeWorkerThread.setTask(task);
        }
        shutdownWorkerThreads();
    }

    private WorkThread getFreeWorkerThread() {
        for (WorkThread workerThread : workerThreads) {
            if (workerThread.getTask() == null) {
                return workerThread;
            }
        }
        return null;
    }

    private void shutdownWorkerThreads() {
        for (WorkThread workerThread : workerThreads) {
            synchronized (workerThread) {
                workerThread.notify();
                workerThread.shutdown();
            }
        }
        for (Thread workerThread : workerThreads) {
            try {
                workerThread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
        System.out.println(Util.getPrefix("MANAGE", "End"));
    }

    public void shutdown() {
        this.isRunning = false;
    }

}
