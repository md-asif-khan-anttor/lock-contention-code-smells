/*
  Overly Split locking Example
*/

public class OverlySplit{ {
    private static List<Integer> buffer = new ArrayList<>();
    private static int foo;
    private static int bar;

    private static void addToBuffer(int x, int y) {
        try {
            synchronized (buffer) {
                buffer.add(x);
                // replace by sleep statement for benchmarking
            }
            foo = bar;
            
            synchronized (buffer) {
                buffer.add(y);
                // replace by sleep statement for benchmarking
            }
            synchronized (buffer) {
                if (!buffer.isEmpty()) {
                    buffer.remove(0);
                    // replace by sleep statement for benchmarking
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/*
  Overly Split Refactored
*/

private static void addToBuffer(int x, int y) {
        try {
            synchronized (buffer) {
                buffer.add(x); 
                // replace by sleep statement for benchmarking
                foo = bar;
                buffer.add(y);
                // replace by sleep statement for benchmarking
                if (!buffer.isEmpty()) {
                    buffer.remove(0);
                    // replace by sleep statement for benchmarking
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
