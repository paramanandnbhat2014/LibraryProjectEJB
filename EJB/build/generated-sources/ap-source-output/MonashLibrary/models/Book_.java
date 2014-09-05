package MonashLibrary.models;

import MonashLibrary.models.Category;
import MonashLibrary.models.Comment;
import MonashLibrary.models.Customer;
import MonashLibrary.models.LoanHistory;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-09-02T09:18:55")
@StaticMetamodel(Book.class)
public class Book_ { 

    public static volatile ListAttribute<Book, LoanHistory> loans;
    public static volatile SingularAttribute<Book, String> author;
    public static volatile SingularAttribute<Book, Date> pubDate;
    public static volatile SingularAttribute<Book, String> title;
    public static volatile SingularAttribute<Book, String> description;
    public static volatile SingularAttribute<Book, String> ISBN;
    public static volatile SingularAttribute<Book, Boolean> AvailableStatus;
    public static volatile SingularAttribute<Book, String> imgURL;
    public static volatile SingularAttribute<Book, Long> bookId;
    public static volatile ListAttribute<Book, Category> categories;
    public static volatile ListAttribute<Book, Customer> customers;
    public static volatile ListAttribute<Book, Comment> comments;
    public static volatile SingularAttribute<Book, String> publisher;

}