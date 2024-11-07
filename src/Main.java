import queue.TaskQueue;
import thread.ManageTask;
import thread.Selector;
import thread.WorkTask;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        TaskQueue<WorkTask> taskQueue = new TaskQueue<>(5);
        ManageTask manageTask = new ManageTask(taskQueue, 20);
        Selector selector = new Selector(taskQueue);

        Thread thread = new Thread(manageTask);
        thread.start();
//        [2024-11-07T22:09:55.4566752]
        // 건당 100ms소요
        // 2만건 단일스레드 예측 소요량은 약 33분
        // 워크스레드의 갯수를 30개일때 예상 소요량은 약 1분 40초
        for (int i = 0; i < 20000; i++) {
            selector.addTask();
        }
        thread.join();
    }
}