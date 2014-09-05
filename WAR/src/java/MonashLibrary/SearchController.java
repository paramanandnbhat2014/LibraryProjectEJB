/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MonashLibrary;

import MonashLibrary.ejb.BookService;
import MonashLibrary.ejb.CustomerService;
import MonashLibrary.models.Book;
import MonashLibrary.models.Customer;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.el.ELContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Pammy
 */
@Named(value = "searchController")
@SessionScoped
public class SearchController implements Serializable {

    @EJB
    BookService bookService;
    
    @EJB
    CustomerService customerService;
    /**
     * Creates a new instance of SearchController
     */
    private String attribute; 
    private String value;
    private List<Book> books;
    private String resultMessage;

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    public SearchController() {
        books = new ArrayList<>();
    }
    
    public void search(){
         books = null;
         resultMessage = "";
         books = bookService.search(attribute, value);
         if(books.isEmpty())
             resultMessage = "Sorry, no book found";
         //return "/faces/Search.xhtml";         
    }
  /*  
       public String addBookmark(Book book) {
        System.out.println("addBookmark function ran.");   
        Customer c = getCurrentAuthCustomer();
        if (c != null) {
            System.out.println("customer is not null");
            customerService.createBookmark(c, book);
            return "/faces/Search.xhtml.xhtml";
        } else {
            System.out.println("lllllllllllllllll");
            return "/faces/Login.xhtml";
        }
    }
 */   
    public Customer getCurrentAuthCustomer(){ 
        ELContext context = FacesContext.getCurrentInstance().getELContext();
        LoginController loginController =
                (LoginController) FacesContext.getCurrentInstance()
                .getApplication()
                .getELResolver()
                .getValue(context, null, "loginController");
       return customerService.getCurrentCustomer(loginController.getUserName(), loginController.getPassword());
    }
}
