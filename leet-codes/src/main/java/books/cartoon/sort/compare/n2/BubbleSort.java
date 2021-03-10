package books.cartoon.sort.compare.n2;

import java.util.Arrays;

public class BubbleSort {

    public static void sort_version_1(int[] array) {

        int len = array.length;

        for (int i = 0; i < len - 1; i++) {

            for (int j = 0; j < len - i - 1; j++) {

                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }

            }

        }

    }

    public static void sort_version_2(int[] array) {
        int len = array.length;

        for (int i = 0; i < len - 1; i++) {

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

        }
    }

    /**
     * 不通用
     *
     * @param array
     */
    public static void sort_version_3(int[] array) {

        int len = array.length;

        for (int i = 0; i < len - 1; i++) {

            boolean isSorted = true;
            int sortBoarder = len - 1;
            for (int j = 0; j < sortBoarder; j++) {

                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    isSorted = false;
                    sortBoarder = j;
                }

            }

            if (isSorted) {
                break;
            }

        }

    }

    public static void main(String[] args) {
        int[] array = new int[]{5, 8, 6, 3, 9, 2, 1, 7};
        sort_version_1(array);
        System.out.println(Arrays.toString(array));

        array = new int[]{5, 8, 6, 3, 9, 2, 1, 7};
        sort_version_2(array);
        System.out.println(Arrays.toString(array));

        array = new int[]{3, 4, 2, 1, 5, 6, 7, 8};
        sort_version_3(array);
        System.out.println(Arrays.toString(array));

    }
}
