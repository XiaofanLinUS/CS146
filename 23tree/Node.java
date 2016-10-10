public class Node
{
   int[] keys = new int[3];
   Node[] child = new Node[4];
   int numKeys = 0;

   public Node()
   {
   }

   public Node(int key, Node left, Node right)
   {
      numKeys++;
      keys[0] = key;
      child[0] = left;
      child[1] = right;
   }

   public boolean isFull()
   {
      return numKeys == 3 ? true : false;
   }

   public boolean isLeaf()
   {
      return child[0] == null ? true : false;
   }

   public Node split()
   {
      Node left = new Node(keys[0], child[0], child[1]);
      Node right = new Node(keys[2], child[2], child[3]);
      Node proNode = new Node(keys[1], left, right);
      return proNode;
   }

   public String toString()
   {
      String result = "";
      for (int i = 0; i < numKeys; i++)
      {
         result += keys[i] + " ";
      }
      return result.substring(0, result.length() - 1);
   }

   public void shift(int i)
   {
      int index = numKeys;
      while (index > 0 && index > i)
      {
         keys[index] = keys[index - 1];
         child[index + 1] = child[index];
         index--;
      }
   }

   public void add(Node aNode)
   {
      int x = aNode.keys[0];
      int i = numKeys;
      Node left = aNode.child[0];
      Node right = aNode.child[1];

      while (i > 0 && x < keys[i - 1])
      {
         i--;
      }
      shift(i);
      keys[i] = x;
      child[i] = left;
      child[i + 1] = right;
      numKeys++;
   }

   public void insert(Node aNode)
   {
      int x = aNode.keys[0];
      int i = 0;
      while (i < numKeys && x > keys[i])
      {
         i++;
      }
      if (numKeys > 0 && keys[i] == x)
      {
         return;
      }
      if (!isLeaf())
      {
         child[i].insert(aNode);
         if (child[i].isFull())
         {
            Node newNode = child[i].split();
            this.add(newNode);
         }
      } else
      {
         this.add(aNode);
      }
   }

}
