package lesson06;

import java.util.ArrayList;
import java.util.List;

import static lesson06.TreeBuilder.*;
import static java.lang.Math.abs;

class Node {
    public int number;
    public Node leftNode;
    public Node rightNode;
    public Node (int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return number + "";
    }
}

class TreeBuilder {
    public static Node root;
    public static int unbalancedBranches = 0;
    public static List<Node> treeList = new ArrayList<>();
    public static List<Node> leftBranches = new ArrayList<>();
    public static List<Node> rightBranches = new ArrayList<>();
    public static List<String> levels = new ArrayList<>();

    /**
     * Transforms list of node objects to simple list of integers denoting nodes
     * @param objectList list of node objects
     * @return list of integers
     */
    public static List<Integer> objectListToInt(List<Node> objectList) {
        List<Integer> listInt = new ArrayList<>();
        for (Node node : objectList) {
            listInt.add(node.number);
        }
        return listInt;
    }

    /**
     * Creates and adds new node to a tree and also defines the level and adds this node to the level list
     * @param number plain number that defines node
     */
    public static void insertNode(int number) {
        Node node = new Node(number);
        boolean vacant = false;
        int levelCounter = 0;
        if (root == null){
            root = node;
            treeList.add(node);
            levels.add("");
            levels.set(levelCounter, levels.get(levelCounter) + "/" + node.number);
        } else {
            Node parent = root;
            while (!vacant) {
                if (number < parent.number){
                    levelCounter++;
                    if (parent.leftNode == null){
                        parent.leftNode = node;
                        treeList.add(node);
                        leftBranches.add(node);
                        if (levels.size() <= levelCounter) {
                            levels.add("");
                        }
                        levels.set(levelCounter, levels.get(levelCounter) + "/" + node.number);
                        vacant = true;
                    } else {
                        parent = parent.leftNode;
                    }
                } else {
                    levelCounter++;
                    if (parent.rightNode == null){
                        parent.rightNode = node;
                        treeList.add(node);
                        rightBranches.add(node);
                        if (levels.size() <= levelCounter) {
                            levels.add("");
                        }
                        levels.set(levelCounter, levels.get(levelCounter) + "/" + node.number);
                        vacant = true;
                    } else {
                        parent = parent.rightNode;
                    }
                }
            }
        }
    }

    /**
     * Counts levels of a tree and defines if a tree is balanced or not through outside counter
     * @param selectedNode Root node
     * @return number of levels
     */
    public static int levelCounter(Node selectedNode) {
        int leftSideLevels;
        int rightSideLevels;
        if(selectedNode == null) {
            return 0;
        }
        leftSideLevels = levelCounter(selectedNode.leftNode);
        rightSideLevels = levelCounter(selectedNode.rightNode);
        if (abs(leftSideLevels - rightSideLevels) > 1) {
            unbalancedBranches++;
        }
        if (leftSideLevels < rightSideLevels) {
            return rightSideLevels + 1;
        } else {
            return leftSideLevels + 1;
        }
    }
}

public class Tree {
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
        // Random tree mostly unbalanced
        int numbersCounter = 0;
        while (numbersCounter < 10) {
            int randomNumber = random(1, 20);
            if (!objectListToInt(treeList).contains(randomNumber)) {
                numbersCounter++;
                insertNode(randomNumber);
            }
        }

        // Balanced
//        insertNode(10);
//        insertNode(7);
//        insertNode(3);
//        insertNode(4);
//        insertNode(8);
//        insertNode(15);
//        insertNode(13);
//        insertNode(1);
//        insertNode(11);
//        insertNode(17);
//        insertNode(16);
//        insertNode(19);

        System.out.println("Sequence of numbers: " + treeList);
        System.out.println("Left branches: " + leftBranches);
        System.out.println("Right branches: " + rightBranches);
        for (int i = 0; i < levels.size(); i++) {
            System.out.println("Level " + (i + 1) + ": " + levels.get(i));
        }
        System.out.println("The tree has " + levelCounter(root) + " levels");
        if (unbalancedBranches > 0) {
            System.out.println("The tree is unbalanced.");
        } else {
            System.out.println("The tree is balanced.");
        }
    }
}
