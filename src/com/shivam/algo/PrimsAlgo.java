package com.shivam.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class PrimsAlgo {

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    String inputDetails = bufferedReader.readLine();
    int numberOfNodes = Integer.parseInt(inputDetails.split(" ")[0]);
    int numberOfEdges = Integer.parseInt(inputDetails.split(" ")[1]);

    int[][] edgeList = new int[numberOfEdges][3];
    edgeList = processInputData(edgeList, numberOfEdges, bufferedReader);
    int startNodeValue = Integer.parseInt(bufferedReader.readLine());
    int minimumWeight = primsAlgoOutput(edgeList, numberOfNodes, numberOfEdges, startNodeValue);
    displayMessage(minimumWeight + "");

  }

  private static int[][] processInputData(int[][] edgeList, int numberOfEdges,
      BufferedReader bufferedReader)
      throws IOException {
    for (int i = 0; i < numberOfEdges; i++) {
      String[] inputEdgeList = bufferedReader.readLine().split(" ");
      for (int j = 0; j < inputEdgeList.length; j++) {
        edgeList[i][j] = Integer.parseInt(inputEdgeList[j]);
      }
    }
    return edgeList;
  }

  private static void displayMessage(String message) {
    System.out.println(message);
  }

  private static int primsAlgoOutput(int[][] edgeList, int numberOfNodes, int numberOfEdges,
      int startNode) {
    List<List<Node>> adjacencyList = adjacencyList(edgeList, numberOfNodes, numberOfEdges);

    return minimumWeight(adjacencyList, startNode, numberOfNodes);
  }

  private static int minimumWeight(List<List<Node>> adjacencyList, int startNode,
      int numberOfNodes) {
    int[] visitedNode = new int[numberOfNodes+1];
    int minimumWeight = 0;
    PriorityQueue<Node> minWeightQueue = new PriorityQueue<>();
    int currentNodeValue = startNode-1;
    visitedNode[currentNodeValue] = 1;
    while (currentNodeValue >= 0) {
      List<Node> connectedNode = adjacencyList.get(currentNodeValue);
      for (Node node : connectedNode) {
        minWeightQueue.add(node);
      }

      Node currentNode = minWeightQueue.poll();
      while (currentNode != null && visitedNode[currentNode.nodeNumber-1] == 1) {
        currentNode = minWeightQueue.poll();
      }
      if (currentNode == null) {
        currentNodeValue = -1;
      } else {
        currentNodeValue = currentNode.nodeNumber-1;
        minimumWeight = minimumWeight + currentNode.weight;
        visitedNode[currentNodeValue] = 1;
      }


    }

    return minimumWeight;
  }

  private static List<List<Node>> adjacencyList(int[][] edgeList, int numberOfNodes,
      int numberOfEdges) {
    List<List<Node>> adjacencyList = new ArrayList<>();
    for (int i = 0; i < numberOfNodes; i++) {
      adjacencyList.add(new ArrayList<>());
    }
    for (int i = 0; i < numberOfEdges; i++) {
      int[] edgeDetails = edgeList[i];
      int startValue = edgeDetails[0];
      int endValue = edgeDetails[1];
      int weight = edgeDetails[2];
      Node nodeStart = new Node(startValue, weight);
      Node nodeEnd = new Node(endValue, weight);
      adjacencyList.get(nodeEnd.nodeNumber - 1).add(nodeStart);
      adjacencyList.get(nodeStart.nodeNumber - 1).add(nodeEnd);
    }

    return adjacencyList;

  }

}

class Node implements Comparable<Node> {

  int weight;
  int nodeNumber;

  Node(int nodeNumber, int weight) {
    this.weight = weight;
    this.nodeNumber = nodeNumber;
  }

//  @Override
//  public int compare(Node o1, Node o2) {
//    if (o1.weight < o2.weight) {
//      return 1;
//    } else if (o1.weight == o2.weight) {
//      return 0;
//    } else {
//      return -1;
//    }
//  }

  @Override
  public int compareTo(Node o) {
    if (this.weight < o.weight) {
      return -1;
    } else if (this.weight == o.weight) {
      return 0;
    } else {
      return 1;
    }
  }
}
