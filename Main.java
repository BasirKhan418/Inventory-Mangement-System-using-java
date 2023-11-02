import java.util.ArrayList;
import java.util.Scanner;

class Product {
    String name;
    int quantity;

    public Product(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }
}

public class InventoryManagementSystem {
    public static void main(String[] args) {
        ArrayList<Product> inventory = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Product");
            System.out.println("2. View Inventory");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter product name: ");
                    String productName = scanner.nextLine();
                    System.out.print("Enter product quantity: ");
                    int productQuantity = scanner.nextInt();
                    Product newProduct = new Product(productName, productQuantity);
                    inventory.add(newProduct);
                    System.out.println("Product added to inventory.");
                }
                case 2 -> {
                    System.out.println("Inventory List:");
                    for (Product product : inventory) {
                        System.out.println("Product: " + product.name + ", Quantity: " + product.quantity);
                    }
                }
                case 3 -> {
                    System.out.println("Exiting the program.");
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
