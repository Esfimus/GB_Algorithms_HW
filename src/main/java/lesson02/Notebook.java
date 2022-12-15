package lesson02;

import java.util.ArrayList;
import java.util.List;

class Notebook {

    // Object parameters
    public static int nbNumber = 100;
    private final String brand;
    private final double price;
    private final int ram;
    private final int brandID;

    // Object constructor
    public Notebook (String brand, int brandID, double price, int ram) {
        this.brand = brand;
        this.brandID = brandID;
        this.price = price;
        this.ram = ram;
    }

    // Lists of possible parameters' values
    private static final String[] brandSelection = new String[] {"Lenuvo", "Asos", "MacNote", "Eser", "Xamiou"};
    private static final int[] ramSelection = new int[] {4, 8, 16, 20, 24};
    private static final double[] priceSelection = new double[] {100, 200, 300, 400, 500, 600, 700};

    // Function for object creation with random values
    public static int random (int arrLength) {
        return (int) Math.floor(Math.random() * arrLength);
    }

    // Output function
    @Override
    public String toString() {
        return brand + "\t" + ram + "\t" + price;
    }

    // Basic upper level recursive sorting function
    public static List<Notebook> sortMe1(List<Notebook> array, String direction, String parameter) {
        if (array.size() < 2) {
            return array;
        } else {
            List<Notebook> midValue = new ArrayList<>();
            midValue.add(array.get(0));
            List<Notebook> lessList = new ArrayList<>();
            List<Notebook> greaterList = new ArrayList<>();
            switch (parameter) {
                case "price":
                    for (int i = 1; i < array.size(); i++) {
                        if (direction.equals(">")) {
                            if (array.get(i).price >= midValue.get(0).price) {
                                greaterList.add(array.get(i));
                            } else {
                                lessList.add(array.get(i));
                            }
                        } else if (direction.equals("<")) {
                            if (array.get(i).price < midValue.get(0).price) {
                                greaterList.add(array.get(i));
                            } else {
                                lessList.add(array.get(i));
                            }
                        } else {
                            System.out.println("Wrong direction, sorting operation has not been completed.");
                            return array;
                        }
                    }
                    break;
                case "ram":
                    for (int i = 1; i < array.size(); i++) {
                        if (direction.equals(">")) {
                            if (array.get(i).ram >= midValue.get(0).ram) {
                                greaterList.add(array.get(i));
                            } else {
                                lessList.add(array.get(i));
                            }
                        } else if (direction.equals("<")) {
                            if (array.get(i).ram < midValue.get(0).ram) {
                                greaterList.add(array.get(i));
                            } else {
                                lessList.add(array.get(i));
                            }
                        } else {
                            System.out.println("Wrong direction, sorting operation has not been completed.");
                            return array;
                        }
                    }
                    break;
                case "brand":
                    for (int i = 1; i < array.size(); i++) {
                        if (direction.equals(">")) {
                            if (array.get(i).brandID >= midValue.get(0).brandID) {
                                greaterList.add(array.get(i));
                            } else {
                                lessList.add(array.get(i));
                            }
                        } else if (direction.equals("<")) {
                            if (array.get(i).brandID < midValue.get(0).brandID) {
                                greaterList.add(array.get(i));
                            } else {
                                lessList.add(array.get(i));
                            }
                        } else {
                            System.out.println("Wrong direction, sorting operation has not been completed.");
                            return array;
                        }
                    }
                    break;
                default:
                    System.out.println("Wrong parameter, sorting operation has not been completed.");
                    return array;
            }
            List<Notebook> sorted = new ArrayList<>();
            sorted.addAll(sortMe1(lessList, direction, parameter));
            sorted.addAll(midValue);
            sorted.addAll(sortMe1(greaterList, direction, parameter));
            return sorted;
        }
    }

