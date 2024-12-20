package musicstore;

import java.rmi.RemoteException;
import javax.ejb.*;
import javax.rmi.PortableRemoteObject;
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

    public boolean addInventory(MusicData[] data) {
        Context     c    = null;
        MusicCDHome home = null;
        MusicCD     cd   = null;

        try {
            c = new InitialContext();
        }
        catch (Exception e) {
            System.out.println("InventoryBean: can't get initial context.");
            return false;
        }
        try {
            Object ref = c.lookup("java:comp/env/ejb/MusicCD");
            home = (MusicCDHome) PortableRemoteObject.narrow(ref, MusicCDHome.class);
        }
        catch (Exception e) {
            System.out.println("InventoryBean: can't look up MusicCDHome.");
            return false;
        }
        for (int i=0; i<data.length; i++) {
            try {
                cd = home.create(data[i].upc);
            }
            catch (Exception e) {
                System.out.println("Can't create CD: " + data[i]);
                return false;
            }
            try {
                cd.setTitle(data[i].title);
                cd.setArtist(data[i].artist);
                cd.setType(data[i].type);
                cd.setPrice(data[i].price);
            }
            catch (Exception e) {
                System.out.println("Can't update CD: " + data[i]);
                return false;
            }
        }
        return true;
    }
}
