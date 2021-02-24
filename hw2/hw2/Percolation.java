package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
// 0 -- has not open  1 -- open
public class Percolation {
    private int[] box;
    private int length;
    private int openSites;
    private WeightedQuickUnionUF myDisjoint;
    private WeightedQuickUnionUF myDisjoint2;
    public Percolation(int n) {
        box = new int[n * n];
        for (int i = 0; i < box.length; i++) {
            box[i] = 0;
        }
        length = n;
        openSites = 0;
        myDisjoint = new WeightedQuickUnionUF(n * n + 2);
        myDisjoint2 = new WeightedQuickUnionUF(n * n + 2);
    }
    private int index(int row, int col) {
        return row * length + col;
    }
    public void open(int row, int col) {
        if (box[index(row, col)] == 0) {
            box[index(row, col)] = 1;
            openSites += 1;
        }
        if (row == 0) {
            myDisjoint.union(index(row, col), length * length);
            myDisjoint2.union(index(row, col), length * length);
        }
        if (row == length - 1) {
            myDisjoint.union(index(row, col), length * length + 1);
        }
        if (col != 0 && isOpen(row, col - 1)) {
            myDisjoint2.union(index(row, col), index(row, col - 1));
            myDisjoint.union(index(row, col), index(row, col - 1));
        }
        if (col != length - 1 && isOpen(row, col + 1)) {
            myDisjoint2.union(index(row, col), index(row, col + 1));
            myDisjoint.union(index(row, col), index(row, col + 1));
        }
        if (row != 0 && isOpen(row - 1, col)) {
            myDisjoint2.union(index(row, col), index(row - 1, col));
            myDisjoint.union(index(row, col), index(row - 1, col));
        }
        if (row != length - 1 && isOpen(row + 1, col)) {
            myDisjoint2.union(index(row, col), index(row + 1, col));
            myDisjoint.union(index(row, col), index(row + 1, col));
        }
    }
    public boolean isOpen(int row, int col) {
        if (box[index(row, col)] == 1){
            return true;
        } else {
            return false;
        }
    }
    public boolean isFull(int row, int col) {
        if (myDisjoint2.connected(index(row, col), length * length) == true){
            return true;
        }
        return false;
    }
    public boolean percolates() {
        if (myDisjoint.connected(length * length, length * length + 1)) {
            return true;
        } else {
            return false;
        }
    }
    public int numberOfOpenSites() {
        return openSites;
    }
    public static void main(String[] args) {
        PercolationFactory f = new PercolationFactory();
        PercolationStats a = new PercolationStats(20,10,f);
        System.out.println(a.mean());
        System.out.println(a.stddev());
        System.out.println(a.confidenceHigh());
        System.out.println(a.confidenceLow());
    }
}
