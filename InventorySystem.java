import java.util.*;

public class InventorySystem {

    static class Item {
        int id;
        String name;
        int quantity;
        double price;

        Item(int id, String name, int quantity, double price) {
            this.id = id;
            this.name = name;
            this.quantity = quantity;
            this.price = price;
        }

        public String toString() {
            return id + " | " + name + " | Qty: " + quantity + " | Price: ₹" + price;
        }
    }

    private static final Scanner sc = new Scanner(System.in);
    private static final Map<Integer, Item> inventory = new LinkedHashMap<>();
    private static int nextId = 1;

    public static void main(String[] args) {
        System.out.println("=== Inventory Management System ===");
        printMenu();

        while (true) {
            System.out.print("\nEnter choice: ");
            int choice;
            if(!sc.hasNextInt()) break;
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addItem();
                    break;
                case 2:
                    viewItems();
                    break;
                case 3:
                    searchItem();
                    break;
                case 4:
                    updateStock();
                    break;
                case 5:
                    deleteItem();
                    break;
                case 6:
                    loadDemoData();
                    break;
                case 0:
                    printMenu();
                    break;
                case -1:
                    System.out.println("Exiting system...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    static void printMenu() {
        System.out.println("\nMenu:");
        System.out.println("1 - Add Item");
        System.out.println("2 - View All Items");
        System.out.println("3 - Search Item by Name");
        System.out.println("4 - Update Stock");
        System.out.println("5 - Delete Item");
        System.out.println("6 - Load Demo Data");
        System.out.println("0 - Show Menu");
        System.out.println("-1 - Exit");
    }

    static void addItem() {
        System.out.print("Item Name: ");
        String name = sc.nextLine();

        System.out.print("Quantity: ");
        int qty = sc.nextInt();

        System.out.print("Price: ");
        double price = sc.nextDouble();

        Item item = new Item(nextId++, name, qty, price);
        inventory.put(item.id, item);
        System.out.println("Item added with ID: " + item.id);
    }

    static void viewItems() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }
        System.out.println("\n--- Inventory List ---");
        for (Item item : inventory.values()) {
            System.out.println(item);
        }
    }

    static void searchItem() {
        System.out.print("Enter item name: ");
        String name = sc.nextLine().toLowerCase();
        boolean found = false;

        for (Item item : inventory.values()) {
            if (item.name.toLowerCase().contains(name)) {
                System.out.println(item);
                found = true;
            }
        }
        if (!found) System.out.println("Item not found.");
    }

    static void updateStock() {
        System.out.print("Enter Item ID: ");
        int id = sc.nextInt();

        Item item = inventory.get(id);
        if (item == null) {
            System.out.println("Item not found.");
            return;
        }

        System.out.print("New Quantity: ");
        item.quantity = sc.nextInt();
        System.out.println("Stock updated successfully.");
    }

    static void deleteItem() {
        System.out.print("Enter Item ID to delete: ");
        int id = sc.nextInt();

        if (inventory.remove(id) != null)
            System.out.println("Item removed.");
        else
            System.out.println("Item not found.");
    }

    static void loadDemoData() {
        inventory.clear();
        nextId = 1;
        inventory.put(nextId, new Item(nextId++, "Laptop", 10, 55000));
        inventory.put(nextId, new Item(nextId++, "Mouse", 50, 500));
        inventory.put(nextId, new Item(nextId++, "Keyboard", 30, 1200));
        inventory.put(nextId, new Item(nextId++, "Monitor", 15, 8000));
        System.out.println("Demo data loaded.");
    }
}