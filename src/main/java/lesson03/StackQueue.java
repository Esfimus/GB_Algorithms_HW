package lesson03;

public class StackQueue {

    public static int missingNumber(int[] array) {
        int value = 1;
        if (array.length > 0) {
            for (int j : array) {
                if (j != value) {
                    return value;
                } else {
                    value++;
                }
            }
            return value;
        } else {
            return 1;
        }
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 5, 6, 7, 8, 9};
        System.out.println(missingNumber(array));
    }
}
