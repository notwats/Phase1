import javax.swing.*;
import java.sql.*;

public class ConnectionLink {

    public static void connecting() {
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "inthelight");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM -group");

            while(resultSet.next()){
                System.out.println(resultSet.getString("group_id"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
