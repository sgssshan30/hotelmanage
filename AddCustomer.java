import java.sql.SQLException;
import java.util.Scanner;

public class AddCustomer {

	AddCustomer() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nAdd New Customer\n ");
        
        String name;
        System.out.println("Enter customer's name: ");
        name = sc.next();

        String email;
        System.out.println("Enter customer's email: ");
        email = sc.next();

        String contact_number;
        System.out.println("Enter customer's number: ");
        contact_number = sc.next();
        
        String room_number;
        System.out.println("Enter room number: ");
        room_number = sc.next();
       
        DBUtilities dbUtilities = new DBUtilities();
        String sql_stmt = "INSERT INTO customers (name,email,contact_number,room_number) VALUES ('" + name + "','" + email + "','" + contact_number + "','"+ room_number +"')";
        
        dbUtilities.ExecuteSQLStatement(sql_stmt);
        
        Booking.DisplayOption();
        sc.close();
    }
}