package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private double[] ptr;
    public PercolationStats(int N, int T, PercolationFactory pf){
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        Percolation p;
        ptr = new double[T];
        double count = 0;
        int row, col;
        for (int i = 0; i < T; i++) {
            p = pf.make(N);
            while (p.percolates() == false) {
                row = StdRandom.uniform(N);
                col = StdRandom.uniform(N);
                if (!p.isOpen(row, col)) {
                    p.open(row, col);
                    count += 1;
                }
            }
            ptr[i] = count;
            count = 0;
        }
    }
    public double mean() {
        return StdStats.mean(ptr);
    }
    public double stddev() {
        return StdStats.stddev(ptr);
    }
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(ptr.length);
    }
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(ptr.length);
    }
/*    public void main(String[] args) {
        PercolationFactory f = new PercolationFactory();
        PercolationStats a = new PercolationStats(10,10,f);
        System.out.println(a.mean());
        System.out.println(a.stddev());
        System.out.println(a.confidenceHigh());
        System.out.println(a.confidenceLow());
    }*/
}
