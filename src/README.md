# Assignment 2: Algorithmic Analysis, Correctness and Performance Trade-offs

## 1. Overview
This project implements three fundamental data structures from scratch in Java:
- **Dynamic Array** (resizing array)
- **Singly Linked List**
- **Min-Heap** (binary heap)

The objective is to analyze their theoretical complexities, prove algorithmic correctness using loop invariants, and conduct empirical performance benchmarking under four distinct workloads.

---

## 2. Complexity Analysis

| Data Structure | Operation | Best Case | Average Case | Worst Case | Auxiliary Space |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **Dynamic Array** | `get(i)` | $\Theta(1)$ | $\Theta(1)$ | $\Theta(1)$ | $O(1)$ |
| | `add(x)` | $\Theta(1)$ | $\Theta(1)$ (amortized) | $\Theta(n)$ | $O(1)$ |
| | `add(index, x)` | $\Theta(1)$ | $\Theta(n)$ | $\Theta(n)$ | $O(1)$ |
| | `remove(index)` | $\Theta(1)$ | $\Theta(n)$ | $\Theta(n)$ | $O(1)$ |
| | `contains(x)` | $\Theta(1)$ | $\Theta(n)$ | $\Theta(n)$ | $O(1)$ |
| **Linked List** | `get(i)` | $\Theta(1)$ | $\Theta(n)$ | $\Theta(n)$ | $O(1)$ |
| | `add(x)` | $\Theta(1)$ | $\Theta(1)$ | $\Theta(1)$ | $O(1)$ |
| | `add(index, x)` | $\Theta(1)$ | $\Theta(n)$ | $\Theta(n)$ | $O(1)$ |
| | `remove(index)` | $\Theta(1)$ | $\Theta(n)$ | $\Theta(n)$ | $O(1)$ |
| | `contains(x)` | $\Theta(1)$ | $\Theta(n)$ | $\Theta(n)$ | $O(1)$ |
| **Min-Heap** | `insert(x)` | $\Theta(1)$ | $O(\log n)$ | $O(\log n)$ | $O(1)$ |
| | `peekMin()` | $\Theta(1)$ | $\Theta(1)$ | $\Theta(1)$ | $O(1)$ |
| | `extractMin()` | $O(\log n)$ | $O(\log n)$ | $O(\log n)$ | $O(1)$ |

### Explanations:
- `get(index)` in Dynamic Array operates in $O(1)$ due to contiguous memory allocation and direct index arithmetic. In Linked List, it requires traversing nodes sequentially, leading to $O(n)$ time.
- `add(0, x)` is $O(n)$ for Dynamic Array because all existing elements must be shifted. For Linked List, updating pointers takes $O(1)$ time at index 0.

---

## 3. Correctness & Loop Invariants

### Proof 1: `DynamicArray.contains(int x)`
1. **Loop Invariant Statement:** At the start of each iteration $i$ (for $0 \le i \le \text{size}$), the target element $x$ is not present in the subarray $\text{data}[0 \dots i-1]$.
2. **Initialization:** Before the loop starts ($i = 0$), the subarray $\text{data}[0 \dots -1]$ is empty, so the claim trivially holds.
3. **Maintenance:** During iteration $i$, if $\text{data}[i] == x$, the loop returns `true`, correctly identifying presence. Otherwise, $x \neq \text{data}[i]$. Combined with the invariant that $x \notin \text{data}[0 \dots i-1]$, it follows that $x \notin \text{data}[0 \dots i]$. Incrementing $i$ maintains the invariant for the next iteration.
4. **Termination:** The loop terminates either when $x$ is found (returns `true`) or when $i = \text{size}$.
5. **Correctness:** Upon termination with $i = \text{size}$, the invariant guarantees that $x \notin \text{data}[0 \dots \text{size}-1]$. Therefore, returning `false` is correct.

### Proof 2: `MinHeap.siftUp(int index)`
1. **Loop Invariant Statement:** For all nodes in the heap, the min-heap property holds except possibly between the node at `index` and its parent.
2. **Initialization:** Prior to `siftUp`, a new element was added at position `size - 1`. The rest of the tree maintains the min-heap property.
3. **Maintenance:** If `heap[index] < heap[parent]`, swapping them fixes the violation at `index` and shifts potential violation up to `parent`. The rest of the subtree retains the min-heap property.
4. **Termination:** The loop terminates when `index == 0` (node reached root) or `heap[index] >= heap[parent]`.
5. **Correctness:** At termination, no node violates `heap[node] >= heap[parent]`, proving the heap structure is restored.

---

## 4. Experimental Setup
- **N values:** 100, 1,000, 10,000, 100,000
- **Random Seed:** `42`
- **Timing Method:** `System.nanoTime()`
- **Runs per experiment:** 5 iterations averaged.

---

## 5. Experimental Results

### Workload 1: Random Access (10,000 get operations)
| N | DynamicArray Time (ms) | LinkedList Time (ms) |
| :--- | :--- | :--- |
| **100** | 0.358 | 1.197 |
| **1,000** | 0.055 | 9.823 |
| **10,000** | 0.040 | 106.936 |

---

## 6. Performance and Design Analysis
1. **Cache Locality:** Dynamic Array stores elements sequentially in memory, enabling CPU cache prefetching. Linked List uses pointers to scattered memory nodes, causing frequent cache misses.
2. **Dynamic Array vs Linked List:** Dynamic Array is preferred for read-heavy workloads (`get`), while Linked List excels at frequent insertions/removals at the head without memory reallocation overhead.
3. **Min-Heap Trade-offs:** Binary Heaps provide $O(\log n)$ insertions and extractions, making them optimal for priority queue implementations.