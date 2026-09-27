public class Main {
    public static void main(String[] args) {
        DynamicArray arr = new DynamicArray();
        arr.add(10);
        arr.add(20);
        System.out.println("DynamicArray index 0: " + arr.get(0));

        MinHeap heap = new MinHeap();
        heap.insert(5);
        heap.insert(2);
        System.out.println("MinHeap min element: " + heap.peekMin());

        System.out.println("Всё работает идеально!");
    }
}