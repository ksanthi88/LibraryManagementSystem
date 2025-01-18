package runner;

import controller.BookDaoImpl;
import daointerface.BookDao;
import model.Books;

import java.awt.print.Book;
import java.sql.SQLException;
import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class AccessBook {
    public static void main(String[] args) {
        BookDao bookDao = new BookDaoImpl();
        System.out.println("--------- inserting book records ----------");

        ArrayList<Books> BookList = new ArrayList<Books>();

        Books b1 = new Books();
        b1.setIsbn(120);
        b1.setBookName("Java Book");
        BookList.add(b1);

        Books b2 = new Books();
        b2.setIsbn(300);
        b2.setBookName("Python Book");
        BookList.add(b2);

        Books b3 = new Books();
        b3.setIsbn(365);
        b3.setBookName("JavaScript Book");
        BookList.add(b3);

        Books b4 = new Books();
        b4.setIsbn(256);
        b4.setBookName("SQL Book");
        BookList.add(b4);

        bookDao.saveBook(BookList);
//Let's get all the books from the database. We can invoke/use the getAllBooks()
        System.out.println( " ====== Display list of all books ====");
        for (Books cc : bookDao.getAllBooks()) {
            int ISBN = cc.getIsbn();
            String BookName = cc.getBookName();
            System.out.println("======================");
            System.out.println("ISBN Number :" + ISBN + "and Book name: " + BookName);

        }
//We can invoke/use updateBook (Books bookObj, int id) method
        System.out.println("----Book information is updating -----");
        Books Bookupdating = new Books();
        Bookupdating.setIsbn(3);
        Bookupdating.setBookName("Algorithms Book");
        boolean result = bookDao.updateBook(Bookupdating, 3);
        System.out.println(result);


    }
}