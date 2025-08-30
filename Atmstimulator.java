import java.util.InputMismatchException;
import java.util.Scanner;

public class Atmstimulator{
    private double balance;

    public Atmstimulator(double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        balance += amount;
        System.out.println("Deposited: " + amount);
        System.out.println("Balance: " + balance);
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }
        if (amount > balance) {
            throw new ArithmeticException("Insufficient funds.");
        }
        balance -= amount;
        System.out.println("Withdrawn: " + amount);
        System.out.println("Balance: " + balance);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Atmstimulator atm = new Atmstimulator(1000); // Starting balance of $1000

        while (true) {
            System.out.println("\n--- ATM Menu ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            try {
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.printf("Current balance: %.2f%n", atm.getBalance());
                        break;

                    case 2:
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = scanner.nextDouble();
                        atm.deposit(depositAmount);
                        break;

                    case 3:
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = scanner.nextDouble();
                        atm.withdraw(withdrawAmount);
                        break;

                    case 4:
                        System.out.println("Thank you for using the ATM");
                        scanner.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice! Please select a valid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter numeric values only.");
            } catch (IllegalArgumentException | ArithmeticException e) {
                System.out.println("Error: " + e.getMessage());
            } finally {
                System.out.println("Transaction Completed.\n");
            }
        }
    }
}
