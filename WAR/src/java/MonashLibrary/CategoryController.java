/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MonashLibrary;

import MonashLibrary.ejb.CategoryService;
import MonashLibrary.models.Book;
import MonashLibrary.models.Category;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Pammy
 */
@Named(value = "categoryController")
@SessionScoped
public class CategoryController implements Serializable {

    /**
     * Creates a new instance of CategoryController
     */
    @EJB
    private CategoryService categoryService;
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public CategoryController() {
    }

    public String create() {
        categoryService.insert(category);
        return "/faces/secured/AdminDetail.xhtml";
    }

    public String update() {
        categoryService.updateCategory(category);
        return "/faces/secured/AdminDetail.xhtml";
    }

    public List<Category> getCategories() {
        return categoryService.getCategories();
    }

    public String read(Category c) {
        this.category = c;
        return "/faces/secured/EditCategory.xhtml";
    }

    public String setupCreate() {
        category = new Category();
        return "/faces/secured/EditCategory.xhtml";
    }

    public String delete(Category c) {
        categoryService.deleteCategory(c);
        category = null;
        return "/faces/secured/AdminDetail.xhtml";
    }
}
