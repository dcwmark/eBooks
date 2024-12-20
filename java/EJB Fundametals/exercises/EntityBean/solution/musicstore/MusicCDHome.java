package musicstore;

import java.rmi.RemoteException;
import javax.ejb.*;


public interface MusicCDHome extends EJBHome {
    public MusicCD create(String upc)
                   throws CreateException, RemoteException;
    public MusicCD findByPrimaryKey(String pk)
                   throws FinderException, RemoteException;
}
