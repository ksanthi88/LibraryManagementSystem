package daointerface;

import java.sql.*;

public class ConnectionDao {
    static Connection connection=null;
    protected PreparedStatement ps=null;
    protected ResultSet rs=null;
    public static Connection getConnection()throws ClassNotFoundException{
        final String  DBURL  = "jdbc:mysql://localhost:3306/library";
        final String DBUSERNAME = "root";
        final String  DBPASSWORD = "santhiJava1";
        try {
            connection = DriverManager.getConnection(DBURL, DBUSERNAME, DBPASSWORD);
            System.out.println("Connected Database Successfully");
        }
        catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        }
return connection;
    }
    public void disconnect()
    {
        try {
            if(rs != null)
            {
                rs.close();
            }
            if(ps != null)
            {
                ps.close();
            }
            if(connection != null)
            {
                connection.close();
            }

        }catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }

    }
}


