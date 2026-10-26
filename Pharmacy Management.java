import java.util.Scanner;

class PharmacyManagement {
    static Scanner sc = new Scanner(System.in);

    static String[] medicine = new String[100];
    static int[] quantity = new int[100];
    static double[] price = new double[100];
    static int count = 0;

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n===== PHARMACY MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Medicine");
            System.out.println("2. View Medicine Stock");
            System.out.println("3. Sell Medicine and Generate Bill");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addMedicine();
                    break;
                case 2:
                    viewStock();
                    break;
                case 3:
                    sellMedicine();
                    break;
                case 4:
                    System.out.println("Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 4);

        sc.close();
    }

    static void addMedicine() {
        if (count >= medicine.length) {
            System.out.println("Medicine storage is full!");
            return;
        }

        System.out.print("Enter medicine name: ");
        medicine[count] = sc.nextLine();

        System.out.print("Enter quantity: ");
        quantity[count] = sc.nextInt();

        System.out.print("Enter price per unit: ");
        price[count] = sc.nextDouble();
        sc.nextLine();

        if (quantity[count] < 0 || price[count] < 0) {
            System.out.println("Invalid quantity or price!");
            return;
        }

        count++;
        System.out.println("Medicine added successfully!");
    }

    static void viewStock() {
        if (count == 0) {
            System.out.println("No medicines available.");
            return;
        }

        System.out.println("\n----- MEDICINE STOCK -----");
        System.out.printf("%-20s %-10s %-10s%n",
                          "Medicine", "Quantity", "Price");

        for (int i = 0; i < count; i++) {
            System.out.printf("%-20s %-10d Rs. %.2f%n",
                    medicine[i], quantity[i], price[i]);
        }
    }

    static void sellMedicine() {
        System.out.print("Enter medicine name: ");
        String name = sc.nextLine();

        int index = -1;

        for (int i = 0; i < count; i++) {
            if (medicine[i].equalsIgnoreCase(name)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("Medicine not found!");
            return;
        }

        System.out.println("Available stock: " + quantity[index]);
        System.out.print("Enter quantity to sell: ");
        int qty = sc.nextInt();
        sc.nextLine();

        if (qty <= 0) {
            System.out.println("Enter a valid quantity!");
            return;
        }

        if (qty > quantity[index]) {
            System.out.println("Insufficient stock!");
            return;
        }

        double total = qty * price[index];
        quantity[index] -= qty;

        System.out.println("\n========== PHARMACY BILL ==========");
        System.out.println("Medicine Name : " + medicine[index]);
        System.out.println("Quantity      : " + qty);
        System.out.printf("Price per Unit: Rs. %.2f%n", price[index]);
        System.out.printf("Total Amount  : Rs. %.2f%n", total);
        System.out.println("===================================");
        System.out.println("Billing completed successfully!");
    }
}