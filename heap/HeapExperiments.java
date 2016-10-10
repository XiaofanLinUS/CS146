import java.util.ArrayList;

public class HeapExperiments
{
   static int POPULATION = 1000000;
   static boolean RUN_ITERATED_INSERTION = false;
   static int ERRORS = 10000;

   
   public static void main(String[] args)
   {
      
      ArrayList<Student> students = createStudents(POPULATION);      
      MaxHeap heap = null;
      long startTime, endTime, duration;
      
      {
         startTime = System.nanoTime();
         heap = linearBuildHeap(students);      
         endTime = System.nanoTime();
         duration = endTime - startTime;
         System.out.println("Linear-time build time  = " + duration);
         System.out.println("Time per student = " + duration/POPULATION);
      }

      if(RUN_ITERATED_INSERTION)
      {
         startTime = System.nanoTime();
         heap = iteratedInsertionBuildHeap(students);            
         endTime = System.nanoTime();
         duration = endTime - startTime;
         System.out.println("Iterated-insertion build time  = " + duration);
         System.out.println("Time per student = " + duration/POPULATION);
      }
      
      startTime = System.nanoTime();
      makeErrors(students, heap);
      endTime = System.nanoTime();
      duration = endTime - startTime;

      if(ERRORS > 0)
      {
         System.out.println("Time to make " + ERRORS + " changes = " + duration);
         System.out.println("Time per student = " + duration/ERRORS);
      }
      
	  // doThemTogether();
   }
   
   private static void doThemTogether() {
	      ArrayList<Student> students = createStudents(POPULATION);      
	      MaxHeap heap = null;
	      long startTime, endTime, duration;
	      for(int i = 0; i < 10; i++) {
	      {
	         startTime = System.nanoTime();
	         {
	        	 heap = new MaxHeap(students.size());
	        	 for(Student s:students)
	        		 heap.insert(s);   
	         }
	         endTime = System.nanoTime();
	         duration = endTime - startTime;
	         System.out.println("Linear-time build time  = " + duration);
	         System.out.println("Time per student = " + duration/POPULATION);
	      }

	      {
	         startTime = System.nanoTime();
	         heap = iteratedInsertionBuildHeap(students);
	         {
	             heap = new MaxHeap(students.size());
	             for(Student s:students)
	                heap.insert(s);
	         }
	         endTime = System.nanoTime();
	         duration = endTime - startTime;
	         System.out.println("Iterated-insertion build time  = " + duration);
	         System.out.println("Time per student = " + duration/POPULATION);
	      }
	      System.out.println("----------------------------------------");
	      }
	   
   }
   private static void makeErrors(ArrayList<Student> students, MaxHeap heap)
   {
      for(int i = 0; i < ERRORS; i++)
      {
         int index = (int) (Math.random() * POPULATION);
         Student s = students.get(index);
         double newGPA = Math.random() * 8;
         heap.changeKey(s, newGPA);
      }
   }
   
   private static MaxHeap iteratedInsertionBuildHeap(ArrayList<Student> students)
   {
      MaxHeap heap = new MaxHeap(students.size());
      for(Student s:students)
         heap.insert(s);
      return heap;
   }

   private static MaxHeap linearBuildHeap(ArrayList<Student> students)
   {
      return new MaxHeap(students);
   }
   

   private static ArrayList<Student> createStudents(int number)
   {
      ArrayList<Student> students = new ArrayList<>(number);
      for(int i=0; i<number; i++)
      {
         int units = (int) (Math.random() * 100);
         //double gradePoints = Math.random() * 4;     //Random gradePoints
         double gradePoints = 4.0 * i / number;
         students.add(new Student("student" + i, units, gradePoints));
      }
      return students;
   }

         
}
