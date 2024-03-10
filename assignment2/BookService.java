package assignment2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;


public class BookService {
    public static void main(String[] args) {
        try {

            // load the driver:
            Class.forName("com.mysql.cj.jdbc.Driver");

            // creating connection:
            String url = "jdbc:mysql://localhost:3306/ajp";
            String username = "root";
            String password = "****";

            Connection con = DriverManager.getConnection(url, username, password);

            // create table query:
            String createTable = "create table if not exists Books(BookTitle varchar(65) not null, Authorname varchar(50) not null, Publisher varchar(80) not null, Price decimal(10,2) not null)";

            Statement stmt = con.createStatement();
            stmt.executeUpdate(createTable);

            System.out.println("Table created successfully..\n");

            // inserting record into table:
            String insertRecord = "insert into Books values(?,?,?,?)";

            PreparedStatement pstmt = con.prepareStatement(insertRecord);
            for(int i = 1; i < 21; i++) {
                pstmt.setString(1, "Book"+i);
                pstmt.setString(2, "Author"+i);
                pstmt.setString(3, "Publisher"+i);
                pstmt.setDouble(4, (i*100));
                pstmt.executeUpdate();
            }

            // update query:
            System.out.println("---- Update Book Price: \n");
            String upadteQuery = "update Books set Price = ? where BookTitle = ?";

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Enter valid book title: ");
            String bookTitle = br.readLine();

            String getPriceQuery = "select Price from Books where BookTitle = '"+bookTitle+"'";

            ResultSet rs =  stmt.executeQuery(getPriceQuery);

            double currPrice = -1;
            
            while(rs.next()) {
                currPrice = rs.getDouble(1);
            }

            Scanner scn = new Scanner(System.in);

            if(currPrice == -1) 
                System.out.println("Invalid book title !!");
            else {
                System.out.println("The current price of book is "+currPrice+" enter new price to update: ");
                double newPrice = scn.nextDouble();
                pstmt = con.prepareStatement(upadteQuery);
                pstmt.setDouble(1, newPrice);
                pstmt.setString(2, bookTitle);

                pstmt.executeUpdate();
                System.out.println("Price updated successfully !!\n");
            }


            // delete record of the given book:

            System.out.println("---- Delete Book Record: \n");

            String deleteQuery = "delete from Books where BookTitle = ?";

            System.out.println("Enter the book title to delete that book record:");
            bookTitle = scn.next();

            pstmt = con.prepareStatement(deleteQuery);
            pstmt.setString(1, bookTitle);
            pstmt.executeUpdate();

            System.out.println("Record deleted successfully !!");

            pstmt.close();
            con.close();
            br.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
