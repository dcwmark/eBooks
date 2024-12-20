package musicstore;

import java.rmi.RemoteException;
import javax.ejb.*;


public class MusicCDBean implements EntityBean {

    transient private EntityContext ctx;

    public String upc;
    public String title;
    public String artist;
    public String type;
    public float  price;


    // Need to implement the ejbCreate method
    // corresponding to the create method defined in
    // MusicCDHome here


    public void ejbPostCreate(String upc) {
    }


    // Need to implement the accessor and mutator
    // methods defined in MusicCD here


    public void setEntityContext(EntityContext ctx) {
        this.ctx = ctx;
    }

    public void unsetEntityContext() {
        ctx = null;
    }

    public void ejbActivate() {
    }

    public void ejbPassivate() {
    }

    public void ejbLoad() {
    }

    public void ejbStore() {
    }

    public void ejbRemove() {
    }
}
