import java.util.Iterator;

public class Subset {
  public static void main(String[] args) {
    if (args.length != 1) {
      StdOut.println("Usage: Subset num.");
    }
    int k = Integer.parseInt(args[0]);
    RandomizedQueue queue = new RandomizedQueue();
    for (String str: StdIn.readAllStrings()) {
      queue.enqueue(str);
    }
    int i = 0;
    for (Iterator<String> it = queue.iterator(); i < k && it.hasNext();) {
      StdOut.println(it.next());
      i++;
    }
  }
}