// models/Metrics.java
package GraphIOModels;

public class Metrics {
    public long edgeInspections = 0;
    public long heapPushes = 0;
    public long heapPops = 0;
    public long keyUpdates = 0;
    public long comparisons = 0;
    public long unionOps = 0;
    public long findOps = 0;

    public long totalOps() {
        return edgeInspections + heapPushes + heapPops + keyUpdates + comparisons + unionOps + findOps;
    }
}
