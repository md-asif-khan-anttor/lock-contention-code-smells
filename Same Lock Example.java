/*
  Same Lock Example
*/
public class SameLock {
    int counter;
    
    public void task1() {
        synchronized(counter) {
            counter.toString();
            // replace by sleep statement for benchmarking
        }
    }
    
    public void task2() {
        synchronized(counter) {
            counter += 1;
            // replace by sleep statement for benchmarking
        }
    }
}

/*
  Same Lock Refactored
*/

public class SameLock {
    private final AtomicInteger counter = new AtomicInteger();

    public void task1() {
        counter.toString();
        // replace by sleep statement for benchmarking
    }
    public void task2() {
        counter.incrementAndGet();
        // replace by sleep statement for benchmarking
        }
    }
}
