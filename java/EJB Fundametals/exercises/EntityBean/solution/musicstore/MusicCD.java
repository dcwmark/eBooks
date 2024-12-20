package musicstore;

import java.rmi.RemoteException;
import javax.ejb.*;


public interface MusicCD extends EJBObject {
    public String getTitle() throws RemoteException;
    public void   setTitle(String title) throws RemoteException;

    public String getArtist() throws RemoteException;
    public void   setArtist(String artist) throws RemoteException;

    public String getType() throws RemoteException;
    public void   setType(String type) throws RemoteException;

    public float  getPrice() throws RemoteException;
    public void   setPrice(float price) throws RemoteException;
}
