public class Percolation {
  private int n;
  private boolean[] isOpen;
  private WeightedQuickUnionUF uf;
  private WeightedQuickUnionUF fullUf;

  public Percolation(int n) {
    this.n = n;
    isOpen = new boolean[n * n + 2];
    isOpen[0] = true;
    isOpen[n * n + 1] = true;
//    for (int i = 1; i < n * n + 1; i++) {
//      isOpen[i] = false;
//    }
    uf = new WeightedQuickUnionUF(isOpen.length);
    fullUf = new WeightedQuickUnionUF(isOpen.length - 1);
  }

  public void open(int i, int j) {
    if (isOpen(i, j)) return;
    int p = (i - 1) * n + j;
    isOpen[p] = true;
    if (i == 1) {
      uf.union(0, p);
      fullUf.union(0, p);
    }
    if (i == n) uf.union(n * n + 1, p);
    if (i > 1 && isOpen(i - 1, j)) {
      uf.union(p, p - n);
      fullUf.union(p, p - n);
    }
    if (i < n && isOpen(i + 1, j)) {
      uf.union(p, p + n);
      fullUf.union(p, p + n);
    }
    if (j > 1 && isOpen(i, j - 1)) {
      uf.union(p, p - 1);
      fullUf.union(p, p - 1);
    }
    if (j < n && isOpen(i, j + 1)) {
      uf.union(p, p + 1);
      fullUf.union(p, p + 1);
    }
  }

  public boolean isOpen(int i, int j) {
    if (i < 1 || i > n || j < 1 || j > n)
      throw new java.lang.IndexOutOfBoundsException("Indices are out of bounds.");
    return isOpen[(i - 1) * n + j];
  }

  public boolean isFull(int i, int j) {
    return isOpen(i, j) && fullUf.connected(0, (i - 1) * n + j);
  }

  public boolean percolates() {
    return uf.connected(0, n * n + 1);
  }
}