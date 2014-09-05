/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MonashLibrary;

import MonashLibrary.ejb.BookService;
import MonashLibrary.ejb.CategoryService;
import MonashLibrary.models.Book;
import MonashLibrary.models.Category;
import MonashLibrary.models.LoanHistory;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Pammy
 */
@Named(value = "bookController")
@SessionScoped
public class BookController implements Serializable {

    @EJB
    BookService bookService;
    @EJB
    CategoryService categoryService;
    private Book book;
    private Category cate;
    private List<String> categoriesIdPicked;
    private List<Category> allCategories;

    public List<String> getCategoriesIdPicked() {
        return categoriesIdPicked;
    }

    public void setCategoriesIdPicked(List<String> categoriesIdPicked) {
        this.categoriesIdPicked = categoriesIdPicked;
    }

    public List<Category> getAllCategories() {
        return allCategories;
    }

    public void setAllCategories(List<Category> allCategories) {
        this.allCategories = allCategories;
    }

    public Category getCate() {
        return cate;
    }

    public void setCate(Category cate) {
        this.cate = cate;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    /**
     * Creates a new instance of CustomerController
     */
    public BookController() {
        book = new Book();
        cate = new Category();
    }

    public String read(Book b) {
        allCategories = getCategories();
        this.book = b;
        //this.cate = b.getCategories().get(0);
// We return 'message' here since we will be invoking this in
// the 'action' property of <h:commandButton>
        return "/faces/secured/EditBook.xhtml";
    }

    public String setupCreate() {
        book = new Book();
        cate = new Category();
        allCategories = getCategories();
        return "/faces/secured/EditBook.xhtml";
    }

    public String create() {
        bookService.insert(book, categoriesIdPicked);
        return "/faces/secured/AdminDetail.xhtml";
    }

    public String update() {
        bookService.updateBook(book, categoriesIdPicked);
        return "/faces/secured/AdminDetail.xhtml";
    }

    public String delete(Book b) {
        bookService.deleteBook(b);
        book = null;
        return "/faces/secured/AdminDetail.xhtml";
    }

    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    public List<LoanHistory> getAllLoans() {
        return bookService.getAllLoans();
    }

    public List<Category> getCategories() {
        return categoryService.getCategories();
    }

    public String deleteLoan(LoanHistory l) {
        bookService.deleteLoan(l);
        l = null;
        return "/faces/secured/AdminDetail.xhtml";
    }

    public String deleteCategory(Category c) {
        bookService.deleteCategory(c);
        c = null;
        return "/faces/Books.xhtml";
    }
}
