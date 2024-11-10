package v1.thread;

public class WorkThread extends Thread {

    private WorkTask task;

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
}
