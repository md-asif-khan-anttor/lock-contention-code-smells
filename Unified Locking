/*
   Unified Locking Examlple
*/
public class UnifiedLocking {
    private final Object lock = new Object();
    private int counter = 0;
    private final List<String> items = new ArrayList<>();

    public void incrementCounter() {
        synchronized (lock) {
           ++counter; 
            // replace by sleep statement for benchmarking
        }
    }

    public void addItem(String item) {
        synchronized (lock) {
            items.add(item);
            // replace by sleep statement for benchmarking
        }
    }

/*
  Refactored Unified Locking
*/
public class UnifiedLocking {
    private final ReentrantLock lock = new ReentrantLock();
    private int counter = 0;
    private final List<String> items = new ArrayList<>();

    public void incrementCounter() {
        lock.lock();
        try {
            ++counter;
            // replace by sleep statement for benchmarking
        } finally {
            lock.unlock();
        }
    }

    public void addItem(String item) {
        lock.lock();
        try {
            items.add(item);
            // replace by sleep statement for benchmarking
        } finally {
            lock.unlock();
        }
    }
