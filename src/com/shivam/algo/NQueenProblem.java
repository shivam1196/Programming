package com.shivam.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NQueenProblem {

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    System.out.println("Enter the number of queens");

    int boardSize = Integer.parseInt(bufferedReader.readLine());

    TreeNode rootTreeNode = createNode(0);

    List<Integer> childListValues = createChildList(boardSize);

    rootTreeNode = createChild(childListValues,rootTreeNode);

    int numberOfPossibleSolution = nQueenSolution(rootTreeNode,childListValues);
    System.out.println(rootTreeNode.toString());



  }


  public static TreeNode createNode(int value){
    return new TreeNode(value);
  }

  public static int nQueenSolution(TreeNode rootTreeNode,List<Integer> childListValues){
    int numberOfSolution = 0;
    List<Integer> numberOfPlaceholders = new ArrayList<>(rootTreeNode.getChildValues().keySet());
    List<Integer> unvistedPlaceholders = new ArrayList<>(numberOfPlaceholders);
    while (!unvistedPlaceholders.isEmpty()){
        TreeNode currentNode = rootTreeNode.getChildValues().get(unvistedPlaceholders.get(0));
        List<Integer> eligiblePlaceholders = new ArrayList<>(numberOfPlaceholders);
        eligiblePlaceholders.remove(Integer.valueOf(currentNode.nodeValue));
        unvistedPlaceholders.remove(Integer.valueOf(currentNode.nodeValue));
        while (!eligiblePlaceholders.isEmpty()) {
          int nextPlaceHolderValue = findNextPlaceholderValue(eligiblePlaceholders,
              currentNode.nodeValue);
          if (nextPlaceHolderValue == -1) {
            break;
          }
          TreeNode nextTreeNode = new TreeNode(nextPlaceHolderValue);
          HashMap<Integer, TreeNode> childTreeNodes = new HashMap<>();
          childTreeNodes.put(nextPlaceHolderValue, nextTreeNode);
          currentNode.setChildValues(childTreeNodes);
          currentNode = nextTreeNode;
          eligiblePlaceholders.remove(Integer.valueOf(currentNode.nodeValue));
        }
        if(eligiblePlaceholders.isEmpty()) {
          numberOfSolution++;
        }

    }

    return numberOfSolution;
  }

  public static int findNextPlaceholderValue(List<Integer> eligiblePlaceholdersList, int currentPlaceholders){
        for (int i =0; i< eligiblePlaceholdersList.size();i++){
          if (Math.abs(eligiblePlaceholdersList.get(i)-currentPlaceholders) > 1 ){
            return eligiblePlaceholdersList.get(i);
          }
        }
        return -1;
  }

  public static TreeNode createChild(List<Integer> childValues,TreeNode rootNode){
    HashMap<Integer,TreeNode> childHashMap = new HashMap<>();
    for (int i = 0 ;i < childValues.size();i++){
      TreeNode node = new TreeNode(childValues.get(i));
      childHashMap.put(i+1,node);
    }
    rootNode.setChildValues(childHashMap);
    return rootNode;
  }

  public static List<Integer> createChildList(int boardSize){
    List<Integer> childList = new ArrayList<>();
    for (int i =0 ;i <boardSize;i++){
      childList.add(i,i+1);
    }
    return childList;
  }



}

class TreeNode {
  int nodeValue;
  HashMap<Integer,TreeNode> childValues ;

  public  TreeNode(int nodeValue){
    this.nodeValue = nodeValue;
  }

  public int getNodeValue() {
    return nodeValue;
  }

  public void setNodeValue(int nodeValue) {
    this.nodeValue = nodeValue;
  }

  public HashMap<Integer,TreeNode> getChildValues() {
    return childValues;
  }

  public void setChildValues(HashMap<Integer,TreeNode> childValues) {
    this.childValues = childValues;
  }

  @Override
  public String toString() {
    return "TreeNode{" +
        "nodeValue=" + nodeValue +
        ", childValues=" + childValues +
        '}';
  }
}
