import java.util.Scanner;

public class Hotelmanagementsystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean[][] rooms = new boolean[5][4];
        int choice;

        System.out.println("Welcome to the Room Booking System!");

        do {
            System.out.println("\nMenu:");
            System.out.println("1. View Room Availability");
            System.out.println("2. Book a Room");
            System.out.println("3. Cancel a Booking");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewRooms(rooms);
                    break;
                case 2:
                    bookRoom(rooms, scanner);
                    break;
                case 3:
                    cancelBooking(rooms, scanner);
                    break;
                case 4:
                    System.out.println("Thank you for using the Hotel Management System!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);

        scanner.close();
    }

    public static void viewRooms(boolean[][] rooms) {
        System.out.println("\nRoom Availability:");
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[i].length; j++) {
                System.out.print((rooms[i][j] ? "[Booked]" : "[Available]") + " ");
            }
            System.out.println();
        }

    }
    public static void bookRoom(boolean[][] rooms, Scanner scanner) {
        System.out.print("\nEnter floor number (1-5): ");
        int floor = scanner.nextInt() - 1;
        System.out.print("Enter room number (1-4): ");
        int room = scanner.nextInt() - 1;

        if (floor >= 0 && floor < rooms.length && room >= 0 && room < rooms[floor].length) {
            if (!rooms[floor][room]) {
                rooms[floor][room] = true;
                System.out.println("Room successfully booked!");
            } else {
                System.out.println("Room is already booked.");
            }
        } else {
            System.out.println("Invalid floor or room number.");
        }
    }

    public static void cancelBooking(boolean[][] rooms, Scanner scanner) {
        System.out.print("\nEnter floor number (1-5): ");
        int floor = scanner.nextInt() - 1;
        System.out.print("Enter room number (1-4): ");
        int room = scanner.nextInt() - 1;

        if (floor >= 0 && floor < rooms.length && room >= 0 && room < rooms[floor].length) {
            if (rooms[floor][room]) {
                rooms[floor][room] = false;
                System.out.println("Booking successfully canceled!");
            } else {
                System.out.println("Room is not booked.");
            }
        } else {
            System.out.println("Invalid floor or room number.");
        }
    }
}
