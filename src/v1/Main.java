package v1;

import v1.queue.TaskQueue;
import v1.thread.ManageTask;
import v1.thread.Selector;
import v1.thread.WorkTask;


public class Main {

    public static void main(String[] args) throws InterruptedException {
        TaskQueue<WorkTask> taskQueue = new TaskQueue<>(5);
        ManageTask manageTask = new ManageTask(taskQueue, 20);
        Selector selector = new Selector(taskQueue);

        Thread thread = new Thread(manageTask);
        thread.start();

        // 건당 100ms소요
        // 2만건 단일스레드 예측 소요량은 약 33분 
        // 워크스레드의 갯수를 30개일때 예상 소요량은 약 1분 40초 확인 완료
        for (int i = 0; i < 20000; i++) {
            selector.addTask();
        }
        thread.join();
    }
}