import java.util.Random;

public class Benchmark {
    private static final int SEED = 42;
    private static final int[] N_VALUES = {100, 1000, 10000, 100000};

    public static void main(String[] args) {
        System.out.println("=== STARTING BENCHMARKS ===\n");

        runWorkload1();
        runWorkload2();
        runWorkload3();
        runWorkload4();

        System.out.println("\n=== BENCHMARKS FINISHED ===");
    }

    // Workload 1: Random Access (DynamicArray vs LinkedList)
    private static void runWorkload1() {
        System.out.println("--- Workload 1: Random Access (10,000 gets) ---");
        System.out.println("N\t\tDynamicArray (ms)\tLinkedList (ms)");

        for (int n : N_VALUES) {
            long daTime = 0;
            long llTime = 0;

            for (int r = 0; r < 5; r++) {
                Random rand = new Random(SEED + r);
                DynamicArray da = new DynamicArray();
                LinkedList ll = new LinkedList();
                for (int i = 0; i < n; i++) {
                    int val = rand.nextInt();
                    da.add(val);
                    ll.add(val);
                }

                int[] indices = new int[10000];
                for (int i = 0; i < 10000; i++) {
                    indices[i] = rand.nextInt(n);
                }

                long start = System.nanoTime();
                for (int idx : indices) {
                    da.get(idx);
                }
                daTime += (System.nanoTime() - start);

                start = System.nanoTime();
                for (int idx : indices) {
                    ll.get(idx);
                }
                llTime += (System.nanoTime() - start);
            }

            System.out.printf("%d\t\t%.3f\t\t\t%.3f\n", n, (daTime / 5.0) / 1e6, (llTime / 5.0) / 1e6);
        }
        System.out.println();
    }

    // Workload 2: Search (DynamicArray vs LinkedList)
    private static void runWorkload2() {
        System.out.println("--- Workload 2: Search (1,000 contains) ---");
        System.out.println("N\t\tDynamicArray (ms)\tLinkedList (ms)");

        for (int n : N_VALUES) {
            long daTime = 0;
            long llTime = 0;

            for (int r = 0; r < 5; r++) {
                Random rand = new Random(SEED + r);
                DynamicArray da = new DynamicArray();
                LinkedList ll = new LinkedList();
                for (int i = 0; i < n; i++) {
                    int val = rand.nextInt();
                    da.add(val);
                    ll.add(val);
                }

                int[] targets = new int[1000];
                for (int i = 0; i < 1000; i++) {
                    targets[i] = rand.nextInt();
                }

                long start = System.nanoTime();
                for (int val : targets) {
                    da.contains(val);
                }
                daTime += (System.nanoTime() - start);

                start = System.nanoTime();
                for (int val : targets) {
                    ll.contains(val);
                }
                llTime += (System.nanoTime() - start);
            }

            System.out.printf("%d\t\t%.3f\t\t\t%.3f\n", n, (daTime / 5.0) / 1e6, (llTime / 5.0) / 1e6);
        }
        System.out.println();
    }

    // Workload 3: Insert & Remove at Index 0 (1,000 ops)
    private static void runWorkload3() {
        System.out.println("--- Workload 3: Insertion & Removal at Index 0 ---");
        System.out.println("N\t\tDA Insert(ms)\tLL Insert(ms)\tDA Remove(ms)\tLL Remove(ms)");

        for (int n : N_VALUES) {
            long daInsTime = 0, llInsTime = 0;
            long daRemTime = 0, llRemTime = 0;

            for (int r = 0; r < 5; r++) {
                Random rand = new Random(SEED + r);
                DynamicArray da = new DynamicArray();
                LinkedList ll = new LinkedList();
                for (int i = 0; i < n; i++) {
                    int val = rand.nextInt();
                    da.add(val);
                    ll.add(val);
                }

                // Insert at 0
                long start = System.nanoTime();
                for (int i = 0; i < 1000; i++) {
                    da.add(0, 999);
                }
                daInsTime += (System.nanoTime() - start);

                start = System.nanoTime();
                for (int i = 0; i < 1000; i++) {
                    ll.add(0, 999);
                }
                llInsTime += (System.nanoTime() - start);

                // Remove at 0
                start = System.nanoTime();
                for (int i = 0; i < 1000; i++) {
                    da.remove(0);
                }
                daRemTime += (System.nanoTime() - start);

                start = System.nanoTime();
                for (int i = 0; i < 1000; i++) {
                    ll.remove(0);
                }
                llRemTime += (System.nanoTime() - start);
            }

            System.out.printf("%d\t\t%.3f\t\t%.3f\t\t%.3f\t\t%.3f\n",
                    n, (daInsTime / 5.0) / 1e6, (llInsTime / 5.0) / 1e6,
                    (daRemTime / 5.0) / 1e6, (llRemTime / 5.0) / 1e6);
        }
        System.out.println();
    }

    // Workload 4: Priority Processing (MinHeap)
    private static void runWorkload4() {
        System.out.println("--- Workload 4: MinHeap (N Inserts & N Extractions) ---");
        System.out.println("N\t\tInsert Total (ms)\tExtract Total (ms)");

        for (int n : N_VALUES) {
            long insTime = 0;
            long extTime = 0;

            for (int r = 0; r < 5; r++) {
                Random rand = new Random(SEED + r);
                int[] data = new int[n];
                for (int i = 0; i < n; i++) {
                    data[i] = rand.nextInt();
                }

                MinHeap heap = new MinHeap();

                long start = System.nanoTime();
                for (int val : data) {
                    heap.insert(val);
                }
                insTime += (System.nanoTime() - start);

                start = System.nanoTime();
                for (int i = 0; i < n; i++) {
                    heap.extractMin();
                }
                extTime += (System.nanoTime() - start);
            }

            System.out.printf("%d\t\t%.3f\t\t\t%.3f\n", n, (insTime / 5.0) / 1e6, (extTime / 5.0) / 1e6);
        }
        System.out.println();
    }
}