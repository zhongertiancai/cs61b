import edu.princeton.cs.algs4.Picture;

import javax.lang.model.util.Elements;
import java.awt.*;

public class SeamCarver {
    private Picture picture;
    private int width;
    private int height;
    public SeamCarver(Picture picture) {
        this.picture = picture;
        width = picture.width();
        height = picture.height();
    }
    public Picture picture() {
        return picture;
    }
    public int width() {
        return width;
    }
    public int height() {
        return height;
    }
    public double energy(int x, int y) {
        if (x < 0 || x > width - 1 || y < 0 || y > height - 1) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        double energy = 0;
        int left = x - 1;
        if (left - 1 <= 0) {
            left += picture.width();
        }
        int up = y - 1;
        if (y <= 0) {
            up = up + picture.height();
        }
        int right = (x + 1) % picture.width();
        int down = (y + 1) % picture.height();
        Color a1 = picture.get(x, right);
        Color a2 = picture.get(x, left);
        energy += (a1.getBlue() - a2.getBlue()) * (a1.getBlue() - a2.getBlue());
        energy += (a1.getGreen() - a2.getGreen()) * (a1.getGreen() - a2.getGreen());
        energy += (a1.getRed() - a2.getRed()) * (a1.getRed() - a2.getRed());
        a1 = picture.get(up, y);
        a2 = picture.get(down, y);
        energy += (a1.getBlue() - a2.getBlue()) * (a1.getBlue() - a2.getBlue());
        energy += (a1.getGreen() - a2.getGreen()) * (a1.getGreen() - a2.getGreen());
        energy += (a1.getRed() - a2.getRed()) * (a1.getRed() - a2.getRed());
        return energy;
    }
    public int[] findHorizontalSeam() {
        double[][] energy = new double[width][height];
        double[][] M = new double[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; i++) {
                energy[i][j] = energy(i, j);
            }
        }
        int[][] prev = new int[width][height];
        for (int i = 0; i < width; i++) {
             M[i][0] = energy[i][0];
             prev[i][0] = i;
        }
        for (int i = 0; i < width; i++) {
            for (int j = 1; j < height; j++) {
                double min = 10000000;
                int minIndex = -1;
                if (min > M[i][j - 1]) {
                    min = M[i][j - 1];
                    minIndex = i;
                }
                if (i != 0) {
                    if (min > M[i - 1][j - 1]) {
                        min = M[i - 1][j - 1];
                        minIndex = i - 1;
                    }
                }
                if (i != width - 1) {
                    if (min > M[i + 1][j - 1]) {
                        min = M[i + 1][j - 1];
                        minIndex = i + 1;
                    }
                }
                M[i][j] = min + energy[i][j];
                prev[i][j] = minIndex;
            }
        }
        int result = 0;
        for (int i = 0; i < width; i++) {
            if (M[height - 1][result] > M[height - 1][i]) {
                result = i;
            }
        }
        int[] resultA = new int[width];
        for (int i = resultA.length - 1; i >= 0; i--) {
            resultA[i] = result;
            result = prev[result][i];
        }
        return resultA;
    }
    public int[] findVerticalSeam() {
        int temp = width;
        width = height;
        height = temp;
        int[] resultV = findHorizontalSeam();
        temp = height;
        height = width;
        width = temp;
        return resultV;
    }
    public void removeHorizontalSeam(int[] seam) {
        return;
    }
    public void removeVerticalSeam(int[] seam) {
        return;
    }
}
