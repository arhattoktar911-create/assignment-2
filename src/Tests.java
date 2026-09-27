public class Tests {
    public static void main(String[] args) {
        System.out.println("=== RUNNING CORRECTNESS TESTS ===\n");

        testDynamicArray();
        testLinkedList();
        testMinHeap();

        System.out.println("\nALL TESTS PASSED SUCCESSFULLY!");
    }

    private static void testDynamicArray() {
        System.out.print("Testing DynamicArray... ");
        DynamicArray arr = new DynamicArray();

        // Edge case: Empty & Boundary
        assert arr.size() == 0;

        // Addition & Resizing
        for (int i = 0; i < 20; i++) {
            arr.add(i * 10);
        }
        assert arr.size() == 20;
        assert arr.get(0) == 0;
        assert arr.get(19) == 190;

        // Insert at index
        arr.add(0, 999);
        assert arr.get(0) == 999;
        assert arr.get(1) == 0;

        // Remove
        int removed = arr.remove(0);
        assert removed == 999;
        assert arr.get(0) == 0;

        // Contains
        assert arr.contains(100);
        assert !arr.contains(9999);

        System.out.println("PASSED");
    }

    private static void testLinkedList() {
        System.out.print("Testing LinkedList... ");
        LinkedList list = new LinkedList();

        assert list.size() == 0;

        for (int i = 0; i < 20; i++) {
            list.add(i * 5);
        }
        assert list.size() == 20;
        assert list.get(0) == 0;

        list.add(0, 777);
        assert list.get(0) == 777;

        int removed = list.remove(0);
        assert removed == 777;

        assert list.contains(25);
        assert !list.contains(9999);

        System.out.println("PASSED");
    }

    private static void testMinHeap() {
        System.out.print("Testing MinHeap... ");
        MinHeap heap = new MinHeap();

        int[] values = {15, 3, 20, 1, 8, 2};
        for (int v : values) {
            heap.insert(v);
        }

        // Check Heap Extraction Property (Non-decreasing order)
        int prev = heap.extractMin();
        while (heap.size() > 0) {
            int current = heap.extractMin();
            assert prev <= current : "Heap property violated!";
            prev = current;
        }

        System.out.println("PASSED");
    }
}