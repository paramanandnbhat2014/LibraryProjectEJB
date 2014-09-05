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

/**
 *
 * @author Pammy
 */
@Entity
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "CTGY_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ctgyId;

    @Column(name = "CTGY_TITLE", nullable = false)
    private String ctgyTitle;
    
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="CTGY_BOOK",
         joinColumns = @JoinColumn(name="CTGY_ID", referencedColumnName="CTGY_ID"),
         inverseJoinColumns = @JoinColumn(name="BOOK_ID", referencedColumnName="BOOK_ID")
       )
    private List<Book> books = new ArrayList<>();
    //CREATE TABLE CTGY_BOOK (CTGY_ID BIGINT NOT NULL, BOOK_ID BIGINT NOT NULL, PRIMARY KEY (CTGY_ID, BOOK_ID))
    //ALTER TABLE CTGY_BOOK ADD CONSTRAINT CTGY_BOOK_CTGY_ID FOREIGN KEY (CTGY_ID) REFERENCES CATEGORY (CTGY_ID)
    //ALTER TABLE CTGY_BOOK ADD CONSTRAINT CTGY_BOOK_BOOK_ID FOREIGN KEY (BOOK_ID) REFERENCES BOOK (BOOK_ID)
    
    
    public Long getCtgyId() {
        return ctgyId;
    }

    public void setCtgyId(Long ctgyId) {
        this.ctgyId = ctgyId;
    }

    public String getCtgyTitle() {
        return ctgyTitle;
    }

    public void setCtgyTitle(String ctgyTitle) {
        this.ctgyTitle = ctgyTitle;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
    
    


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ctgyId != null ? ctgyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Category)) {
            return false;
        }
        Category other = (Category) object;
        if ((this.ctgyId == null && other.ctgyId != null) || (this.ctgyId != null && !this.ctgyId.equals(other.ctgyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MonashLibrary.models.Category[ id=" + ctgyId + " ]";
    }
    
}
