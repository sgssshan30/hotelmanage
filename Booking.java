import java.sql.SQLException;
import java.util.Scanner;

public class Booking {

    public static void main(String[] args) throws SQLException {
        DisplayOption();
    }

    public static void DisplayOption() throws SQLException {
        Scanner sc = new Scanner(System.in);
        String Option;

        System.out.println("HOTEL MANAGEMENT SYSTEM\nOptions:\n1. Add New Customer\n2. View All Customer\n3. Search For Customer\n4. Check Out\n5. Update Customer Data\n6. Exit\n\nSelect option:");
        Option = sc.next();
        
        switch (Option) {
            case "1":
                new AddCustomer();
                break;
            case "2":
            	new ViewList();
                break;
            case "3":
            	new SearchCustomer();
                break;
            case "4":
            	new CheckOut();
                break;
            case "5": 
            	new UpdateCustomer();
            	break;
            case "6":
                System.exit(0);
                break;
            default:
                System.out.println("Selection Incorrect");
                break; 
        }
        sc.close();
    }
    
}