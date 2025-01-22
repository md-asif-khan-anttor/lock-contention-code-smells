/*
  Loop Inside Critical Section (LIC) Example
*/
public class LIC {
    Object lock = new Object();
    int value = lock.value();

    public int doSomething(ArrayList<Integer> A) {
        int x = 0;
        synchronized (lock) {
            int x = 0;
            while (A.get(x) != value) {
                x++;
                // replace by sleep statement for benchmarking
            }
            return x;
        }
    }
}

/*
  Refactored Loop Inside Critical Section (LIC)
*/

public class LIC {
    Object lock = new Object();
    int value = lock.value();

    public int doSomething(ArrayList A) {
        int x = 0;

        while (A.get(x) != value) {
            synchronized (lock) {
                x++; 
                // replace by sleep statement for benchmarking
            }
        }

        return x;
    }
}
