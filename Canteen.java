import java.util.Scanner;

public class Canteen {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Menu items
        String[] items = {
            "Burger",
            "Pizza",
            "Pasta",
            "Sandwich",
            "Milk Tea"
        };

        double[] prices = {
            80.00,
            120.00,
            100.00,
            70.00,
            90.00
        };

        // Overall order tracking
        int totalItems = 0;
        double totalBeforeDiscount = 0.0;
        double totalDiscount = 0.0;

        String orderAgain = "Y";

        while (orderAgain.equalsIgnoreCase("Y")) {

            // Display menu
            System.out.println("\n===== M E N U =====");

            for (int i = 0; i < items.length; i++) {
                System.out.printf("%d. %s - $%.2f%n",
                        i + 1, items[i], prices[i]);
            }

            // Get order details
            System.out.print("\nEnter item number: ");
            int itemNumber = input.nextInt();

            System.out.print("Enter quantity: ");
            int quantity = input.nextInt();

            System.out.print("Are you a student? (Y/N): ");
            String studentAnswer = input.next();

            // Validate item number and quantity
            if (itemNumber < 1 || itemNumber > 5 || quantity < 1 || quantity > 10) {

             System.out.println("\nInvalid order! Please enter a valid item and quantity.");

            // Ask if customer wants to order again
            System.out.print("\nDo you want to order again? (Y/N): ");
            orderAgain = input.next();

            // Skip the remaining processing of this invalid order
            continue;
        }
        
            // Determine student status
            boolean isStudent = studentAnswer.equalsIgnoreCase("Y");

            // Calculate subtotal
            double subtotal = prices[itemNumber - 1] * quantity;

            // Calculate discount
            double discountRate;

            if (isStudent && subtotal >= 500) {
                // Student + $500 or more = 15%
                discountRate = 0.15;
            } else if (isStudent) {
                // Student only = 10%
                discountRate = 0.10;
            } else if (subtotal >= 500) {
                // $500 or more = 5%
                discountRate = 0.05;
            } else {
                // No discount
                discountRate = 0.00;
            }

            double discount = subtotal * discountRate;
            double orderTotal = subtotal - discount;

            // Update overall totals
            totalItems += quantity;
            totalBeforeDiscount += subtotal;
            totalDiscount += discount;

            // Display order result
            System.out.printf("%nSubtotal: $%.2f%n", subtotal);
            System.out.printf("Discount: $%.2f%n", discount);
            System.out.printf("Order total: $%.2f%n", orderTotal);

            // Ask for another order
            System.out.print("\nDo you want to order again? (Y/N): ");
            orderAgain = input.next();
        }

        // Final order summary
        double finalAmount = totalBeforeDiscount - totalDiscount;

        System.out.println("\n===== ORDER SUMMARY =====");
        System.out.println("Total items: " + totalItems);
        System.out.printf("Total before discount: $%.2f%n", totalBeforeDiscount);
        System.out.printf("Total discount: $%.2f%n", totalDiscount);
        System.out.printf("Final amount: $%.2f%n", finalAmount);
        System.out.println("Thank you for ordering!");

        input.close();
    }
}