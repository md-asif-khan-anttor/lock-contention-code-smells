/*
  Loop Outside Critical Section Example
*/

public class LOC {
    Object lock = new Object();

    public void doSomething() {
        Boolean x = true;
        while (x) {
            synchronized (lock) {
                x = System.currentTimeMillis() == 123456789;
                // replace by sleep statement for benchmarking
            }
        }
    }
}

/*
  Refactored Loop Outside Critical Section
*/

public class LOC {
    Object lock;

    public void doSomething() {
        int time;
        do {
            synchronized (lock) {
                time = System.currentTimeMillis();
            }
        } while (time == 123456789);
        // replace by sleep statement for benchmarking
    }
}
