package musicstore;

import java.rmi.*;
import javax.ejb.*;


public interface Inventory extends EJBObject {
    public boolean addInventory(MusicData[] data) throws RemoteException;
}
