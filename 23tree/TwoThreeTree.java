public class TwoThreeTree
{
   Node root = new Node();

   public static void main(String[] args)
   {
      TwoThreeTree aTree = new TwoThreeTree();
      for (int i = 0; i <= 20; i++)
      {
         aTree.insert(i);
      }

      aTree.printItOut();

   }

   public void insert(int key)
   {
      root = insert(root, key);
   }

   public Node insert(Node rt, int key)
   {
      rt.insert(new Node(key, null, null));
      if (rt.isFull())
      {
         return rt.split();
      } else
      {
         return rt;
      }
   }

   public void printItOut()
   {
      printItOut("--", root);
   }

   public void printItOut(String partition, Node rt)
   {
      String newPartition = "";
      newPartition += partition;
      newPartition += partition;

      System.out.println(partition + rt);
      for (Node n : rt.child)
      {
         if (n != null)
            printItOut(newPartition, n);
      }

   }
}
