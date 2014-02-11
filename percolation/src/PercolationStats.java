/**
 * Created by Kafka Liu on 14-2-9.
 */
public class PercolationStats {

  private double[] stats;

  private int t;

  public PercolationStats(int n, int t) {
    if (n <= 0 || t <= 0) {
      throw new IllegalArgumentException("Both n and t should be greater than 0.");
    }
    this.t = t;
    stats = new double[t];
    for (int i = 0; i < t; i++) {
      StdRandom.setSeed(System.currentTimeMillis());
      Percolation percolation = new Percolation(n);
      int count = 0;
      while (!percolation.percolates()) {
        int row = StdRandom.uniform(1, n + 1);
        int column = StdRandom.uniform(1, n + 1);
        if (!percolation.isOpen(row, column)) {
          count++;
          percolation.open(row, column);
        }
      }
      stats[i] = count * 1.0 / (n * n);
    }
  }

  public double mean() {
    return StdStats.mean(stats);
  }

  public double stddev() {
    return StdStats.stddev(stats);
  }

  public double confidenceLo() {
    return mean() - 1.96 * stddev() / Math.sqrt(t);
  }

  public double confidenceHi() {
    return mean() + 1.96 * stddev() / Math.sqrt(t);
  }

  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);
    int t = Integer.parseInt(args[1]);
    PercolationStats stats = new PercolationStats(n, t);
    StdOut.printf("%-23s = %.16f\n", "mean", stats.mean());
    StdOut.printf("%-23s = %.16f\n", "stddev", stats.stddev());
    StdOut.printf("%-23s = %.16f, %.16f", "95% confidence interval",
        stats.confidenceLo(), stats.confidenceHi());
  }  // test client, described below
}
