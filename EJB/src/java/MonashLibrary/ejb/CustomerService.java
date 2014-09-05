/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MonashLibrary.ejb;

import MonashLibrary.models.Book;
import MonashLibrary.models.Comment;
import MonashLibrary.models.Customer;
import MonashLibrary.models.LoanHistory;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

/**
 *
 * @author Pammy
 */
@Stateless
@LocalBean
public class CustomerService {

    @PersistenceContext(unitName = "MonashLibraryPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public void persist(Customer c) {
        em.persist(c);
    }

    public void createCustomer(Customer c) {
        persist(c); // Can invoke em.persist(...) directly if desired
    }

    public void updateCustomer(Customer c) {
        em.merge(c);
    }

    public void deleteCustomer(Customer c) {
        em.remove(em.merge(c));
    }

    public List<Customer> getCustomers() {
        TypedQuery<Customer> query = em.createQuery(
                "SELECT c FROM Customer c", Customer.class);
        return query.getResultList();
    }

    public Customer getCurrentCustomer(String userName, String password) {
        Query query = em.createQuery(
                "SELECT c FROM Customer c WHERE c.userName = :userName AND c.passWord = :password", Customer.class);
        query.setParameter("userName", userName);
        query.setParameter("password", password);
        if (!query.getResultList().isEmpty()) {
            return (Customer) query.getResultList().get(0);
        } else {
            return null;
        }
    }

    public List<Customer> getBookmarks(String userName, String password) {
        TypedQuery<Customer> query = em.createQuery(
                "SELECT c FROM Customer c WHERE c.userName = :userName AND c.passWord = :password", Customer.class);
        query.setParameter("userName", userName);
        query.setParameter("password", password);
        return query.getResultList();
    }

    public List<Customer> getLoans(String userName, String password) {
        TypedQuery<Customer> query = em.createQuery(
                "SELECT c FROM Customer c WHERE c.userName = :userName AND c.passWord = :password", Customer.class);
        query.setParameter("userName", userName);
        query.setParameter("password", password);
        return query.getResultList();
    }

    public List<Comment> getAllUsersComment() {
        TypedQuery<Comment> query = em.createQuery(
                "SELECT c FROM Comment c", Comment.class);
        return query.getResultList();
    }

    public void createLoanRecord(Customer c, Book b) {
        LoanHistory l = new LoanHistory();
        l.setReturnStatus(false);
        Date date = new Date();
        l.setBorrowDate(date);
        l.setReturnDate(addNDays(date,14));
        em.persist(l);
        b.setAvailableStatus(false);
        l.setBook(b);
        l.setCustomer(c);
        b.getLoans().add(l);
        c.getLoans().add(l);
        em.merge(l);
        em.merge(b);
        em.merge(c);
    }
    
     public Date addNDays(Date date, int days) { 
        Calendar cal = Calendar.getInstance(); 
        cal.setTime(date); 
        cal.add(Calendar.DATE, days);  
        return cal.getTime(); 
    }   

    public void createBookmark(Customer c, Book b) {
        Book book = em.find(Book.class, b.getBookId());
        //if (checked) {
        c = em.find(Customer.class, c.getCustId());
        if (!book.getCustomers().contains(c)) {
            System.out.println("bbbbbbbbbbb" + b.getBookId());
            System.out.println("ccccccccccc" + c.getCustId());
            //TypedQuery<Book> query = em.createQuery(
            //        "SELECT b FROM Bookmark b WHERE b.bookId = :bookId AND b.ctgyId = :ctgyId", Book.class);
            //query.setParameter("id", b.getBookId());
            //Book book = query.getResultList().get(0);           
            c.getBooks().add(book);
            book.getCustomers().add(c);
            em.merge(c);
            //return true;
        } else {
            c.getBooks().remove(book);
            book.getCustomers().remove(c);
            em.merge(c);
            //return false;
        }
    }

    public boolean readBookmarkRecord(Customer c, Book b) {
        Book book = em.find(Book.class, b.getBookId());
        c = em.find(Customer.class, c.getCustId());
        if (!book.getCustomers().contains(c)) {
            return true;
        } else {
            return false;
        }
    }

    public void changeLoanReturnStatus(LoanHistory loan) {
        loan = em.find(LoanHistory.class, loan.getLoanId());
        loan.setReturnDate(new Date());
        loan.setReturnStatus(true);
        loan.getBook().setAvailableStatus(true);
        em.merge(loan);
    }

    public void createComment(Customer c, Book b, String commentInfo) {
        Comment ct = new Comment();
        ct.setCommInfo(commentInfo);
        ct.setPostDate(new Date());
        em.persist(ct);
        ct.setBook(b);
        ct.setCustomer(c);
        b.getComments().add(ct);
        c.getComments().add(ct);
        em.merge(ct);
        em.merge(b);
        em.merge(c);
    }

    public void deleteComment(Comment comm) {
        comm = em.find(Comment.class, comm.getCommId());
        comm.getBook().getComments().remove(comm);
        comm.getCustomer().getComments().remove(comm);
        em.remove(em.merge(comm));
    }

    public void deleteBookmark(Customer cust, Book b) {
        //TypedQuery<Book> query1 = em.createQuery("SELECT b FROM Book b WHERE b.bookId = :id", Book.class);
        //query1.setParameter("id", book.getBookId())
        if(b == null)
            System.out.println("inside #######");
        Book book = em.find(Book.class, b.getBookId());
        //book = query1.getResultList().get(0);
        Customer c = em.find(Customer.class, cust.getCustId());
        book.getCustomers().remove(c);
        c.getBooks().remove(book);
        em.merge(book);
        em.merge(c);
    }
    
    public void updateComment(Comment comm){
        String s = comm.getCommInfo();
        Comment c = em.find(Comment.class,comm.getCommId());
        c.setCommInfo(s);
        em.merge(c);
    }
}
