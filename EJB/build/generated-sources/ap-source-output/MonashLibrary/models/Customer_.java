package MonashLibrary.models;

import MonashLibrary.models.Book;
import MonashLibrary.models.Comment;
import MonashLibrary.models.LoanHistory;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-09-02T09:18:55")
@StaticMetamodel(Customer.class)
public class Customer_ { 

    public static volatile SingularAttribute<Customer, Long> custId;
    public static volatile ListAttribute<Customer, LoanHistory> loans;
    public static volatile ListAttribute<Customer, Book> books;
    public static volatile SingularAttribute<Customer, String> phone;
    public static volatile SingularAttribute<Customer, String> email;
    public static volatile SingularAttribute<Customer, String> userName;
    public static volatile SingularAttribute<Customer, String> isAdmin;
    public static volatile SingularAttribute<Customer, String> fullName;
    public static volatile SingularAttribute<Customer, String> passWord;
    public static volatile ListAttribute<Customer, Comment> comments;

}