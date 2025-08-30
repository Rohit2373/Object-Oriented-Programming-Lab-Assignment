import java.util.InputMismatchException;
import java.util.Scanner;
public class Calculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
       int a=0, b=0;
       try
       {
        System.out.print("Enter first number: ");
        a = sc.nextInt();

        System.out.print("Enter second number: ");
        b = sc.nextInt();
        }
        catch(InputMismatchException e)
        {
        System.out.println("Invaild Input!!!");
        System.out.println("Terminal Error :"+e.getMessage());
        return;
        }
        System.out.println("\nChoose Operation:");
        System.out.println("1. Add");
        System.out.println("2. Subtract");
        System.out.println("3. Multiply");
        System.out.println("4. Divide");
        System.out.print("Enter your choice (1-4): ");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Sum = " + (a + b));
                break;
            case 2:
                System.out.println("Sub = " + (a - b));
                break;
            case 3:
                System.out.println("Mul = " + (a * b));
                break;
            case 4:
               try 
               {
               System.out.println("Div = " + (a/b));
               }
               catch(ArithmeticException e)
               {
               System.out.println("Error: Cannot Divide by Zero!");
               System.out.println("Terminal Error :"+e.getMessage());
               }
        }
         sc.close();
    }
}
