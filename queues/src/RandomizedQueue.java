import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
  private RandomizedQueueNode head, tail;
  private int size;

  public RandomizedQueue() {

  }

  public boolean isEmpty() {
    return size == 0;
  }

  public int size() {
    return size;
  }

  public void enqueue(Item item) {
    RandomizedQueueNode node = createNode(item);
    if (tail != null) tail.next = node;
    tail = node;
    if (size == 0) head = tail;
    size++;
  }

  public Item dequeue() {
    if (size == 0) throw new NoSuchElementException();
    RandomizedQueueNode node = head;
    head = node.next;
    size--;
    if (size == 0) {
      tail = null;
    }
    return node.item;
  }

  public Item sample() {
    return iterator().next();
  }

  public Iterator<Item> iterator() {
    int[] indexes = new int[size];
    Item[] nodes = (Item[]) new Object[size];
    for (int i = 0; i < size; i++) {
      indexes[i] = i;
    }
    StdRandom.shuffle(indexes);
    RandomizedQueueNode current = head;
    for (int i = 0; i < size; i++) {
      nodes[i] = current.item;
      current = current.next;
    }
    return new RandomizedQueueIterator(nodes, indexes);
  }

  public static void main(String[] args) {
    RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<Integer>();
    randomizedQueue.enqueue(1);
    randomizedQueue.enqueue(2);
    randomizedQueue.enqueue(3);
    randomizedQueue.enqueue(4);
    randomizedQueue.enqueue(5);
    randomizedQueue.enqueue(6);
    randomizedQueue.enqueue(7);
    randomizedQueue.enqueue(8);
    randomizedQueue.enqueue(9);
    randomizedQueue.enqueue(10);
    for (Iterator<Integer> it = randomizedQueue.iterator(); it.hasNext();) {
      StdOut.println(it.next());
    }
  }

  private RandomizedQueueNode createNode(Item item) {
    if (item == null) throw new NullPointerException();
    return new RandomizedQueueNode(item);
  }

  private class RandomizedQueueNode {
    private Item item;

    private RandomizedQueueNode next;

    private RandomizedQueueNode(Item item) {
      this.item = item;
    }
  }

  private class RandomizedQueueIterator implements Iterator {
    private Item[] items;

    private int[] indexes;

    private int current;

    private RandomizedQueueIterator(Item[] items, int[] indexes) {
      this.items = items;
      this.indexes = indexes;
    }

    @Override
    public boolean hasNext() {
      return current != indexes.length;
    }

    @Override
    public Item next() {
      if (!hasNext()) throw new NoSuchElementException();
      return items[indexes[current++]];
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException("remove");
    }
  }
}