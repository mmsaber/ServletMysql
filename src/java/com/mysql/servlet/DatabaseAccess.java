package com.mysql.servlet;

import java.io.*;
import org.json.simple.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/DatabaseAccess")
public class DatabaseAccess extends HttpServlet {

    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        // JDBC driver name and database URL
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost:3306/javasql";

        //  Database credentials
        String USER = "root";
        String PASS = "admin";

        // Set response content type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String title = "Database Result";
        String docType
                = "<!doctype html public \"-//w3c//dtd html 4.0 "
                + "transitional//en\">\n";
        out.println(docType
                + "<html>\n"
                + "<head><title>" + title + "</title></head>\n"
                + "<body bgcolor=\"#f0f0f0\">\n"
                + "<h1 align=\"center\">" + title + "</h1>\n");
        try {
            // Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Open a connection
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Execute SQL query
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT id, name, age, salary,grade FROM employees";
            ResultSet rs = stmt.executeQuery(sql);
            JSONObject headerFile = new JSONObject();

            JSONArray listOfAttrs = new JSONArray();
            // Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                double salary = rs.getDouble("salary");
                int grade = rs.getInt("grade");
                ////////BEGIN TO WRITE JSON file//////////

                listOfAttrs.add("Employee{id : " + id);
                listOfAttrs.add("name : " + name);
                listOfAttrs.add("age : " + age);
                listOfAttrs.add("salary : " + salary);
                listOfAttrs.add("grade : " + grade);
                listOfAttrs.add("}");
                headerFile.put("Employees", listOfAttrs);
                try {

                    // Writing to a file  
                    File file = new File("D:\\Employees.json");
                    file.createNewFile();
                    FileWriter fileWriter = new FileWriter(file);

                    fileWriter.write(headerFile.toJSONString());
                    fileWriter.flush();
                    fileWriter.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                //End of writing the JSON file
                //Display values as a demo 
                out.println("ID: " + id + "<br>");
                out.println("Name: " + name + "<br>");
                out.println("Age: " + age + "<br>");
                out.println("Salary: " + salary + "<br>");
                out.println("Grade: " + grade + "<br>");

            }
            out.println("</body></html>");

            // Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
    } //end try
}
