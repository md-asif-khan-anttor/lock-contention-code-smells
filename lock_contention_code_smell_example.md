# Lock Contention Code Smells

## 1. Synchronized Method

**Definition:**
The Synchronized Method refers to a synchronization mechanism that protects large code sections or critical regions in multi-threaded programs.

**Issue:**
When the entire method is synchronized, a thread acquires it before entering a critical section and releases it after completing the entire code section. When a thread holds the lock, other threads are blocked from accessing the same critical section.

### Example Code:
```java
public class SynchronizedMethod {
    public final Set<String> users;
    public final Set<String> queries;

    public synchronized void addUser(String u) { 
        users.add(u); 
    }
    public synchronized void addQuery(String q) { 
        queries.add(q);
    }
    public synchronized void removeUser(String u) { 
        users.remove(u); 
    }
    public synchronized void removeQuery(String q) { 
        queries.remove(q); 
    }
}
```

### General Recommendation:
To minimize unnecessary contention, synchronize on specific objects instead of the entire method, which increases lock granularity.

### Refactored Code:
```java
public class SynchronizedMethod {
    public final Set<String> users; 
    public final Set<String> queries;

    public void addUser(String u) {
        synchronized (users) {
            users.add(u); 
        }
    }
    public void addQuery(String q) {
        synchronized (queries) {
            queries.add(q);
        }
    }
    public void removeUser(String u) {
        synchronized (users) {
            users.remove(u);
        }
    }
    public void removeQuery(String q) {
        synchronized (queries) {
            queries.remove(q);
        }
    }
}
```

---

## 2. Unified Locking

**Definition:**
Unified Locking occurs when logically independent variables, accessed by different threads, share the same lock or synchronization mechanism.

### Example Code:
```java
public class UnifiedLocking {
    private final Object lock = new Object();
    private int counter = 0;
    private final List<String> items = new ArrayList<>();

    public void incrementCounter() {
        synchronized (lock) {
           ++counter; 
        }
    }

    public void addItem(String item) {
        synchronized (lock) {
            items.add(item);
        }
    }
}
```

### Recommendation:
Use `ReentrantLock` to allow more flexible concurrency control.

### Refactored Code:
```java
public class UnifiedLocking {
    private final ReentrantLock lock = new ReentrantLock();
    private int counter = 0;
    private final List<String> items = new ArrayList<>();

    public void incrementCounter() {
        lock.lock();
        try {
            ++counter;
        } finally {
            lock.unlock();
        }
    }

    public void addItem(String item) {
        lock.lock();
        try {
            items.add(item);
        } finally {
            lock.unlock();
        }
    }
}
```

---

## 3. Same Lock

**Definition:**
Same Lock occurs when multiple critical sections synchronize using the same object, leading to contention.

### Example Code:
```java
public class SameLock {
    int counter;
    
    public void task1() {
        synchronized(counter) {
            counter.toString();
        }
    }
    
    public void task2() {
        synchronized(counter) {
            counter += 1;
        }
    }
}
```

### Recommendation:
Use `AtomicInteger` for better concurrency.

### Refactored Code:
```java
public class SameLock {
    private final AtomicInteger counter = new AtomicInteger();

    public void task1() {
        counter.toString();
    }
    public void task2() {
        counter.incrementAndGet();
    }
}
```

---

## 4. Loop Inside Critical Section (LIC)

**Definition:**
A loop inside a critical section prolongs execution time unnecessarily, increasing contention.

### Example Code:
```java
public class LIC {
    Object lock = new Object();
    int value = lock.value();

    public int doSomething(ArrayList<Integer> A) {
        int x = 0;
        synchronized (lock) {
            while (A.get(x) != value) {
                x++;
            }
            return x;
        }
    }
}
```

### Recommendation:
Move the loop outside the critical section.

### Refactored Code:
```java
public class LIC {
    Object lock = new Object();
    int value = lock.value();

    public int doSomething(ArrayList<Integer> A) {
        int x = 0;
        while (A.get(x) != value) {
            synchronized (lock) {
                x++; 
            }
        }
        return x;
    }
}
```

---

## 6. Loop Outside Critical Section (LOC)

**Definition:**
A loop outside a critical section can cause frequent re-entry, leading to recurring accessibility issues.

### Example Code:
```java
public class LOC {
    Object lock = new Object();

    public void doSomething() {
        Boolean x = true;
        while (x) {
            synchronized (lock) {
                x = System.currentTimeMillis() == 123456789;
            }
        }
    }
}
```

### Recommendation:
Optimize the loop by reducing re-entry frequency.

### Refactored Code:
```java
public class LOC {
    Object lock;

    public void doSomething() {
        int time;
        do {
            synchronized (lock) {
                time = System.currentTimeMillis();
            }
        } while (time == 123456789);
    }
}
```

---
