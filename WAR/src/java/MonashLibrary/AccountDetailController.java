/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MonashLibrary;

import MonashLibrary.ejb.CustomerService;
import MonashLibrary.models.Book;
import MonashLibrary.models.Comment;
import MonashLibrary.models.Customer;
import MonashLibrary.models.LoanHistory;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.el.ELContext;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Pammy
 */
@Named(value = "accountDetailController")
@SessionScoped
public class AccountDetailController implements Serializable {

    //@ManagedProperty(value="#{loginController}")
    //LoginController loginController;
    @EJB
    CustomerService customerService;
    private Comment commentEdited;

    public Comment getCommentEdited() {
        return commentEdited;
    }

    public void setCommentEdited(Comment commentEdited) {
        this.commentEdited = commentEdited;
    }

    /**
     * Creates a new instance of AccountDetailController
     */
    public AccountDetailController() {
    }

    public List<Customer> getBookmarks() {
        ELContext context = FacesContext.getCurrentInstance().getELContext();
        LoginController loginController =
                (LoginController) FacesContext.getCurrentInstance()
                .getApplication()
                .getELResolver()
                .getValue(context, null, "loginController");
        return customerService.getBookmarks(loginController.getUserName(), loginController.getPassword());
    }

    public Customer getLoans() {
        ELContext context = FacesContext.getCurrentInstance().getELContext();
        LoginController loginController =
                (LoginController) FacesContext.getCurrentInstance()
                .getApplication()
                .getELResolver()
                .getValue(context, null, "loginController");
        return customerService.getLoans(loginController.getUserName(), loginController.getPassword()).get(0);
    }

    public Customer getComment() {
        return getLoans();
    }

    public Customer getBookmark() {
        return getLoans();
    }

    public List<Comment> getComments() {
        return customerService.getAllUsersComment();
    }

    public String returnBook(LoanHistory loan) {
        customerService.changeLoanReturnStatus(loan);
        return "/faces/secured/AccountDetail.xhtml";
    }

    public String delete(Comment comm) {
        customerService.deleteComment(comm);
        comm = null;
        return "/faces/secured/AccountDetail.xhtml";
    }

    public String deleteOneBookmark(Book book) {
        System.out.println("hhhhhhhhhhhhhhhhh book id");
        Customer c = getLoans();
        customerService.deleteBookmark(c, book);
        book = null;
        return "/faces/secured/AccountDetail.xhtml";
    }

    public String deleteComment(Comment comm) {
        customerService.deleteComment(comm);
        comm = null;
        return "/faces/secured/AdminDetail.xhtml";
    }

    public String edit(Comment c) {
        this.commentEdited = c;
        return "/faces/secured/EditComment.xhtml";
    }

    public String updateComment() {
        customerService.updateComment(commentEdited);
        return "/faces/secured/AdminDetail.xhtml";
    }
}
