package Thread;

import Utils.Util;

public class WorkTask implements ThreadScript {

    @Override
    public void run() {
        System.out.println(Util.getPrefix("TASK"));

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println();
    }
}
