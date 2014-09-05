package MonashLibrary.models;

import MonashLibrary.models.Book;
import MonashLibrary.models.Customer;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-09-02T09:18:55")
@StaticMetamodel(Comment.class)
public class Comment_ { 

    public static volatile SingularAttribute<Comment, Date> postDate;
    public static volatile SingularAttribute<Comment, Book> book;
    public static volatile SingularAttribute<Comment, Long> commId;
    public static volatile SingularAttribute<Comment, String> commInfo;
    public static volatile SingularAttribute<Comment, Customer> customer;

}