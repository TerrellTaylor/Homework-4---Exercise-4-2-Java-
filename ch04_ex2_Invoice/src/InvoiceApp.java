import java.text.NumberFormat;
import java.util.Scanner;

public class InvoiceApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String choice = "y";

        while (!choice.equalsIgnoreCase("n")) {
            // get the input from the user
            System.out.print("Enter customer type (r/c): ");
            String customerType = sc.nextLine();
            
            System.out.print("Enter subtotal:   ");
            double subtotal = Double.parseDouble(sc.nextLine());

            // get the discount percent
            double discountPercent = switch(customerType) {
                case "r", "R" -> {
                    if (subtotal >= 250) {
                        yield .2;
                    } else if (subtotal >= 100) {
                        yield .1;
                    } else {  
                        yield 0.0;
                    } 
                }
                case "c", "C" -> {
                    if (subtotal >= 250) {
                        yield .3;
                    } else {
                        yield .2;
                    }
                }
                default -> .1;
            };

            // calculate the discount amount and round to 2 decimals
            double discountAmount = subtotal * discountPercent;
            discountAmount = Math.ceil(discountAmount * 100) / 100;

            // calculate the total
            double total = subtotal - discountAmount;

            // format and display the results
            NumberFormat currency = NumberFormat.getCurrencyInstance();
            NumberFormat percent = NumberFormat.getPercentInstance();
            System.out.println(
                "Discount percent: " + percent.format(discountPercent) + "\n"
              + "Discount amount:  " + currency.format(discountAmount) + "\n"
              + "Total:            " + currency.format(total) + "\n");

            // see if the user wants to continue
            System.out.print("Continue? (y/n): ");
            choice = sc.nextLine();
            System.out.println();
        }
    }
}