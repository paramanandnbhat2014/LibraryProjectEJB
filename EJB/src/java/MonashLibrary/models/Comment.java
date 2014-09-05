/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MonashLibrary.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Pammy
 */
@Entity
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "COMM_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commId;
    @Column(name = "COMM_INFO", nullable = false)
    private String commInfo;
    @Column(name = "COMM_POSTDATE")
    @Temporal(TemporalType.DATE)
    private Date postDate;
    //@Column(name = "BOOK_ID")   
    //private long bookId;  //forergn key to book table
    //@ManyToOne(optional = false)
    //@JoinColumn(name = "BOOK_ID", referencedColumnName = "BOOK_ID",insertable=false, updatable=false)
    @ManyToOne
    @JoinColumn(name = "BOOK_ID")
    private Book book;
    //ALTER TABLE COMMENT ADD CONSTRAINT FK_COMMENT_BOOK_ID FOREIGN KEY (BOOK_ID) REFERENCES BOOK (BOOK_ID)
    //@Column(name = "CUST_ID")
    //private long custId;  //foreign key to customer table
    @ManyToOne
    @JoinColumn(name = "CUST_ID")
    //@ManyToOne(optional = false)
    //@JoinColumn(name = "CUST_ID", referencedColumnName = "CUST_ID", insertable = false, updatable = false)
    private Customer customer;

    //ALTER TABLE COMMENT ADD CONSTRAINT FK_COMMENT_CUST_ID FOREIGN KEY (CUST_ID) REFERENCES CUSTOMER (CUST_ID)
    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public Long getCommId() {
        return commId;
    }

    public void setCommId(Long commId) {
        this.commId = commId;
    }

    public String getCommInfo() {
        return commInfo;
    }

    public void setCommInfo(String commInfo) {
        this.commInfo = commInfo;
    }
/*
    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }
*/
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
/*
    public long getCustId() {
        return custId;
    }

    public void setCustId(long custId) {
        this.custId = custId;
    }
*/
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (commId != null ? commId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comment)) {
            return false;
        }
        Comment other = (Comment) object;
        if ((this.commId == null && other.commId != null) || (this.commId != null && !this.commId.equals(other.commId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MonashLibrary.models.Comment[ id=" + commId + " ]";
    }
}
