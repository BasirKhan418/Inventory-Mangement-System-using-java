import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Product {
    private int id;
    private String name;
    private double price;
    private int quantity;

    public Product(int id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public void addStock(int quantity) {
        this.quantity += quantity;
    }

    public void sell(int quantity) {
        if (this.quantity >= quantity) {
            this.quantity -= quantity;
        } else {
            System.out.println("Insufficient stock for " + name);
        }
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Price: $" + price + ", Quantity: " + quantity;
    }
}

class Inventory {
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public void listProducts() {
        for (Product product : products) {
            System.out.println(product);
        }
    }
}

public class InventoryManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Inventory inventory = new Inventory();

        while (true) {
            System.out.println("\nInventory Management System");
            System.out.println("1. Add Product");
            System.out.println("2. List Products");
            System.out.println("3. Sell Product");
            System.out.println("4. Restock Product");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    // Adding a new product
                    System.out.print("Enter product name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter product price: $");
                    double price = scanner.nextDouble();
                    System.out.print("Enter initial quantity: ");
                    int quantity = scanner.nextInt();
                    Product product = new Product(inventory.product.size() + 1, name, price, quantity);
                    inventory.addProduct(product);
                    System.out.println("Product added successfully.");
                    break;
                case 2:
                    // Listing all products
                    System.out.println("\nInventory List:");
                    inventory.listProducts();
                    break;
                case 3:
                    // Selling a product
                    System.out.print("Enter product ID to sell: ");
                    int sellId = scanner.nextInt();
                    System.out.print("Enter quantity to sell: ");
                    int sellQuantity = scanner.nextInt();
                    Product productToSell = inventory.product.get(sellId - 1);
                    productToSell.sell(sellQuantity);
                    System.out.println("Product sold.");
                    break;
                case 4:
                    // Restocking a product
                    System.out.print("Enter product ID to restock: ");
                    int restockId = scanner.nextInt();
                    System.out.print("Enter quantity to restock: ");
                    int restockQuantity = scanner.nextInt();
                    Product productToRestock = inventory.product.get(restockId - 1);
                    productToRestock.addStock(restockQuantity);
                    System.out.println("Product restocked.");
                    break;
                case 5:
                    // Exiting the program
                    System.out.println("Exiting Inventory Management System.");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }
}
