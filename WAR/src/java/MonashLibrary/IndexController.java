/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MonashLibrary;

import MonashLibrary.ejb.BookService;
import MonashLibrary.ejb.CategoryService;
import MonashLibrary.models.Book;
import MonashLibrary.models.Category;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Pammy
 */
@Named(value = "indexController")
@ApplicationScoped
public class IndexController {

    @EJB
    private BookService bookService;
    @EJB
    private CategoryService categoryService;

    /**
     * Creates a new instance of IndexController
     */
    public IndexController() {
    }

    @PostConstruct
    public void init(){
        this.clearData();

        addNewBook("Eyrie", "Tim Winton", "Random House", "26-10-2013", "Hot sale", "1237181726111", "http://www.dymocks.com.au/ImageHandler.ashx?w=200&q=9781926428536",
                "The Signature Of All Things", "Tim Winton", "Random House", "16-11-2013", "Latest Book", "1018172635161", "http://www.dymocks.com.au/ImageHandler.ashx?w=200&q=9781408850114",
                "Minor Adjustment Salon", "McCall Smith", "Australia", "08-10-2013", "Latest Book", "9780385608282", "http://www.dymocks.com.au/ImageHandler.ashx?w=200&q=9781408704318",
                "History", "Sport");
        addNewBook("Doctor Sleep", "Stephen King", "Random House", "22-12-2013", "Hot sale", "1019283647281", "http://www.dymocks.com.au/ImageHandler.ashx?w=200&q=9781444761177",
                "Bittersweet", "Wilbur Smith", "Random House", "22-12-2013", "Latest Book", "1253618290181", "http://www.dymocks.com.au/ImageHandler.ashx?w=200&q=9780732298203",
                "WeirDo", "Anh Do", "Scholastic", "01-10-2013", "Hot sale", "9781742837581", "http://www.dymocks.com.au/ImageHandler.ashx?w=200&q=9781742837581",
                "Fiction", "Advertisement");
        addNewBook("Vicious Circle", "Wilbur Smith", "Random House", "13-09-2012", "Hot sale", "1829182730122", "http://www.dymocks.com.au/ImageHandler.ashx?w=200&q=9781447250128",
                "Michael Hussey", "Michael Hussey", "Random House", "26-09-2013", "Latest Book", "2910292828101", "http://www.dymocks.com.au/ImageHandler.ashx?w=200&q=9781742706573",
                "One Summer", "Bryson Bill", "Random House", "26-09-2013", "Latest Book", "9780385608282", "http://www.dymocks.com.au/ImageHandler.ashx?w=200&q=9780385608282",
                "Children", "Health");
        addNewBook("The Longest Ride", "Nicholas Sparks", "Paul Barry", "26-09-2013", "Hot sale", "0982783728292", "http://www.dymocks.com.au/ImageHandler.ashx?w=200&q=9780751549959",
                "Breaking News", "Paul Barry", "Random House", "13-10-2013", "Latest Book", "2029384958272", "http://www.dymocks.com.au/ImageHandler.ashx?w=200&q=9781741759785",
                "Allegiant", "Veronica Roth", "Random House", "19-12-2013", "Hot sale", "2029382902928", "http://www.dymocks.com.au/ImageHandler.ashx?w=200&q=9780007534944",
                "Art", "Magazine");
    }

    public void clearData() {
        List<Book> books = bookService.getBooks();
        for (int i = 0; i < books.size(); i++) {
            bookService.deleteBook(books.get(i));
        }
        List<Category> cates = categoryService.getCategories();
        for (int i = 0; i < cates.size(); i++) {
            categoryService.deleteCategory(cates.get(i));
        }
    }

    public void addNewBook(String title1,
            String author1,
            String publisher1,
            String pubDate1,
            String desc1,
            String ISBN1,
            String imgURL1,
            String title2,
            String author2,
            String publisher2,
            String pubDate2,
            String desc2,
            String ISBN2,
            String imgURL2,
            String title3,
            String author3,
            String publisher3,
            String pubDate3,
            String desc3,
            String ISBN3,
            String imgURL3,
            String cateTitle1,
            String cateTitle2){
        Book b1 = new Book();
        b1.setTitle(title1);
        b1.setAuthor(author1);
        b1.setPublisher(publisher1);
 //     Date date1 = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).parse(pubDate1);
 //     b1.setPubDate(date1);
        b1.setDescription(desc1);
        b1.setISBN(ISBN1);
        b1.setImgURL(imgURL1);
        b1.setAvailableStatus(true);

        Book b2 = new Book();
        b2.setTitle(title2);
        b2.setAuthor(author2);
        b2.setPublisher(publisher2);
//        Date date2 = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).parse(pubDate2);
//        b2.setPubDate(date2);
        b2.setDescription(desc2);
        b2.setISBN(ISBN2);
        b2.setImgURL(imgURL2);
        b2.setAvailableStatus(true);

        Book b3 = new Book();
        b3.setTitle(title3);
        b3.setAuthor(author3);
        b3.setPublisher(publisher3);
  //      Date date3 = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).parse(pubDate3);
  //      b2.setPubDate(date3);
        b3.setDescription(desc3);
        b3.setISBN(ISBN3);
        b3.setImgURL(imgURL3);
        b3.setAvailableStatus(true);

        Category c1 = new Category();
        c1.setCtgyTitle(cateTitle1);
        //categoryService.persist(c);

        b1.getCategories().add(c1);
        c1.getBooks().add(b1);

        b2.getCategories().add(c1);
        c1.getBooks().add(b2);

        b3.getCategories().add(c1);
        c1.getBooks().add(b3);
        //categoryService.merge(c1);

        Category c2 = new Category();
        c2.setCtgyTitle(cateTitle2);
        //categoryService.persist(c);

        b1.getCategories().add(c2);
        c2.getBooks().add(b1);

        b2.getCategories().add(c2);
        c2.getBooks().add(b2);

        b3.getCategories().add(c2);
        c2.getBooks().add(b3);

        categoryService.merge(c2);
        //bookService.addBook(b1);
        //bookService.addBook(b2);

    }

    public List<Book> getBooks() {
        return bookService.getBooks();
    }
}
