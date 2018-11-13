import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;

public class CheckOut {

		CheckOut() throws SQLException {
        Scanner sc = new Scanner(System.in);
       

        String customer_id;

        System.out.println("Enter customer id to proceed to check out: ");
        customer_id = sc.next();

        DisplayRecord(customer_id);

        String confirm_checkout;
        System.out.println("Enter Y to confirm check out: ");
        confirm_checkout = sc.next();

        if ("Y".equals(confirm_checkout)) {
            DBUtilities dbUtilities = new DBUtilities();

            String sql_stmt = "DELETE FROM customers WHERE id = " + customer_id;

            dbUtilities.ExecuteSQLStatement(sql_stmt);
            
            System.out.println("The Customer has successfully checked out");
        }

        Booking.DisplayOption();
        sc.close();
    }

    private void DisplayRecord(String customer_id) throws SQLException {
        try {
            DBUtilities dbUtilities = new DBUtilities();

            String sql_stmt = "SELECT id, name, email, contact_number, room_number FROM customers WHERE id = " + customer_id;
            ResultSet resultSet = dbUtilities.ReadRecords(sql_stmt);

            if (resultSet.next()) {

                ResultSetMetaData metaData = resultSet.getMetaData();
                int numberOfColumns = metaData.getColumnCount();
                System.out.print("\nList of Customers\n");

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
                System.out.println("No database records found");
            }

            dbUtilities.DisconnectFromDB();
        } catch (SQLException ex) {
            System.out.println("The following error has occured: " + ex.getMessage());
        } 
    }
}
