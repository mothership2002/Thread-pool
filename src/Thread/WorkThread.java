package Thread;

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

    public void setTask(WorkTask task) {
        this.task = task;
        notifyAll();
    }

    public WorkTask getTask() {
        return task;
    }
}
