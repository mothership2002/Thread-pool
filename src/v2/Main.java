package v2;

import utils.Util;
import v2.queue.TaskQueue;
import v2.thread.script.ManageTask;
import v2.thread.Selector;
import v2.thread.script.WorkTask;

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
        long l = System.currentTimeMillis();
        for (int i = 0; i < 20000; i++) {
            selector.addTask();
            if (System.currentTimeMillis() - l >= 5000) {
                manageTask.shutdown();
                break;
            }
        }
        thread.join();
        System.out.println(Util.getPrefix("MAIN", "End"));
    }
}
