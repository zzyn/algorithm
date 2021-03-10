package books.cartoon.sort.compare.n2;

import java.util.Arrays;

public class CocktailSort {

    public static void sort(int[] array) {
        int len = array.length;

        for (int i = 0; i < len / 2; i++) {

            boolean isSorted = true;
            for (int j = 0; j < len - i - 1; j++) {

                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    isSorted = false;
                }

            }

            if (isSorted) {
                break;
            }

            isSorted = true;
            for (int j = len - i - 1; j > i; j--) {

                if (array[j] < array[j - 1]) {
                    int tmp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = tmp;
                    isSorted = false;
                }
            }

            if (isSorted) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{2, 3, 4, 5, 6, 7, 8, 1};
        sort(array);
        System.out.println(Arrays.toString(array));

        array = new int[]{5, 8, 6, 3, 9, 2, 1, 7};
        sort(array);
        System.out.println(Arrays.toString(array));

        array = new int[]{3, 4, 2, 1, 5, 6, 7, 8};
        sort(array);
        System.out.println(Arrays.toString(array));
    }
}
