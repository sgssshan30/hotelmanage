import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ViewList {

    public ViewList() throws SQLException {
      
        DisplayResults();
    }

    private void DisplayResults() throws SQLException {
        try {
            DBUtilities dbUtilities = new DBUtilities();

            String sql_stmt = "SELECT id, name, email, contact_number, room_number FROM customers";
            ResultSet resultSet = dbUtilities.ReadRecords(sql_stmt);

            if (resultSet.next()) {

                ResultSetMetaData metaData = resultSet.getMetaData();
                int numberOfColumns = metaData.getColumnCount();
                System.out.print("\nList of Customers\n");

                for (int i = 1; i <= numberOfColumns; i++) {
                    System.out.printf("%-13s \t", metaData.getColumnName(i));
                }
                System.out.println();

                do {
                    for (int i = 1; i <= numberOfColumns; i++) {
                        System.out.printf("%-8s \t", resultSet.getObject(i));
                    }
                    System.out.println();
                } while (resultSet.next());
                
                System.out.println();

            } else 
                System.out.println("Customer List is Empty");

            dbUtilities.DisconnectFromDB();
        } catch (SQLException ex) {
            System.out.println("The following error has occured: " + ex.getMessage());
        } finally {
        	Booking.DisplayOption();
        }
    }
}