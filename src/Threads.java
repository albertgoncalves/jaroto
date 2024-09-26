class SynchronizedCounter {
    private int c = 0;

    synchronized void increment() {
        ++c;
    }

    synchronized int value() {
        return c;
    }
}

public class Threads {
    static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        SynchronizedCounter counter = new SynchronizedCounter();

        int  n = 10000;
        long start = System.currentTimeMillis();

        for (int i = 0; i < n; ++i) {
            Thread.startVirtualThread(() -> {
                sleep(1000);
                counter.increment();
            });
        }

        while (counter.value() < n) {
            sleep(50);
        }

        System.out.printf("%dms\n", System.currentTimeMillis() - start);
    }
}
