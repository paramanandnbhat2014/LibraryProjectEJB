/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MonashLibrary;

import MonashLibrary.ejb.BookService;
import MonashLibrary.ejb.CustomerService;
import MonashLibrary.models.Book;
import MonashLibrary.models.Customer;
import MonashLibrary.models.LoanHistory;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;

/**
 *
 * @author Pammy
 */
@Named(value = "bookDetailController")
@RequestScoped
public class BookDetailController implements Serializable {

    private int bookId;
    private int cateId;
    private Book book;
    private boolean checked;
    private String pageFrom;
    private String commentInfo;
    
    @EJB
    private BookService bookService;

    public String getCommentInfo() {
        return commentInfo;
    }

    public void setCommentInfo(String commentInfo) {
        this.commentInfo = commentInfo;
    }

    public int getCateId() {
        return cateId;
    }

    public void setCateId(int cateId) {
        this.cateId = cateId;
    }

    public String getPageFrom() {
        return pageFrom;
    }

    public void setPageFrom(String pageFrom) {
        this.pageFrom = pageFrom;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
        //addBookmark(checked);
    }

    public String addBookmark() throws IOException {
        //this.initiate();
        System.out.println("000000000000000000000000000000");
        Customer c = getCurrentAuthCustomer();
        if (c != null) {
            customerService.createBookmark(c, book);
            System.out.println("yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy" + book.getBookId());
            return "/faces/BookDetail.xhtml";
            //ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            //context.redirect(context.getRequestContextPath() + "/faces/BookDetail.xhtml");
        } else {
            return "/faces/Login.xhtml";
            //ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            //context.redirect(context.getRequestContextPath() + "/faces/Login.xhtml");
        }
    }

    public boolean readBookmark(){
         Customer c = getCurrentAuthCustomer();
         if(c == null)
             return true;
         else{
             if(customerService.readBookmarkRecord(c,book))
                 return true;
             else
                 return false;
         }
    }
    
    public Customer getCurrentAuthCustomer() {
        ELContext context = FacesContext.getCurrentInstance().getELContext();
        LoginController loginController =
                (LoginController) FacesContext.getCurrentInstance()
                .getApplication()
                .getELResolver()
                .getValue(context, null, "loginController");
        return customerService.getCurrentCustomer(loginController.getUserName(), loginController.getPassword());
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
    /**
     * Creates a new instance of BookDetailController
     */
    //@ManagedProperty(value = "#{loginController}")
    //@Inject
    //private LoginController loginController;
    @EJB
    private CustomerService customerService;

    public BookDetailController() {
        bookId = 0;
        this.getCurrentBook();
    }

    public Book getCurrentBook() {
        if (bookId == 0) {
            bookId = Integer.valueOf(FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getRequestParameterMap()
                    .get("bookID"));
            pageFrom = FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getRequestParameterMap()
                    .get("pageFrom");
            System.out.println("############# current page from:" + pageFrom);
            System.out.println("############# current book id:" + bookId);
            // Assign movie based on the id provided
            book = getBook();
        }
        
        return book;
    }

    public Book getBook() {
// Get application context bean MovieApplication
        ELContext context = FacesContext.getCurrentInstance().getELContext();
        SearchController app;
        BookController app1;
        IndexController app2;
        if (pageFrom.equals("search")) {
            app = (SearchController) FacesContext.getCurrentInstance()
                    .getApplication()
                    .getELResolver()
                    .getValue(context, null, "searchController");
            return app.getBooks().get(bookId);
        } else if (pageFrom.equals("books")) {
            cateId = Integer.valueOf(FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getRequestParameterMap()
                    .get("cateID"));
            System.out.println("############# current cate ID:" + cateId);
            app1 = (BookController) FacesContext.getCurrentInstance()
                    .getApplication()
                    .getELResolver()
                    .getValue(context, null, "bookController");
            return app1.getCategories().get(cateId).getBooks().get(bookId);
        } else {
            app2 = (IndexController) FacesContext.getCurrentInstance()
                    .getApplication()
                    .getELResolver()
                    .getValue(context, null, "indexController");
            return app2.getBooks().get(bookId);
        }
// -1 to movieId since we +1 in JSF (to always have positive movie id!)

    }

    public String postComment() {
        Customer c = getCurrentAuthCustomer();
        if (c != null) {
            //System.out.println("############# current cust id:" + c.getCustId());
            //System.out.println("############# current cust Name:" + c.getUserName());
            //System.out.println("############# current Paaword:" + c.getPassWord());
            //System.out.println("############# current book id:" + book.getBookId());
            //System.out.println("############# current book title:" + book.getTitle());
            customerService.createComment(c, book,commentInfo);
            return "/faces/BookDetail.xhtml";
        } else {
            return "/faces/Login.xhtml";
        }
    }
    
        public String doLoan() {
        Customer c = getCurrentAuthCustomer();
        if (c != null) {
            System.out.println("############# current cust id:" + c.getCustId());
            System.out.println("############# current cust Name:" + c.getUserName());
            System.out.println("############# current Paaword:" + c.getPassWord());
            System.out.println("############# current book id:" + book.getBookId());
            System.out.println("############# current book title:" + book.getTitle());
            customerService.createLoanRecord(c, book);
            return "/faces/secured/LoanConfirm.xhtml";
        } else {
            return "/faces/Login.xhtml";
        }
    }
}
