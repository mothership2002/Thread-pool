package v2.thread;

import v2.thread.script.WorkTask;

public class WorkThread extends Thread {

    private WorkTask task;
    private volatile boolean shutdown = false;

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                while (task == null) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                    if (shutdown) {
                        return;
                    }
                }
                task.run();
                task = null;
            }
        }
    }

    public synchronized void setTask(WorkTask task) {
        this.task = task;
        notify();
    }

    public WorkTask getTask() {
        return task;
    }

    public synchronized void shutdown() {
        this.shutdown = true;
        notify();
    }
}
