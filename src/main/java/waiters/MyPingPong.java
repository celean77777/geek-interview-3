package waiters;

public class MyPingPong {
    private final Object mon = new Object();
    private volatile String currentP = "PING";
    public static void main(String[] args) {
        MyPingPong waitNotifyObj = new MyPingPong();
        Thread thread1 = new Thread(waitNotifyObj::printA);
        Thread thread2 = new Thread(waitNotifyObj::printB);
        thread1.start();
        thread2.start();
    }
    public void printA() {
        synchronized (mon) {
            try {
                for (int i = 0; i < 10; i++) {
                    while (!currentP.equals("PING")) {
                        mon.wait();
                    }
                    System.out.print("PING_");
                    currentP = "PONG";
                    mon.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void printB() {

        synchronized (mon) {
            try {
                for (int i = 0; i < 10; i++) {
                    while (!currentP.equals("PONG")) {
                        mon.wait();
                    }
                    System.out.print("PONG_");
                    currentP = "PING";
                    mon.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
