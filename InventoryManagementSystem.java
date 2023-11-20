import java.util.ArrayList;
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

    // Add getters and setters here

    public double calculateTotalPrice(int quantity) {
        return price * quantity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Price: $" + price + ", Quantity: " + quantity;
    }
}

class Inventory {
    private ArrayList<Product> products;

    public Inventory() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
        System.out.println("Product added successfully!");
    }

    public void updateProduct(int id, int newQuantity) {
        for (Product product : products) {
            if (product.getId() == id) {
                product.setQuantity(newQuantity);
                System.out.println("Product updated successfully!");
                return;
            }
        }
        System.out.println("Product not found with ID: " + id);
    }

    public void sellProduct(int id, int quantity) {
        for (Product product : products) {
            if (product.getId() == id) {
                if (product.getQuantity() >= quantity) {
                    double totalPrice = product.calculateTotalPrice(quantity);
                    System.out.println("Product sold successfully!");
                    System.out.println("Product: " + product.getName());
                    System.out.println("Quantity: " + quantity);
                    System.out.println("Total Price: $" + totalPrice);

                    // Update product quantity after selling
                    int remainingQuantity = product.getQuantity() - quantity;
                    product.setQuantity(remainingQuantity);
                    return;
                } else {
                    System.out.println("Insufficient quantity in stock.");
                    return;
                }
            }
        }
        System.out.println("Product not found with ID: " + id);
    }

    public void displayProducts() {
        System.out.println("Inventory:");
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
            System.out.println("\n1. Add Product\n2. Update Product\n3. Sell Product\n4. Display Products\n5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter product ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    System.out.print("Enter product name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter product price: ");
                    double price = scanner.nextDouble();
                    System.out.print("Enter product quantity: ");
                    int quantity = scanner.nextInt();

                    Product newProduct = new Product(id, name, price, quantity);
                    inventory.addProduct(newProduct);
                    break;

                case 2:
                    System.out.print("Enter product ID to update quantity: ");
                    int updateId = scanner.nextInt();
                    System.out.print("Enter new quantity: ");
                    int newQuantity = scanner.nextInt();
                    inventory.updateProduct(updateId, newQuantity);
                    break;

                case 3:
                    System.out.print("Enter product ID to sell: ");
                    int sellId = scanner.nextInt();
                    System.out.print("Enter quantity to sell: ");
                    int sellQuantity = scanner.nextInt();
                    inventory.sellProduct(sellId, sellQuantity);
                    break;

                case 4:
                    inventory.displayProducts();
                    break;

                case 5:
                    System.out.println("Exiting Inventory Management System. Goodbye!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
