// first jdbc program:

import java.sql.Connection;
import java.sql.DriverManager;

// JDBC Connection illustration:

class Jdbcfirst {
    public static void main(String[] args) {
        try {

            // loading driver class:
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Creating connection:
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ajp", "root", "****");

            if(con.isClosed()) {
                System.out.println("Connection closed.");
            } else {
                System.out.println("Connected to mysql database.");
            }

            con.close();
        } catch(Exception sq) {
            sq.printStackTrace();
        }
    }
}