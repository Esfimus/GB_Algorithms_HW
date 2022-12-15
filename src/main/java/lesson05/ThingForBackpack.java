package lesson05;

import java.util.ArrayList;
import java.util.List;

public class ThingForBackpack {
    public static int maxWeight = 10;
    public static int numberOfThings = 10;
    private final int weight;
    private final int importance;

    public ThingForBackpack (int weight, int importance) {
        this.weight = weight;
        this.importance = importance;
    }

    /**
     * Finds the number of objects with max importance withing limited weight using dynamic programming
     * @param array Objects with importance and weight values
     * @param maxWeight Limiting weight
     * @return Max importance value from the matrix of objects x weights
     */
    public static int fullBackpack(List<ThingForBackpack> array, int maxWeight) {
        int divider = 10;
        int weightCell = maxWeight / divider;
        int[][] weightCollection = new int[array.size()][divider + 1];
        String[][] thingsInBackpack = new String[array.size()][divider + 1];
        for (int i = 0; i < array.size(); i++) {
            for (int j = 0; j <= divider; j++) {
                if (i == 0) {
                    if (array.get(i).weight <= weightCell * j) {
                        weightCollection[i][j] = array.get(i).importance;
                        thingsInBackpack[i][j] = array.get(i).toString();
                    } else {
                        weightCollection[i][j] = 0;
                        thingsInBackpack[i][j] = "";
                    }
                } else {
                    if (array.get(i).weight <= weightCell * j) {
                        int freeWeight = weightCell * j - array.get(i).weight;
                        if (array.get(i).importance + weightCollection[i - 1][freeWeight] > weightCollection[i - 1][j]) {
                            weightCollection[i][j] = array.get(i).importance + weightCollection[i - 1][freeWeight];
                            thingsInBackpack[i][j] = thingsInBackpack[i - 1][freeWeight] + "/" + array.get(i).toString();
                        } else {
                            weightCollection[i][j] = weightCollection[i - 1][j];
                            thingsInBackpack[i][j] = thingsInBackpack[i - 1][j];
                        }
                    } else {
                        weightCollection[i][j] = weightCollection[i - 1][j];
                        thingsInBackpack[i][j] = thingsInBackpack[i - 1][j];
                    }
                }
            }
        }
        System.out.println("Most important things in our backpack: " + (thingsInBackpack[thingsInBackpack.length - 1][maxWeight]).replace(" ",""));
        return weightCollection[weightCollection.length - 1][maxWeight];
    }

    @Override
    public String toString() {
        return weight + "-" + importance;
    }

    /**
     * Creates random value between min and max
     * @param min minimum random value
     * @param max maximum random value
     * @return random value
     */
    public static int random (int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    public static void main(String[] args) {
        List<ThingForBackpack> things = new ArrayList<>();
        for (int i = 0; i < numberOfThings; i++) {
            things.add(new ThingForBackpack(random(3, 9), random(5, 15) * 100));
        }
        System.out.println("Maximum possible importance in our backpack: " + fullBackpack(things, maxWeight));
        System.out.println("\nThings to pack (weight-importance):");
        for (ThingForBackpack thing : things) {
            System.out.println(thing);
        }
    }
}