    // Second level sorting function
    public static List<Notebook> sortMe2(List<Notebook> array, String direction, String parameter) {
        List<Notebook> sorted2 = new ArrayList<>();
        List<Notebook> currentSort = new ArrayList<>();
        if (parameter.equals("ram") && (direction.equals(">") || direction.equals("<"))) {
            for (int j = 1; j < array.size(); j++) {
                if (array.get(j).price != array.get(j - 1).price) {
                    currentSort.add(array.get(j - 1));
                    if (currentSort.contains(array.get(j - 1))) {
                        sorted2.addAll(sortMe1(currentSort, direction, parameter));
                        currentSort.clear();
                    } else {
                        sorted2.addAll(currentSort);
                        currentSort.clear();
                    }
                } else {
                    currentSort.add(array.get(j - 1));
                }
            }
            currentSort.add(array.get(array.size() - 1));
            sorted2.addAll(sortMe1(currentSort, direction, parameter));
            return sorted2;
        } else if (parameter.equals("price") && (direction.equals(">") || direction.equals("<"))) {
            for (int j = 1; j < array.size(); j++) {
                if (array.get(j).ram != array.get(j - 1).ram) {
                    currentSort.add(array.get(j - 1));
                    if (currentSort.contains(array.get(j - 1))) {
                        sorted2.addAll(sortMe1(currentSort, direction, parameter));
                        currentSort.clear();
                    } else {
                        sorted2.addAll(currentSort);
                        currentSort.clear();
                    }
                } else {
                    currentSort.add(array.get(j - 1));
                }
            }
            currentSort.add(array.get(array.size() - 1));
            sorted2.addAll(sortMe1(currentSort, direction, parameter));
            return sorted2;
        } else if (parameter.equals("brand") && (direction.equals(">") || direction.equals("<"))) {
            for (int j = 1; j < array.size(); j++) {
                if (array.get(j).ram != array.get(j - 1).ram) {
                    currentSort.add(array.get(j - 1));
                    if (currentSort.contains(array.get(j - 1))) {
                        sorted2.addAll(sortMe1(currentSort, direction, parameter));
                        currentSort.clear();
                    } else {
                        sorted2.addAll(currentSort);
                        currentSort.clear();
                    }
                } else {
                    currentSort.add(array.get(j - 1));
                }
            }
            currentSort.add(array.get(array.size() - 1));
            sorted2.addAll(sortMe1(currentSort, direction, parameter));
            return sorted2;
        } else {
            System.out.println("Wrong parameter or direction, sorting operation has not been completed.");
            return array;
        }
    }

    public static void main(String[] args) {
        // Creating random objects
        List<Notebook> nb = new ArrayList<>();
        for (int i = 0; i < nbNumber; i++) {
            String nbBrand = brandSelection[random(brandSelection.length)];
            int nbBrandID = switch (nbBrand) {
                case "Lenuvo" -> 1;
                case "Asos" -> 2;
                case "MacNote" -> 3;
                case "Eser" -> 4;
                case "Xamiou" -> 5;
                default -> 0;
            };
            double nbPrice = priceSelection[random(priceSelection.length)];
            int nbRam = ramSelection[random(ramSelection.length)];
            nb.add(new Notebook(nbBrand, nbBrandID, nbPrice, nbRam));
        }
        // Sorting objects with preferences
        List<Notebook> nbSorted1 = sortMe1(nb, ">", "price");
        List<Notebook> nbSorted2 = sortMe2(nbSorted1, "<", "ram");
        List<Notebook> nbSorted3 = sortMe2(nbSorted2, ">", "brand");
        // Final output
        System.out.println("\nUnsorted objects:");
        System.out.println(nb);
        System.out.println("\nSorted objects (1 parameter):");
        System.out.println(nbSorted1);
        System.out.println("\nSorted objects (2 parameters):");
        System.out.println(nbSorted2);
        System.out.println("\nSorted objects (3 parameters):");
        System.out.println(nbSorted3);
    }
}