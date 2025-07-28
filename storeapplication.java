import java.util.Scanner;

class Product {
    String name;
    int quantity;
    int price;

    Product(String name, int quantity, int price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
}

class Grocerystore {
    static int numproducts = 0;
    static int totalrevenue = 0;

    static void addproducts(Product p) {
        System.out.println("✅ Product added: " + p.name);
        numproducts = numproducts + 1;
    }

    static void sellproducts(Product p, int quantity) {
        if (p.quantity >= quantity) {
            int amount = quantity * p.price;
            p.quantity -= quantity;
            System.out.println("🛒 Product " + p.name + " sold. Revenue: ₹" + amount);
            totalrevenue = totalrevenue + amount;
        } else {
            System.out.println("❌ Not enough stock for " + p.name + ". Available: " + p.quantity);
        }
    }

    static void displaystats(Product[] products) {
        System.out.println("\n📦 Store Stats:");
        System.out.println("Total distinct products: " + numproducts);
        System.out.println("Total revenue after selling: ₹" + totalrevenue);
        System.out.println("Product details:");
        for (Product p : products) {
            if (p != null) {
                System.out.println("- " + p.name + " | Stock: " + p.quantity + " | Price: ₹" + p.price);
            }
        }
    }
}

public class storeapplication {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Product[] products = new Product[100]; // Max 100 products
        int index = 0;

        int choice;
        do {
            System.out.println("\n🛒 Grocery Store Menu");
            System.out.println("1. Add Product");
            System.out.println("2. Sell Product");
            System.out.println("3. Show Store Stats");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume leftover newline

            switch (choice) {
                case 1:
                    System.out.print("Enter product name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter quantity: ");
                    int qty = sc.nextInt();
                    System.out.print("Enter price: ");
                    int price = sc.nextInt();
                    Product p = new Product(name, qty, price);
                    products[index++] = p;
                    Grocerystore.addproducts(p);
                    break;

                case 2:
                    System.out.print("Enter product name to sell: ");
                    String sellName = sc.nextLine();
                    System.out.print("Enter quantity to sell: ");
                    int sellQty = sc.nextInt();
                    boolean found = false;
                    for (int i = 0; i < index; i++) {
                        if (products[i].name.equalsIgnoreCase(sellName)) {
                            Grocerystore.sellproducts(products[i], sellQty);
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("❌ Product not found: " + sellName);
                    }
                    break;

                case 3:
                    Grocerystore.displaystats(products);
                    break;

                case 4:
                    System.out.println("👋 Exiting... Thank you!");
                    break;

                default:
                    System.out.println("❌ Invalid choice. Please try again.");
            }
        } while (choice != 4);

        sc.close();
    }
}