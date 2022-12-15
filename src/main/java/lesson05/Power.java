package lesson05;

public class Power {

    public static int powerOf (int n, int power) {
        if (power == 0) {
            return 1;
        } else {
            return n * powerOf(n, power - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(powerOf(5, 6));
    }
}
