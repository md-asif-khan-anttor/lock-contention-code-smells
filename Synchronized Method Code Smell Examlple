/*
  Synchronized Method Examlple
*/
public class SynchronizedMethod {
    public final Set<String> users;
    public final Set<String> queries;

    public synchronized void addUser(String u) { 
        users.add(u); 
        // replace by sleep statement for benchmarking
    }
    public synchronized void addQuery(String q) { 
        queries.add(q);
        // replace by sleep statement for benchmarking
    }
    public synchronized void removeUser(String u) { 
        users.remove(u); 
        // replace by sleep statement for benchmarking
    }
    public synchronized void removeQuery(String q) { 
        queries.remove(q); 
        // replace by sleep statement for benchmarking
    }
}

/*
  Refactored Synchronized Method
*/
public class SynchronizedMethod {
    public final Set<String> users; 
    public final Set<String> queries;

    public void addUser(String u) {
        synchronized (users) {
            users.add(u); 
        // replace by sleep statement for benchmarking
        }
    }
    public void addQuery(String q) {
        synchronized (queries) {
            queries.add(q);
        // replace by sleep statement for benchmarking
        }
    }
    public void removeUser(String u) {
        synchronized (users) {
            users.remove(u);
        // replace by sleep statement for benchmarking
        }
    }
    public void removeQuery(String q) {
        synchronized (queries) {
            queries.remove(q);
        // replace by sleep statement for benchmarking
        }
    }
}
