/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MonashLibrary.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 *
 * @author Pammy
 */
@Entity
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "CUST_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long custId;
    @Column(name = "CUST_USERNAME", nullable = false)
    private String userName;
    @Column(name = "CUST_PASSWORD", nullable = false)
    private String passWord;
    @Column(name = "CUST_FULLNAME")
    private String fullName;
    @Column(name = "CUST_PHONE")
    @Pattern(regexp = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$",
            message = "Invalid.phonenumber format, Example: 033 213 2224")
    private String phone;
    @Column(name = "CUST_EMAIL", nullable = false)
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
            + "[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
            + "(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
            message = "Invalid.email format, Example: 123@123.com")
    private String email;
    @Column(name = "CUST_ISADMIN")
    private String isAdmin;
    //@OneToMany(cascade=CascadeType.ALL, mappedBy = "customer", targetEntity = LoanHistory.class,fetch = FetchType.EAGER)
    //private List<LoanHistory> loans = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "customer")
    //@JoinColumn(name = "CUST_ID")
    private List<LoanHistory> loans = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "customer")
    //@OneToMany(cascade=CascadeType.ALL, mappedBy = "customer", targetEntity = Comment.class,fetch = FetchType.EAGER)
    private List<Comment> comments = new ArrayList<>();
    //NAME IS COLUMN NAME, REFERENCECOLUMNNAME IS COLUMN NAME IN PARENT TABLE
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "BOOKMARK",
            joinColumns =
            @JoinColumn(name = "CUST_ID", referencedColumnName = "CUST_ID"),
            inverseJoinColumns =
            @JoinColumn(name = "BOOK_ID", referencedColumnName = "BOOK_ID"))
    private List<Book> books = new ArrayList<>();

    //CREATE TABLE BOOKMARK (CUST_ID BIGINT NOT NULL, BOOK_ID BIGINT NOT NULL, PRIMARY KEY (CUST_ID, BOOK_ID))
    //ALTER TABLE BOOKMARK ADD CONSTRAINT BOOKMARK_CUST_ID FOREIGN KEY (CUST_ID) REFERENCES CUSTOMER (CUST_ID)
    //ALTER TABLE BOOKMARK ADD CONSTRAINT BOOKMARK_BOOK_ID FOREIGN KEY (BOOK_ID) REFERENCES BOOK (BOOK_ID)
    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<LoanHistory> getLoans() {
        return loans;
    }

    public void setLoans(List<LoanHistory> loans) {
        this.loans = loans;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return custId;
    }

    public void setId(Long id) {
        this.custId = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (custId != null ? custId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.custId == null && other.custId != null) || (this.custId != null && !this.custId.equals(other.custId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MonashLibrary.models.Customer[ id=" + custId + " ]";
    }
}
