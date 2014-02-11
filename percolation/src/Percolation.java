/**
 * Created by Kafka Liu on 14-2-9.
 */
public class Percolation {
  private int n;

  private int[] ids;

  private WeightedQuickUnionUF uf;
  public Percolation(int n) {
    this.n = n;
    ids = new int[n * n + 2];
    for (int i = 1; i < n * n + 1; i++) {
      ids[i] = -i;
    }
    ids[n * n + 1] = n * n + 1;
    uf = new WeightedQuickUnionUF(ids.length);
  }

  public void open(int i, int j) {
    if (isOpen(i, j)) return;
    int p = (i - 1) * n + j;
    ids[p] = -ids[p];
    if (i == 1) {
      uf.union(0, p);
    } else if (i == n) {
      uf.union(n * n + 1, p);
    }
    if (i > 1 && isOpen(i - 1, j)) {
      uf.union(p, p - n);
    }
    if (i < n && isOpen(i + 1, j)) {
      uf.union(p, p + n);
    }
    if (j > 1 && isOpen(i, j - 1)) {
      uf.union(p, p - 1);
    }
    if (j < n && isOpen(i, j + 1)) {
      uf.union(p, p + 1);
    }
  }

  public boolean isOpen(int i, int j) {
    if (i < 1 || i > n || j < 1 || j > n) {
      throw new java.lang.IndexOutOfBoundsException("Indices i and j should "
          + "be integers between 1 and " + n);
    }
    return ids[(i - 1) * n + j] > 0;
  }

  public boolean isFull(int i, int j) {
    return isOpen(i, j) && uf.connected(0, (i - 1) * n + j);
  }

  public boolean percolates() {
    return uf.connected(0, n * n + 1);
  }
}