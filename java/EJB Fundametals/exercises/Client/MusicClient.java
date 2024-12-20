package musicstore;

import java.rmi.RemoteException;
import javax.ejb.*;
import javax.naming.*;
import javax.rmi.PortableRemoteObject;
import musicstore.*;


public class MusicClient {

    public static void main(String[] args) {
        Context     c    = null;
        MusicCDHome home = null;
        MusicCDPK   pk   = null;
        MusicCD     cd   = null, cd2 = null;
        try {
            // get InitialContext;
        }
        catch (NamingException e) {
            System.out.println("Can't get context.");
        }
        try {
            home = // lookup reference to ejb/MusicCD
        }
        catch (Exception e) {
            System.out.println("Can't look up MusicCDHome.");
        }
        //
        // put your favorite CD here (see 'music.barnesandnoble.com'):
        //
        try {
            cd = // create a new MusicCD bean with UPC code 743214270820
        }
        catch (Exception e) {
            System.out.println("Can't create new CD.");
        }
        try {
            // set the CD title to "Luna Nueva"
            // set the CD artist to "Diego Torres"
            // set the CD type to "Latin"
            // set the CD price to $12.58
        }
        catch (RemoteException e) {
            System.out.println("Can't update new CD.");
            e.printStackTrace();
        }
        try {
            pk = new MusicCDPK();
            pk.upc = "743214270820";
            cd2 = // find the MusicCD with this primary key
            System.out.println("Title: "  + cd2.getTitle());
            System.out.println("Artist: " + cd2.getArtist());
            System.out.println("Type: "   + cd2.getType());
            System.out.println("Price: "  + cd2.getPrice());
        }
        catch (Exception e) {
            System.out.println("Can't access new CD by primary key.");
            e.printStackTrace();
        }
    }
}
