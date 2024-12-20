package musicstore;

import java.util.*;
import java.rmi.RemoteException;
import javax.ejb.*;
import javax.naming.*;


public class InventoryBean implements SessionBean {

    private SessionContext ctx;

    public void setSessionContext(SessionContext ctx) {
        this.ctx = ctx;
    }

    public void ejbCreate() {
    }

    public void ejbActivate() {
    }

    public void ejbPassivate() {
    }

    public void ejbRemove() {
    }

    //
    // addInventory() methods goes here
    //
}
