import java.util.ArrayList;

public class Sorter {

 public Node root;
 public ArrayList<Node> nodeList = new ArrayList<Node>();

 public Sorter(ArrayList<Node> nodeList) {
  this.nodeList = nodeList;
 }

 public void rank() {
  nodeList = Sort(nodeList);
 }

 private ArrayList<Node> Sort(ArrayList<Node> nodeList) {

  if (nodeList.size() == 0)
   return nodeList;

  for (int i = 0; i < nodeList.size(); i++) {

   for (int j = 0; j < nodeList.size() - 1 - i; j++) {

    if (nodeList.get(j + 1).totalScore > nodeList.get(j).totalScore) {
     Node temp = nodeList.get(j + 1);
     nodeList.set(j + 1, nodeList.get(j));
     nodeList.set(j, temp);
    }
   }
  }

  return nodeList;
 }
}