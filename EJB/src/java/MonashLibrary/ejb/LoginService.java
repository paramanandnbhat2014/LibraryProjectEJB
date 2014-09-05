/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MonashLibrary.ejb;

import MonashLibrary.models.Customer;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;  
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Pammy
 */
@Stateless
@LocalBean

public class LoginService {
    @PersistenceContext(unitName = "MonashLibraryPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public boolean findCustomer(Customer c){
        Query q = em.createQuery(
                "SELECT c FROM Customer c WHERE c.userName = :username AND c.passWord = :password", Customer.class);
        q.setParameter("username",c.getUserName());
        q.setParameter("password",c.getPassWord());
        List<Customer> customers = q.getResultList();
        if(customers.size() > 0)
            return true;
        else
            return false;
    }
    
    public boolean isAdmin(Customer c){
        Query q = em.createQuery(
                "SELECT c FROM Customer c WHERE c.userName = :username AND c.passWord = :password AND c.isAdmin = 'true'", Customer.class);
        q.setParameter("username",c.getUserName());
        q.setParameter("password",c.getPassWord());
        List<Customer> customers = q.getResultList();
        if(customers.size() > 0)
            return true;
        else
            return false;
    }
    
    public List<Customer> getAllCustomers(){
       List<Customer> cs =  em.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
       return cs;
    }
}
