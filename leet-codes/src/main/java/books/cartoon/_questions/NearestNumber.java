package books.cartoon._questions;

import java.util.Arrays;

public class NearestNumber {

    public static int[] findNearestNumber(int[] numbers) {
        //1.从后向前查找逆序区前一位:置换边界
        int index = findTransferPoint(numbers);
        if (index == 0) {
            return null;
        }
        int[] numbersCopy = Arrays.copyOf(numbers, numbers.length);
        //2.交换位置
        exchangeHead(numbersCopy, index);
        //3.逆序区转为顺序
        reverse(numbersCopy, index);
        return numbersCopy;
    }

    private static int findTransferPoint(int[] numbers) {
        for (int i = numbers.length - 1; i > 0; i--) {
            if (numbers[i] > numbers[i - 1]) {
                return i;
            }
        }
        return 0;
    }

    private static int[] exchangeHead(int[] numbers, int index) {
        int head = numbers[index - 1];
        for (int i = numbers.length - 1; i > 0; i--) {
            if (head < numbers[i]) {
                numbers[index - 1] = numbers[i];
                numbers[i] = head;
                break;
            }
        }
        return numbers;
    }

    private static int[] reverse(int[] numbers, int index) {

        for (int i = index, j = numbers.length - 1; i < j; i++, j--) {
            int tmp = numbers[i];
            numbers[i] = numbers[j];
            numbers[j] = tmp;
        }

        return numbers;
    }

    public static void main(String[] args) {

        int[] numbers = {1,2,3,4,5};

        for(int i =0; i <10; i++){
            numbers = findNearestNumber(numbers);
            System.out.println(Arrays.toString(numbers));
        }
    }
}
