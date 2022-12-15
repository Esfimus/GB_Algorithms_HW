package lesson07;

import java.util.LinkedHashMap;
import java.util.Map;

class Node {
    public String name;
    public boolean visited;
    public Node(String name) {
        this.name = name;
        this.visited = false;
    }
}

public class Graph {
    private static Node[] nodeList;
    private static int[][] nodesMatrix;
    private static int graphSize = 0;
    private static int nodesTotal;
    private static int visitedCounter = 0;
    private static final Map<String, Integer> pathMap = new LinkedHashMap<>();

    /**
     * Creates new graph through list of nodes and matrix of connections
     * @param nodesMax maximum nodes
     */
    public static void graph(int nodesMax) {
        nodesTotal = nodesMax;
        nodeList = new Node[nodesTotal];
        nodesMatrix = new int[nodesTotal][nodesTotal];
        for (int i = 0; i < nodesTotal; i++) {
            for (int j = 0; j < nodesTotal; j++) {
                nodesMatrix[i][j] = 0;
            }
        }
    }

    /**
     * Creates new node and adds it to the list of nodes and matrix of connections
     * @param nodeName the name of new node
     */
    public static void addNode(String nodeName) {
        if (graphSize < nodesTotal) {
            nodeList[graphSize] = new Node(nodeName);
            pathMap.put(nodeName, 0);
            graphSize++;
        }
    }

    /**
     * Creates new connection between two nodes with distance (weight)
     * @param node1 name of the first node
     * @param node2 name of the second node
     * @param distance distance or weight of connection between the nodes
     */
    public static void setConnection(String node1, String node2, int distance) {
        for (int i = 0; i < nodesMatrix.length; i++) {
            for (int j = 0; j < nodesMatrix.length; j++) {
                if (
                    nodeList[i].name.equals(node1) && nodeList[j].name.equals(node2) ||
                    nodeList[i].name.equals(node2) && nodeList[j].name.equals(node1)
                ) {
                    nodesMatrix[i][j] = distance;
                }
            }
        }
    }

    /**
     * Shows the matrix of connections with node names
     */
    public static void printMatrix() {
        System.out.println("Matrix of connections between nodes:");
        System.out.print("\t");
        for (Node node : nodeList) {
            System.out.print(node.name + "\t");
        }
        System.out.println();
        for (int i = 0; i < nodesMatrix.length; i++) {
            System.out.print(nodeList[i].name + "\t");
            for (int j = 0; j < nodesMatrix.length; j++) {
                System.out.print(nodesMatrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    /**
     * Shows all the connections between nodes
     */
    public static void printConnections() {
        System.out.println("\nConnections between nodes: ");
        for (int i = 0; i < nodesMatrix.length; i++) {
            for (int j = i; j < nodesMatrix.length; j++) {
                if (nodesMatrix[i][j] != 0) {
                    System.out.println(nodeList[i].name + " <-> " + nodeList[j].name + " : " + nodesMatrix[i][j]);
                }
            }
        }
    }

    /**
     * Calculates the shortest paths to all nodes
     * @param startingNode the name of the starting node
     */
    public static void shortestPath(String startingNode) {
        // detecting the index of starting node
        int startingNodeIndex = -1;
        for (int i = 0; i < nodeList.length; i++) {
            if (nodeList[i].name.equals(startingNode)) {
                startingNodeIndex = i;
            }
        }
        // checking if the input is correct
        if (startingNodeIndex == -1) {
            System.out.println("The node name has not been found");
            return;
        }
        // detecting connections for the starting node and updating values in map
        for (int j = 0; j < nodesMatrix.length; j++) {
            if (nodesMatrix[startingNodeIndex][j] > 0 && !nodeList[j].visited) {
                if (pathMap.get(nodeList[j].name) == 0) {
                    pathMap.put(nodeList[j].name, nodesMatrix[startingNodeIndex][j] + pathMap.get(nodeList[startingNodeIndex].name));
                } else if (pathMap.get(nodeList[j].name) > nodesMatrix[startingNodeIndex][j] + pathMap.get(nodeList[startingNodeIndex].name)) {
                    pathMap.put(nodeList[j].name, nodesMatrix[startingNodeIndex][j] + pathMap.get(nodeList[startingNodeIndex].name));
                }
            }
        }
        // marking starting node as visited
        for (Node node : nodeList) {
            if (node.name.equals(startingNode)) {
                node.visited = true;
                visitedCounter++;
            }
        }
        // searching for the lowest distance (weight) in map for the next recursive step
        String minDistanceNodeName = "";
        for (Node node : nodeList) {
            if (pathMap.get(node.name) > 0 && !node.visited) {
                minDistanceNodeName = node.name;
                break;
            }
        }
        for (Node node : nodeList) {
            if (pathMap.get(node.name) > 0 && !node.visited) {
                if (pathMap.get(node.name) < pathMap.get(minDistanceNodeName)) {
                    minDistanceNodeName = node.name;
                }
            }
        }
        // checking the visited nodes counter to stop further recursive steps
        if (visitedCounter == nodeList.length - 1) {
            return;
        }
        shortestPath(minDistanceNodeName);
    }

    public static void main(String[] args) {
        // creating a graph with 8 nodes
        graph(8);
        addNode("A");
        addNode("B");
        addNode("C");
        addNode("D");
        addNode("E");
        addNode("F");
        addNode("G");
        addNode("H");
        // setting some connections between nodes with distance (weight)
        setConnection("A", "B", 6);
        setConnection("A", "E", 5);
        setConnection("E", "C", 3);
        setConnection("B", "C", 7);
        setConnection("B", "F", 10);
        setConnection("C", "G", 8);
        setConnection("E", "D", 11);
        setConnection("E", "H", 17);
        setConnection("D", "H", 10);
        setConnection("D", "G", 6);
        setConnection("F", "H", 8);
        setConnection("G", "H", 3);
        setConnection("G", "F", 9);
        // printing matrix, all connections and shortest paths to all nodes from the selected one
        printMatrix();
        printConnections();
        shortestPath("H");
        System.out.println("\nShortest paths from the selected node to all other nodes\n" + pathMap);
    }
}