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
public class LoanHistory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "LOAN_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long loanId;
    @Column(name = "LOAN_BORROWDATE")
    @Temporal(TemporalType.DATE)
    private Date borrowDate;
    @Column(name = "LOAN_RETURNDATE")
    @Temporal(TemporalType.DATE)
    private Date returnDate;
    @Column(name = "LOAN_RETURNSTATUS", nullable = false)
    private boolean returnStatus;
    @ManyToOne
    @JoinColumn(name = "BOOK_ID")
    private Book book;
    @ManyToOne
    @JoinColumn(name = "CUST_ID")
    private Customer customer;

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
    
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /*    
     @Column(name = "BOOK_ID")   
     private long bookId;  //forergn key to book table,declare a new column
    
     //name means the Colomun in laonhistory entity, referencedColumnName means the column in Book ENTITY
     @ManyToOne(optional = false)
     @JoinColumn(name = "BOOK_ID", referencedColumnName = "BOOK_ID",insertable=false, updatable=false)
     private Book book; //declare the constraint name in database
     //@ManyToOne is used to declare the constraint in sql, not create a new cllumn,is equal to:
     //"ALTER TABLE LOANHISTORY ADD CONSTRAINT LOANHISTORYBOOK_ID FOREIGN KEY (BOOK_ID) REFERENCES BOOK (BOOK_ID)"
    
    
     @Column(name = "CUST_ID")
     private long custId;  //foreign key to customer table
    
     @ManyToOne(optional = false)
     @JoinColumn(name = "CUST_ID", referencedColumnName = "CUST_ID",insertable=false, updatable=false)
     private Customer customer;
     //ALTER TABLE LOANHISTORY ADD CONSTRAINT LOANHISTORYCUST_ID FOREIGN KEY (CUST_ID) REFERENCES CUSTOMER (CUST_ID)

     public long getCustId() {
     return custId;
     }

     public void setCustId(long custId) {
     this.custId = custId;
     }

     public Customer getCustomer() {
     return customer;
     }

     public void setCustomer(Customer customer) {
     this.customer = customer;
     }

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

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public boolean isReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(boolean returnStatus) {
        this.returnStatus = returnStatus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (loanId != null ? loanId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LoanHistory)) {
            return false;
        }
        LoanHistory other = (LoanHistory) object;
        if ((this.loanId == null && other.loanId != null) || (this.loanId != null && !this.loanId.equals(other.loanId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MonashLibrary.models.LoanHistory[ id=" + loanId + " ]";
    }
}
