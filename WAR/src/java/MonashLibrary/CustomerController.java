/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MonashLibrary;

import MonashLibrary.ejb.CustomerService;
import MonashLibrary.models.Customer;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Pammy
 */
@Named(value = "customerController")
@SessionScoped
public class CustomerController implements Serializable {

    @EJB
    CustomerService customerService;
    
    private Customer customer;   

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    /**
     * Creates a new instance of CustomerController
     */
    public CustomerController() {
        customer = new Customer();
    }



    public String read(Customer c) {
        this.customer = c;
// We return 'message' here since we will be invoking this in
// the 'action' property of <h:commandButton>
        return "/faces/secured/EditCustomer.xhtml";
    }

    public String setupCreate() {
        customer = new Customer();
        return "/faces/secured/EditCustomer.xhtml";
    }

    public String create() {
        customerService.createCustomer(customer);
        return "/faces/secured/AdminDetail.xhtml";
    }

    public String update() {
        customerService.updateCustomer(customer);
        return "/faces/secured/AdminDetail.xhtml";
    }

    public String delete(Customer c) {
        customerService.deleteCustomer(c);
        return "/faces/secured/AdminDetail.xhtml";
    }

    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }
}