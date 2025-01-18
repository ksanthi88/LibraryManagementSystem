package controller;

import daointerface.BookDao;
import daointerface.ConnectionDao;
import model.Books;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl extends ConnectionDao implements BookDao {
    @Override
    public void saveBook(List<Books> booksList) {
try{
    Connection con=ConnectionDao.getConnection();
    for(Books book:booksList){
        String sqlquery="insert into books (isbn,bookName) values(?,?)";
        PreparedStatement ps=con.prepareStatement(sqlquery);
        ps.setInt(1,book.getIsbn());
        ps.setString(2,book.getBookName());
        int afftectedRows=ps.executeUpdate();
        System.out.println(afftectedRows + " row(s) affected !!");
    }
}catch(ClassNotFoundException e){

}catch(SQLException throwables){
    throwables.printStackTrace();
}
    }


    @Override
    public List<Books> getAllBooks() {
        try{
            Connection con= ConnectionDao.getConnection();
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from books");
            List<Books> booksList=new ArrayList<>();
           while(rs.next()){
               Books book=new Books();
               book.setIsbn((rs.getInt("isbn")));
               book.setBookName((rs.getString("bookName")));
               booksList.add(book);
           }
           return booksList;
        }catch (SQLException e){
            e.printStackTrace();
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }

        return null;
    }


    @Override
    public boolean updateBook(Books book, int id) {
        try{
            Connection con=ConnectionDao.getConnection();
            PreparedStatement ps=con.prepareStatement("Update books set isbn=?,bookName=? where id=?");
            ps.setInt(1,book.getIsbn());
            ps.setString(2,book.getBookName());
            ps.setInt(3,id);
            int i=ps.executeUpdate();
            if(i==1){
                return true;
            }
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());

        }
        return false;
    }
    @Override
    public boolean deleteBook(int id) {
        try{
            Connection con=ConnectionDao.getConnection();
            PreparedStatement ps= con.prepareStatement("Delete from books where id=?");
            ps.setInt(1,id);
            int i= ps.executeUpdate();
            if(i==1){
                return true;
            }
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return false;
    }
}
