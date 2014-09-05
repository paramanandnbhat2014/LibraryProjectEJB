/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MonashLibrary.ejb;

import MonashLibrary.models.Book;
import MonashLibrary.models.Category;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Pammy
 */
@Stateless
@LocalBean
public class CategoryService {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "MonashLibraryPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public void persist(Category c) {
        em.persist(c);
    }

    public void merge(Category c) {
        em.merge(c);
    }

    public void createCategory(Category c) {
        persist(c); // Can invoke em.persist(...) directly if desired
    }

    public void insert(Category c) {
        em.merge(c);
    }

    public void updateCategory(Category c) {
        em.merge(c);
    }

    public void deleteCategory(Category c) {
        TypedQuery<Category> query1 = em.createQuery("SELECT c FROM Category c WHERE c.ctgyId = :id", Category.class);
        query1.setParameter("id", c.getCtgyId());
        c = query1.getResultList().get(0);
        for (int i = 0; i < c.getBooks().size(); i++) {
            c.getBooks().get(i).getCategories().remove(c);
        }
        c.getBooks().clear();
        em.remove(em.merge(c));
    }

    public List<Category> getCategories() {
        //em.flush();
        TypedQuery<Category> query = em.createQuery(
                "SELECT c FROM Category c", Category.class);
        return query.getResultList();
    }
}
