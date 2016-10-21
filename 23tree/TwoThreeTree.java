public class TwoThreeTree
{
   Node root = new Node();

   public static void main(String[] args)
   {
      TwoThreeTree t = new TwoThreeTree();
      t.insert(19);
      t.insert(5);
      t.insert(1);
      t.insert(18);
      t.insert(3);
      t.insert(8);
      t.insert(24);
      t.insert(13);
      t.insert(16);
      t.insert(12);
      t.printItOut();
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

   private Node find(int x)
   {
      return find(this.root, x);
   }

   private Node find(Node current, int x)
   {
      int keySize = current.numKeys;
      if (current.isLeaf())
      {
         return current;
      }

      for (int i = 0; i < keySize; i++)
      {
         if (current.keys[i] == x)
         {
            return current;
         } else if (x < current.keys[i])
         {
            return find(current.child[i], x);
         }
      }

      return find(current.child[keySize], x);

   }

   public String search(int val)
   {
      String result = "";
      Node targetNode = find(val);

      int keySize = targetNode.numKeys;
      for (int i = 0; i < keySize; i++)
      {
         result += targetNode.keys[i];
         result += " ";
      }
      return result.substring(0, result.length() - 1);
   }

}
