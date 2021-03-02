import edu.princeton.cs.algs4.Picture;

import javax.lang.model.util.Elements;
import java.awt.*;

public class SeamCarver {
    private Picture picture;
    private int width;
    private int height;
    public SeamCarver(Picture picture) {
        this.picture = new Picture(picture);
        width = picture.width();
        height = picture.height();
        calEnergy();
    }
    public Picture picture() {
        return new Picture(picture);
    }
    public int width() {
        return width;
    }
    public int height() {
        return height;
    }
    private boolean v = true;
    public double energy(int x, int y) {
        Color up, down, left, right;
        up = y > 0 ? picture.get(x, y - 1) : picture.get(x, height - 1);
        down = y < height - 1 ? picture.get(x, y + 1) : picture.get(x, 0);
        left = x > 0 ? picture.get(x - 1, y) : picture.get(width - 1, y);
        right = x < width - 1 ? picture.get(x + 1, y) : picture.get(0, y);
        int rx = left.getRed() - right.getRed();
        int gx = left.getGreen() - right.getGreen();
        int bx = left.getBlue() - right.getBlue();
        int ry = up.getRed() - down.getRed();
        int gy = up.getGreen() - down.getGreen();
        int by = up.getBlue() - down.getBlue();
        return rx * rx + gx * gx + bx * bx + ry * ry + gy * gy + by * by;
    }
    private double[][] energy;
    private void calEnergy() {
        energy = new double[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                energy[i][j] = energy(i, j);
            }
        }
    }
    public int[] findVerticalSeam() {
        calEnergy();
        double[][] M = new double[width][height];
        int[][] prev = new int[width][height];
        for (int i = 0; i < width; i++) {
            M[i][0] = energy[i][0];
            prev[i][0] = i;
        }
        for (int i = 1; i < height; i++) {
            for (int j = 0; j < width; j++) {
                double min = 10000000;
                int minIndex = -1;
                if (min > M[j][i - 1]) {
                    min = M[j][i - 1];
                    minIndex = j;
                }
                if (j != 0) {
                    if (min > M[j - 1][i - 1]) {
                        min = M[j - 1][i - 1];
                        minIndex = j - 1;
                    }
                }
                if (j != width - 1) {
                    if (min > M[j + 1][i - 1]) {
                        min = M[j + 1][i - 1];
                        minIndex = j + 1;
                    }
                }
                M[j][i] = min + energy[j][i];
                prev[j][i] = minIndex;
            }
        }
        int result = 0;
        for (int i = 0; i < width; i++) {
            if (M[result][height - 1] > M[i][height - 1]) {
                result = i;
            }
        }
        int[] resultA = new int[height];
        for (int i = resultA.length - 1; i >= 0; i--) {
            resultA[i] = result;
            result = prev[result][i];
        }
        return resultA;
    }


    public int[] findHorizontalSeam() {
        calEnergy();
        double[][] M = new double[width][height];
        int[][] prev = new int[width][height];
        for (int i = 0; i < height; i++) {
            M[0][i] = energy[0][i];
            prev[0][i] = i;
        }
        for (int i = 1; i < width; i++) {
            for (int j = 0; j < height; j++) {
                double min = 10000000;
                int minIndex = -1;
                if (min > M[i - 1][j]) {
                    min = M[i - 1][j];
                    minIndex = j;
                }
                if (j != 0) {
                    if (min > M[i - 1][j - 1]) {
                        min = M[i - 1][j - 1];
                        minIndex = j - 1;
                    }
                }
                if (j != height - 1) {
                    if (min > M[i - 1][j + 1]) {
                        min = M[i - 1][j + 1];
                        minIndex = j + 1;
                    }
                }
                M[i][j] = min + energy[i][j];
                prev[i][j] = minIndex;
            }
        }
        int result = 0;
        for (int i = 0; i < height; i++) {
            if (M[width - 1][result] > M[width - 1][i]) {
                result = i;
            }
        }
        int[] resultA = new int[width];
        for (int i = resultA.length - 1; i >= 0; i--) {
            resultA[i] = result;
            result = prev[i][result];
        }
        return resultA;
    }
    public void removeHorizontalSeam(int[] seam) {
        if (seam.length != width) {
            throw new java.lang.IllegalArgumentException();
        }
        picture = SeamRemover.removeHorizontalSeam(picture, seam);
        height--;
    }
    public void removeVerticalSeam(int[] seam) {
        if (seam.length != height) {
            throw new java.lang.IllegalArgumentException();
        }
        picture = SeamRemover.removeVerticalSeam(picture, seam);
        width--;
    }
}
