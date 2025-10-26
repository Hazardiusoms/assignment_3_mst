package algorithms;

public class OperationCounter {
    private long count;

    public OperationCounter() {
        this.count = 0;
    }

    public void increment() {
        count++;
    }

    public void add(long value) {
        count += value;
    }

    public long getCount() {
        return count;
    }

    public void reset() {
        count = 0;
    }
}
