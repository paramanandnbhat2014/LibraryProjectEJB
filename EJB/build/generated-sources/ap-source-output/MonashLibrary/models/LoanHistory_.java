package MonashLibrary.models;

import MonashLibrary.models.Book;
import MonashLibrary.models.Customer;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-09-02T09:18:55")
@StaticMetamodel(LoanHistory.class)
public class LoanHistory_ { 

    public static volatile SingularAttribute<LoanHistory, Book> book;
    public static volatile SingularAttribute<LoanHistory, Boolean> returnStatus;
    public static volatile SingularAttribute<LoanHistory, Customer> customer;
    public static volatile SingularAttribute<LoanHistory, Date> borrowDate;
    public static volatile SingularAttribute<LoanHistory, Date> returnDate;
    public static volatile SingularAttribute<LoanHistory, Long> loanId;

}