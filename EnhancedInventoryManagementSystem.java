import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class EnhancedInventoryManagementSystem extends JFrame {

    private String[] productNames;
    private int[] productQuantities;
    private int[] productIds;

    private JTextField adminItemNameField;
    private JTextField adminQuantityField;
    private JTextField adminItemIdField;

    private JTextField customerItemIdField;
    private JTextField customerQuantityField;

    private JTable inventoryTable;
    private DefaultTableModel tableModel;

    private JTextArea billTextArea;

    public EnhancedInventoryManagementSystem() {
        super("Enhanced Inventory Management System");
        productNames = new String[100]; // Assuming a maximum of 100 products
        productQuantities = new int[100];
        productIds = new int[100];

        adminItemNameField = new JTextField(20);
        adminQuantityField = new JTextField(5);
        adminItemIdField = new JTextField(5);

        customerItemIdField = new JTextField(5);
        customerQuantityField = new JTextField(5);

        JButton adminAddButton = new JButton("Add Product");
        adminAddButton.addActionListener(e -> addProduct());

        JButton adminUpdateButton = new JButton("Update Quantity");
        adminUpdateButton.addActionListener(e -> updateQuantity());

        JButton customerSellButton = new JButton("Sell Product");
        customerSellButton.addActionListener(e -> sellProduct());

        JButton viewBillButton = new JButton("View Bill");
        viewBillButton.addActionListener(e -> showBill());

        tableModel = new DefaultTableModel();
        inventoryTable = new JTable(tableModel);
        tableModel.addColumn("Product ID");
        tableModel.addColumn("Product Name");
        tableModel.addColumn("Quantity");

        JScrollPane tableScrollPane = new JScrollPane(inventoryTable);

        billTextArea = new JTextArea(10, 30);
        billTextArea.setEditable(false);

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel adminPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        adminPanel.add(new JLabel("Product Name:"));
        adminPanel.add(adminItemNameField);
        adminPanel.add(new JLabel("Quantity:"));
        adminPanel.add(adminQuantityField);
        adminPanel.add(new JLabel("Product ID:"));
        adminPanel.add(adminItemIdField);
        adminPanel.add(adminAddButton);
        adminPanel.add(adminUpdateButton);

        JPanel customerPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        customerPanel.add(new JLabel("Product ID:"));
        customerPanel.add(customerItemIdField);
        customerPanel.add(new JLabel("Quantity:"));
        customerPanel.add(customerQuantityField);
        customerPanel.add(customerSellButton);
        customerPanel.add(new JLabel()); // Placeholder for better alignment
        customerPanel.add(viewBillButton);

        JPanel billPanel = new JPanel(new BorderLayout());
        billPanel.add(new JScrollPane(billTextArea), BorderLayout.CENTER);

        tabbedPane.addTab("Admin Panel", adminPanel);
        tabbedPane.addTab("Customer Panel", customerPanel);
        tabbedPane.addTab("Billing", billPanel);

        setLayout(new BorderLayout());
        add(tabbedPane, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addProduct() {
        String itemName = adminItemNameField.getText();
        String quantityText = adminQuantityField.getText();
        String itemIdText = adminItemIdField.getText();

        if (!itemIdText.isEmpty() && !itemName.isEmpty() && !quantityText.isEmpty()) {
            int itemId = Integer.parseInt(itemIdText);
            int quantity = Integer.parseInt(quantityText);

            if (itemId >= 0 && itemId < productNames.length) {
                productIds[itemId] = itemId;
                productNames[itemId] = itemName;
                productQuantities[itemId] = quantity;
                updateTable();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid product ID.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter product ID, name, and quantity.");
        }

        clearAdminFields();
    }

    private void updateQuantity() {
        String itemIdText = adminItemIdField.getText();
        String quantityText = adminQuantityField.getText();

        if (!itemIdText.isEmpty() && !quantityText.isEmpty()) {
            int itemId = Integer.parseInt(itemIdText);

            if (itemId >= 0 && itemId < productNames.length) {
                int currentQuantity = productQuantities[itemId];
                int newQuantity = Integer.parseInt(quantityText);
                productQuantities[itemId] = currentQuantity + newQuantity;
                updateTable();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid product ID.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter product ID and quantity.");
        }

        clearAdminFields();
    }

    private void sellProduct() {
        String itemIdText = customerItemIdField.getText();
        String quantityText = customerQuantityField.getText();

        if (!itemIdText.isEmpty() && !quantityText.isEmpty()) {
            int itemId = Integer.parseInt(itemIdText);

            if (itemId >= 0 && itemId < productNames.length) {
                int availableQuantity = productQuantities[itemId];
                int requestedQuantity = Integer.parseInt(quantityText);

                if (requestedQuantity <= availableQuantity) {
                    int updatedQuantity = availableQuantity - requestedQuantity;
                    productQuantities[itemId] = updatedQuantity;
                    generateBill(productNames[itemId], itemId, requestedQuantity);
                    showgenerateBill(productNames[itemId], itemId, requestedQuantity);
                    updateTable();
                } else {
                    JOptionPane.showMessageDialog(this, "Not enough quantity available for sale.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Invalid product ID.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter product ID and quantity.");
        }

        clearCustomerFields();
    }

    private void generateBill(String productName, int productId, int quantity) {
        double pricePerItem = 5.0; // You can set the price per item as needed
        double totalAmount = pricePerItem * quantity;

        String billDetails = "Product ID: " + productId + "\n" +
                "Product Name: " + productName + "\n" +
                "Quantity: " + quantity + "\n" +
                "Price per Item: $" + pricePerItem + "\n" +
                "Total Amount: $" + totalAmount + "\n\n";

        billTextArea.append(billDetails);
    }

    private void showgenerateBill(String productName, int productId, int quantity) {
        double pricePerItem = 5.0; // You can set the price per item as needed
        double totalAmount = pricePerItem * quantity;

        String billDetails = "Product ID: " + productId + "\n" +
                "Product Name: " + productName + "\n" +
                "Quantity: " + quantity + "\n" +
                "Price per Item: $" + pricePerItem + "\n" +
                "Total Amount: $" + totalAmount + "\n\n";

        JOptionPane.showMessageDialog(this, billDetails, "Product Sold Successfully - BILL DETAILS", JOptionPane.INFORMATION_MESSAGE);
    }

    private void updateTable() {
        tableModel.setRowCount(0);
        for (int i = 0; i < productIds.length; i++) {
            if (productIds[i] != 0) {
                Object[] rowData = {productIds[i], productNames[i], productQuantities[i]};
                tableModel.addRow(rowData);
            }
        }
    }

    private void clearAdminFields() {
        adminItemNameField.setText("");
        adminQuantityField.setText("");
        adminItemIdField.setText("");
    }

    private void clearCustomerFields() {
        customerItemIdField.setText("");
        customerQuantityField.setText("");
    }

    private void showBill() {
        if (billTextArea.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No items to display in the bill.");
        } else {
            JTextArea billDisplayArea = new JTextArea(billTextArea.getText());
            billDisplayArea.setEditable(false);
            JOptionPane.showMessageDialog(this, new JScrollPane(billDisplayArea), "Bill Details", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EnhancedInventoryManagementSystem());
    }
}
