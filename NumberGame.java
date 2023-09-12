import java.util.Scanner;
import java.util.Random;
public class NumberGame{
   public static void main(String[] args){
      Scanner obj = new Scanner(System.in);
      
      Random obj1 = new Random();
      int randomnumber = obj1.nextInt(100);
      System.out.println("Your Total Input Chance is 10.");
      System.out.println("Guess any number");
      int Counter = 0;
      do {
         Counter++;
         int input = obj.nextInt();
         if (randomnumber == input){
            System.out.println("Congrats! You won the Match.");
         }
         else if (randomnumber > input){
            System.out.println("Enter some Large Number");
         }
         else if (randomnumber < input){
            System.out.println("Enter some Small Number");
         }
      }
      while (Counter < 10);
      System.out.println("Your input Chance is over.");
   }
}

