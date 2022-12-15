package lesson03;

import java.util.Arrays;

public class Deque {

    public static int [] insertRight(int[] array, int number) {
        int[] arrayInsR = new int[array.length + 1];
        for (int i = 0; i < array.length; i++) {
            arrayInsR[i] = array[i];
        }
        arrayInsR[array.length] = number;
        return arrayInsR;
    }

    public static int [] deleteRight(int[] array) {
        if (array.length > 0) {
            int[] arrayDelR = new int[array.length - 1];
            for (int i = 0; i < array.length - 1; i++) {
                arrayDelR[i] = array[i];
            }
            return arrayDelR;
        } else {
            return array;
        }
    }

    public static int [] insertLeft(int[] array, int number) {
        int[] arrayInsR = new int[array.length + 1];
        arrayInsR[0] = number;
        for (int i = 1; i < array.length + 1; i++) {
            arrayInsR[i] = array[i - 1];
        }
        return arrayInsR;
    }

    public static int [] deleteLeft(int[] array) {
        if (array.length > 0) {
            int[] arrayDelR = new int[array.length - 1];
            for (int i = 0; i < array.length - 1; i++) {
                arrayDelR[i] = array[i + 1];
            }
            return arrayDelR;
        } else {
            return array;
        }
    }

    public static void main(String[] args) {
        int[] array = {5, 6, 7, 8};
        System.out.println("Original array: " + Arrays.toString(array));
        array = insertRight(array, 9);
        System.out.println("+ Right: " + Arrays.toString(array));
        array = insertRight(array, 10);
        System.out.println("+ Right: " + Arrays.toString(array));
        array = insertLeft(array, 4);
        System.out.println("+ Left: " + Arrays.toString(array));
        array = insertLeft(array, 3);
        System.out.println("+ Left: " + Arrays.toString(array));
        array = deleteRight(array);
        System.out.println("- Right: " + Arrays.toString(array));
        array = deleteLeft(array);
        System.out.println("- Left: " + Arrays.toString(array));
    }
}
