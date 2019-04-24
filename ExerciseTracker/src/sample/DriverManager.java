public class DBconnection {

    Connection connection = DriverManager.getConnection(jdbc:mysql://127.0.0.1:3306/ExerciseTrackerJDBC?user=root&password= root);
    Statement st = connection.createStatement();
    ResultSet rs= st.executeQuery("SELECT* FROM ExerciseTrackerJDBC");

}
