/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MonashLibrary.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Pammy
 */
@Entity
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "BOOK_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookId;
    @Column(name = "BOOK_TITLE", nullable = false)
    private String title;
    @Column(name = "BOOK_AUTHOR")
    private String author;
    @Column(name = "BOOK_PUBLISHER")
    private String publisher;
    @Column(name = "BOOK_PUBDATE")
    @Temporal(TemporalType.DATE)
    private Date pubDate;
    @Column(name = "BOOK_ISBN")
    private String ISBN;
    @Column(name = "BOOK_DESCRIPTION")
    private String description;
    @Column(name = "BOOK_IMGURL")
    private String imgURL;
    @Column(name = "BOOK_AVAILABLESTATUS", nullable = false)
    private boolean AvailableStatus;
    
    //@OneToMany(mappedBy = "book", targetEntity = LoanHistory.class,fetch = FetchType.EAGER)
    //private List<LoanHistory> loans = new ArrayList<>();
    @OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "book")
    //@JoinColumn(name = "BOOK_ID")
    private List<LoanHistory> loans = new ArrayList<>();
    
    @OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "book")
    //@OneToMany(mappedBy = "book", targetEntity = Comment.class,fetch = FetchType.EAGER)
    private List<Comment> comments = new ArrayList<>();
    
    @ManyToMany(mappedBy="books",fetch=FetchType.EAGER)
    private List<Customer> customers = new ArrayList<>();   
    
    @ManyToMany(mappedBy="books",fetch=FetchType.EAGER)
    private List<Category> categories = new ArrayList<>(); 

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<LoanHistory> getLoans() {
        return loans;
    }

    public void setLoans(List<LoanHistory> loans) {
        this.loans = loans;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public boolean isAvailableStatus() {
        return AvailableStatus;
    }

    public void setAvailableStatus(boolean AvailableStatus) {
        this.AvailableStatus = AvailableStatus;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bookId != null ? bookId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Book)) {
            return false;
        }
        Book other = (Book) object;
        if ((this.bookId == null && other.bookId != null) || (this.bookId != null && !this.bookId.equals(other.bookId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MonashLibrary.models.Book[ id=" + bookId + " ]";
    }
}
