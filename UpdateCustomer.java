import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateCustomer {

    UpdateCustomer() throws SQLException {
        Scanner sc = new Scanner(System.in);

        String customer_id;
        System.out.println("Enter customer id to update: ");
        customer_id = sc.next();

        DisplayRecord(customer_id);

        String name;
        System.out.println("Enter contact name: ");
        name = sc.next();

        String email;
        System.out.println("Enter contact email: ");
        email = sc.next();

        String contact_number;
        System.out.println("Enter contact number: ");
        contact_number = sc.next();
        
        String room_number;
        System.out.println("Enter room number: ");
        room_number = sc.next();

        DBUtilities dbUtilities = new DBUtilities();

        String sql_stmt = "UPDATE customers SET name = '" + name + "',email = '" + email + "',contact_number = '" + contact_number +"',room_number = '" + room_number + "' WHERE id = " + customer_id;

        dbUtilities.ExecuteSQLStatement(sql_stmt);

        Booking.DisplayOption();
        sc.close();
    }
    private void DisplayRecord(String customer_id) throws SQLException {
        try {
            DBUtilities dbUtilities = new DBUtilities();

            String sql_stmt = "SELECT id, name, email, contact_number,room_number FROM customers WHERE id = " + customer_id;
            ResultSet resultSet = dbUtilities.ReadRecords(sql_stmt);

            if (resultSet.next()) {

                ResultSetMetaData metaData = resultSet.getMetaData();
                int numberOfColumns = metaData.getColumnCount();
                
                for (int i = 1; i <= numberOfColumns; i++) {
                    System.out.printf("%-8s \t", metaData.getColumnName(i));
                }
                System.out.println();

                do {
                    for (int i = 1; i <= numberOfColumns; i++) {
                        System.out.printf("%-8s \t", resultSet.getObject(i));
                    }
                    System.out.println();
                } while (resultSet.next());
               
                System.out.println();

            } else {
                System.out.println("Customer List is Empty");
            }
            dbUtilities.DisconnectFromDB();
        } catch (SQLException ex) {
            System.out.println("The following error has occured: " + ex.getMessage());
        }
    }
}