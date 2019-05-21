package sample;

import java.sql.*;

public class DB {

    String url = "jdbc:mysql://den1.mysql5.gear.host:3306?user=xtracker&password=Kt4j_?V18w07";

    Statement statement;

    public DB() {
        try {
            Connection c = (Connection) DriverManager.getConnection(url);
            statement = c.createStatement();
        } catch (SQLException ex) {
            System.out.println("the connection fails");
        }
    }


    public void doAHandshake() {
        try {
            ResultSet rs = statement.executeQuery("SHOW databases");

            while(rs.next())
            {
                System.out.println(" This database ==> " + rs.getString(1));
            }

        }
        catch (SQLException ex)
        {
            System.out.println("error on executing the handshake");
        }
    }

    public void loadUsers() {
        try {
            statement.executeQuery("USE xtracker");
            ResultSet rs = statement.executeQuery("SELECT * FROM user");

            while(rs.next())
            {
                String name = rs.getString(3);
                String password = rs.getString(4);
                String email = rs.getString(2);
                boolean isAdmin = rs.getBoolean(6);
                int weight = rs.getInt(5);
                int userID = rs.getInt(1);
                //String userName, String passWord, String email, Boolean isAdmin
                //String userName, String passWord, String email, boolean isAnAdmin, int weight, int userID
                User user1 = new User(name, password, email,isAdmin, weight,userID);
                DataHolder.userList.add(user1);
                System.out.println("The user: " + name + " is loaded to the application");
            }

            DataHolder.isConnected = true;

        }
        catch (SQLException ex)
        {
            System.out.println("error on executing the handshake");
        }
    }
}