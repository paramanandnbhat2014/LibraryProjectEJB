package MonashLibrary.models;

import MonashLibrary.models.Book;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-09-02T09:18:55")
@StaticMetamodel(Category.class)
public class Category_ { 

    public static volatile ListAttribute<Category, Book> books;
    public static volatile SingularAttribute<Category, Long> ctgyId;
    public static volatile SingularAttribute<Category, String> ctgyTitle;

}