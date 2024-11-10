package v2.thread.script;

import utils.Util;

public class WorkTask implements ThreadScript {

    @Override
    public void run() {
        System.out.println(Util.getPrefix("TASK", "Start"));
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Util.getPrefix("TASK", "End"));
    }
}
