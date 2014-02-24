import java.util.Iterator;

public class Subset {
  public static void main(String[] args) {
    if (args.length != 1) {
      StdOut.println("Usage: Subset num.");
    }
    int k = Integer.parseInt(args[0]);
    RandomizedQueue queue = new RandomizedQueue();
    if (k > 0)
      for (String str: StdIn.readAllStrings()) {
        if (queue.size() == k) queue.dequeue();
        queue.enqueue(str);
      }
    for (Iterator<String> it = queue.iterator(); it.hasNext();) {
      StdOut.println(it.next());
    }
  }
}