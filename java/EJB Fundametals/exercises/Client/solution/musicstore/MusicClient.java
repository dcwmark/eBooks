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
        MusicCD     cd   = null, cd2 = null;
        try {
            c = new InitialContext();
        }
        catch (NamingException e) {
            System.out.println("Can't get context.");
        }
        try {
            Object obj = c.lookup("ejb/MusicCD");
            home = (MusicCDHome) PortableRemoteObject.narrow(obj, MusicCDHome.class);
        }
        catch (Exception e) {
            System.out.println("Can't look up MusicCDHome.");
        }
        //
        // put your favorite CD here (see 'music.barnesandnoble.com'):
        //
        try {
            cd = home.create("743214270996");
        }
        catch (Exception e) {
            System.out.println("Can't create new CD.");
            System.out.println(e);
            e.printStackTrace();
        }
        try {
            cd.setTitle("Apache Indian");
            cd.setArtist("Apache Indian");
            cd.setType("Bhangra Rap");
            cd.setPrice((float) 12.58);
        }
        catch (RemoteException e) {
            System.out.println("Can't update new CD.");
            e.printStackTrace();
        }
        try {
		String mykey="743214270996";
            cd2 = home.findByPrimaryKey(mykey);
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

