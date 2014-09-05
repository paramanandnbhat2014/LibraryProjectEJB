/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MonashLibrary;

import MonashLibrary.ejb.LoginService;
import MonashLibrary.models.Customer;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Pammy
 */
@Named(value = "loginController")
@SessionScoped
public class LoginController implements Serializable {

    @EJB
    private LoginService loginService;
    
    private String userName;
    private String password;
    private boolean loggedIn = false;
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public LoginController() {
        errorMessage = "";
    }

    public String doLogin() {
        errorMessage = "";
        Customer c = new Customer();
        c.setUserName(userName);
        c.setPassWord(password);

        if (loginService.findCustomer(c)) {
            loggedIn = true;
            errorMessage = "";
            return "/index.xhtml?faces-redirect=true";
        } else {
            loggedIn = false;
            // Set login ERROR
            //errorMessage = "User name or Password is incorrect.";
            FacesMessage msg = new FacesMessage("Login error!", "ERROR MSG");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "/Login.xhtml?faces-redirect=true";
        }
    }
    
    public String doLogout(){
            userName = null;
            password = null;
            loggedIn = false;
            return "/Login.xhtml?faces-redirect=true";
    }
    
    public boolean isAdmin(){
        Customer c = new Customer();
        c.setUserName(userName);
        c.setPassWord(password);

        return loginService.isAdmin(c);
    }

    public LoginService getLoginService() {
        return loginService;
    }

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}
