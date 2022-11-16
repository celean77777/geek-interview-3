package lockCounters;

import java.util.concurrent.locks.ReentrantLock;

public class MyLockCounter {
    ReentrantLock lock = new ReentrantLock();
    int count = 0;

    int getCount(){
        return this.count;
    }

    void increment() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    void decrement() {
        lock.lock();
        try {
            count--;
        } finally {
            lock.unlock();
        }
    }
}
