/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MonashLibrary;

import MonashLibrary.ejb.CustomerService;
import MonashLibrary.models.Customer;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author Pammy
 */
@Named(value = "registrationController")
@RequestScoped
public class RegistrationController {

    @EJB
    CustomerService customerService;
    /**
     * Creates a new instance of RegistrationController
     */
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public RegistrationController() {
        customer = new Customer();
    }

    public String create() {
        customerService.createCustomer(customer);
        return "/faces/Login.xhtml";
    }
}
