package musicstore;

import java.rmi.RemoteException;
import javax.ejb.*;


public interface InventoryHome extends EJBHome {
    public Inventory create() throws CreateException, RemoteException;
}
