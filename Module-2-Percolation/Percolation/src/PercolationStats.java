import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private final int n;
    private final int trails;
    private double [] fraction;
    private static final double confidence_95 = 1.96;
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials){
        this.n = n;
        this.trails = trials;
        fraction= new double[trials];
        if (n <= 0 || trials <= 0) {
            throw new java.lang.IllegalArgumentException("n or trails <=0");
        }
        // Generate number of percolation "t" times (trails)
        for (int i = 0; i < trials; i++) {
           int openSites=0;
            // generate value of percolation for trial "t"
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                int row = StdRandom.uniformInt(n) + 1;
                int col = StdRandom.uniformInt(n) + 1;
                if (!percolation.isOpen(row, col)) {
                    percolation.open(row, col);
                    openSites++;
                }
            }
            // store value of probability "p" to calculate the mean of "t" trials
            fraction[i] = (openSites * 1.0 / (n * n));
        }

    }



    public double mean() {
        return StdStats.mean(fraction);
    }


    public double stddev() {
        return StdStats.stddev(fraction);
    }


    public double confidenceLo() {
        return mean() - confidence_95 * stddev() / Math.sqrt(fraction.length);
    }


    public double confidenceHi() {
        return mean() + confidence_95 * stddev() / Math.sqrt(fraction.length);
    }


    public static void main(String[] args) {

        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, trials);

        StdOut.println("mean       = " + stats.mean());
        StdOut.println("stddev     = " + stats.stddev());
        StdOut.println("95% confidence interval = ["
                + stats.confidenceLo() + ", "
                + stats.confidenceHi()+ "]");
    }

}
