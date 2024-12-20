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

    public String ejbCreate(String upc) {
        this.upc = upc;
        return null;
    }

    public void ejbPostCreate(String upc) {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

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

