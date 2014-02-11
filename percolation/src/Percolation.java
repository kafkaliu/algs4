/**
 * Created by Kafka Liu on 14-2-9.
 */
public class Percolation {
  private int N;

  private int[] ids;

  private WeightedQuickUnionUF uf;
  public Percolation(int N) {
    this.N = N;
    ids = new int[N * N + 2];
    for (int i = 1; i < N * N + 1; i++) {
      ids[i] = -i;
    }
    ids[N * N + 1] = N * N + 1;
    uf = new WeightedQuickUnionUF(ids.length);
  }

  public void open(int i, int j) {
    if (isOpen(i, j)) return;
    int p = (i - 1) * N + j;
    ids[p] = -ids[p];
    if (i == 1) {
      uf.union(0, p);
    } else if (i == N) {
      uf.union(N * N + 1, p);
    }
    if (i > 1 && isOpen(i - 1, j)) {
      uf.union(p, p - N);
    }
    if (i < N && isOpen(i + 1, j)) {
      uf.union(p, p + N);
    }
    if (j > 1 && isOpen(i, j - 1)) {
      uf.union(p, p - 1);
    }
    if (j < N && isOpen(i, j + 1)) {
      uf.union(p, p + 1);
    }
  }

  public boolean isOpen(int i, int j) {
    if (i < 1 || i > N || j < 1 || j > N) throw new java.lang.IndexOutOfBoundsException("Indices i and j should be integers between 1 and " + N);
    return ids[(i - 1) * N  + j] > 0;
  }

  public boolean isFull(int i, int j) {
    throw new IllegalArgumentException("Not implemented.");
  }   // is site (row i, column j) full?

  public boolean percolates() {
    return uf.connected(0, N * N + 1);
  }
}