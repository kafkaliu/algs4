/**
 * Created by Kafka Liu on 14-2-9.
 */
public class PercolationStats {

  public PercolationStats(int n, int t) {
    if (n <= 0 || t <= 0) {
      throw new IllegalArgumentException("Both n and t should be greater than 0.");
    }
    for (int i = 0; i < t; i++) {
      Percolation percolation = new Percolation(n);
      
    }
    throw new IllegalArgumentException("Not implemented.");
  }   // perform T independent computational experiments on an N-by-N grid
  public double mean() {
    throw new IllegalArgumentException("Not implemented.");
  }                    // sample mean of percolation threshold
  public double stddev() {
    throw new IllegalArgumentException("Not implemented.");
  }                  // sample standard deviation of percolation threshold
  public double confidenceLo() {
    throw new IllegalArgumentException("Not implemented.");
  }            // returns lower bound of the 95% confidence interval
  public double confidenceHi() {
    throw new IllegalArgumentException("Not implemented.");
  }            // returns upper bound of the 95% confidence interval
  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);
    int t = Integer.parseInt(args[1]);
    PercolationStats stats = new PercolationStats(n, t);
    StdOut.printf("%-23s = %.16f\n", "mean", stats.mean());
    StdOut.printf("%-23s = %.16f\n", "stddev", stats.stddev());
    StdOut.printf("%-23s = %.16f, %.16f", "95% confidence interval", stats.confidenceLo(), stats.confidenceHi());
  }  // test client, described below
}
