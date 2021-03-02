/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        String[] sorted = new String[asciis.length];
        int maxLen = Integer.MIN_VALUE;
        for (int i = 0; i < asciis.length; i++) {
            sorted[i] = asciis[i];
            if (asciis[i].length() > maxLen) {
                maxLen = asciis[i].length();
            }
        }
        for (int i = maxLen - 1; i >= 0; i--) {
            sortHelperLSD(sorted, i);
        }
        return sorted;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort
        String[] asciis1 = new String[asciis.length];
        for (int i = 0; i < asciis.length; i++) {
            asciis1[i] = asciis[i];
        }
        int[] counts = new int[256];
        int item;
        for (int i = 0; i < asciis.length; i++) {
            if (asciis[i].length() - 1 < index) {
                item = 0;
            } else {
                item = asciis[i].charAt(index);
            }
            counts[item]++;
        }
        int pos = 0;
        int[] starts = new int[256];
        for (int i = 0; i < 256; i++) {
            starts[i] = pos;
            pos += counts[i];
        }
        for (int i = 0; i < asciis1.length; i++) {
            String s = asciis1[i];
            if (asciis1[i].length() - 1 < index) {
                item = 0;
            } else {
                item = asciis1[i].charAt(index);
            }
            int place = starts[item];
            asciis[place] = s;
            starts[item]++;
        }
        return;
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }
    public static void main(String[] arg) {
        String[] s = new String[10];
        String[] s2;
        s[0] = new String("219");
        s[1] = new String("134");
        s[2] = new String("207");
        s[3] = new String("135");
        s[4] = new String("124");
        s[5] = new String("146");
        s[6] = new String("160");
        s[7] = new String("61");
        s[8] = new String("96");
        s[9] = new String("148");
        s2 = sort(s);
        for (String k : s2) {
            System.out.println(k);
        }
        return;
    }
}
