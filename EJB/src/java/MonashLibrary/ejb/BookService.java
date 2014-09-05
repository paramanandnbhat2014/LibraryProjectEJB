/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MonashLibrary.ejb;

import MonashLibrary.models.Book;
import MonashLibrary.models.Category;
import MonashLibrary.models.LoanHistory;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Pammy
 */
@Stateless
@LocalBean
public class BookService {

    @PersistenceContext(unitName = "MonashLibraryPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public void persist(Book b) {
        em.persist(b);
    }

    public void createBook(Book b) {
        persist(b); // Can invoke em.persist(...) directly if desired
    }

    public void addBook(Book b) {
        em.merge(b);
    }

    public void updateBook(Book book, List<String> cateIds) {
        //Book book = em.find(Book.class, b.getBookId());
        Book tmp = book;
        TypedQuery<Book> query1 = em.createQuery("SELECT b FROM Book b WHERE b.bookId = :id", Book.class);
        query1.setParameter("id", book.getBookId());
        book = query1.getResultList().get(0);
        book.setTitle(tmp.getTitle());
        book.setAuthor(tmp.getAuthor());
        book.setPublisher(tmp.getPublisher());
        book.setPubDate(tmp.getPubDate());
        book.setISBN(tmp.getISBN());
        book.setDescription(tmp.getDescription());
        book.setImgURL(tmp.getImgURL());
        book.setAvailableStatus(tmp.isAvailableStatus());
        for (int i = 0; i < book.getCategories().size(); i++) {
            book.getCategories().get(i).getBooks().remove(book);
        }
        book.getCategories().clear();
        //em.merge(book);
        for (int i = 0; i < cateIds.size(); i++) {
            //Category c = em.find(Category.class, Long.parseLong(cateIds.get(i)));//find will create a copy for the object in database, even the id the same,so have a dupicate key problem
            //Category cc = new Category();
            //cc = c;                    
            //Book book = em.find(Book.class, b.getBookId());
            TypedQuery<Category> query = em.createQuery("SELECT c FROM Category c WHERE c.ctgyId = :id", Category.class);
            query.setParameter("id", Long.parseLong(cateIds.get(i)));
            //if (query.getResultList().isEmpty()) {
            //    String title = c.getCtgyTitle();
            //    c = new Category();
            //    c.setCtgyTitle(title);
//                em.persist(c);
            //em.persist(c);
            //book.getCategories().add(cate);
            //em.merge(cate);
            //em.merge(book);//Merge creates a new instance of your entity, copies the state from the supplied entity, and makes the new copy managed. The instance you pass in will not be managed (any changes you make will not be part of the transaction - unless you call merge again).
            //em.flush();
            // } else {
            //    System.out.println("xxxxxxxxxxxxxAAAAAA:" + query.getResultList().get(0).getCtgyId());
            Category c = query.getResultList().get(0);
            //em.find(Category.class, query.getResultList().get(0).getCtgyId());
            //}
            //em.persist(book);
            //book.getCategories().clear();
            //book.getCategories().clear();
            //cc.getBooks().remove(book);
            //c.getBooks().remove(book);
            //book.getCategories().remove(cc);
            book.getCategories().add(c);
            c.getBooks().add(book);
            //em.merge(cc);
            //List<Category> cs = new ArrayList<>();
            //cs.add(c);
            //book.setCategories(cs);
            //book.getCategories().clear();
            //book.getCategories().add(c);
            //c.getBooks().add(book);
            //em.merge(book);
            //em.merge(cc);
            //em.merge(c);
        }
        em.merge(book);
    }

    public void deleteBook(Book book) {
        //em.merge(book);
        TypedQuery<Book> query1 = em.createQuery("SELECT b FROM Book b WHERE b.bookId = :id", Book.class);
        query1.setParameter("id", book.getBookId());
        book = query1.getResultList().get(0);
        for (int i = 0; i < book.getLoans().size(); i++) {
            book.getLoans().get(i).setBook(null);
            book.getLoans().get(i).setCustomer(null);
        }
        for (int i = 0; i < book.getCategories().size(); i++) {
            book.getCategories().get(i).getBooks().remove(book);
        }
        book.getCategories().clear();
        //em.merge(book);
        Book t = em.merge(book);
        em.remove(t);
    }

    public void deleteCategory(Category c) {
        Category d = em.merge(c);
        em.remove(d);
        c = null;
    }

    public void deleteLoan(LoanHistory l) {
        l = em.find(LoanHistory.class, l.getLoanId());
        l.getBook().setAvailableStatus(true);
        l.getBook().getLoans().remove(l);
        l.getCustomer().getLoans().remove(l);
        LoanHistory d = em.merge(l);
        em.remove(d);
        l = null;
    }

    public List<Book> getBooks() {
        //em.flush();
        TypedQuery<Book> query = em.createQuery(
                "SELECT b FROM Book b", Book.class);
        return query.getResultList();
    }

    public List<LoanHistory> getAllLoans() {
        //em.flush();
        TypedQuery<LoanHistory> query = em.createQuery(
                "SELECT l FROM LoanHistory l WHERE l.returnStatus = false", LoanHistory.class);
        return query.getResultList();
    }

    public void insert(Book book, List<String> cates) {
        em.persist(book);//Persist takes an entity instance, adds it to the context and makes that instance managed (ie future updates to the entity will be tracked)
        //em.persist(cate);
        //em.flush();//What em.flush() does is to empty the internal SQL instructions cache, and execute it immediately to the database.
        //List<Category> cs = new ArrayList<Category>();
        //cs.add(cate);
        //List<Book> bs =  new ArrayList<Book>();
        //bs.add(book);
        //book.setCategories(cs);
        //cate.setBooks(bs);
        //em.merge(book);//Merge creates a new instance of your entity, copies the state from the supplied entity, and makes the new copy managed. The instance you pass in will not be managed (any changes you make will not be part of the transaction - unless you call merge again).
        //em.merge(cate);
        //em.flush();
        for (int i = 0; i < cates.size(); i++) {
            System.out.println("xxxxxxxxxxx" + cates.get(i));
            Category c = em.find(Category.class, Long.parseLong(cates.get(i)));
            //em.persist(c);
            book.getCategories().add(c);
            c.getBooks().add(book);
        }
        em.merge(book);
    }

    public List<Book> search(String attribute, String value) {
        TypedQuery<Book> query;
        switch (attribute) {
            case "title":
                query = em.createQuery("SELECT b FROM Book b WHERE UPPER(b.title) LIKE UPPER(:value)", Book.class);
                break;
            case "ISBN":
                query = em.createQuery("SELECT b FROM Book b WHERE b.ISBN LIKE (:value)", Book.class);
                break;
            case "author":
                query = em.createQuery("SELECT b FROM Book b WHERE UPPER(b.author) LIKE UPPER(:value)", Book.class);
                break;
            default:
                query = em.createQuery("SELECT b FROM Book b WHERE UPPER(b.author) LIKE UPPER(:value)", Book.class);
                break;
        }
        query.setParameter("value", "%" + value + "%");
        return query.getResultList();
    }
}
