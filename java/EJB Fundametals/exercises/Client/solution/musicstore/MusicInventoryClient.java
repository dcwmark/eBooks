package musicstore;

import java.rmi.RemoteException;
import javax.ejb.*;
import javax.naming.*;
import musicstore.*;


public class MusicInventoryClient {

    public static void main(String[] args) {
        Context c = null;
        InventoryHome home = null;
        Inventory inv = null;
        try {
            c = new InitialContext();
        }
        catch (NamingException e) {
            System.out.println("Can't get context.");
        }
        try {
            home = (InventoryHome) c.lookup("ejb/Inventory");
        }
        catch (Exception e) {
            System.out.println("Can't look up InventoryHome.");
        }
        try {
            inv = home.create();
        }
        catch (Exception e) {
            System.out.println("Can't create new Inventory object.");
        }
        try {
            System.out.println("Building new inventory...");
            MusicData[] md = new MusicData[3];
            md[0] = new MusicData("724382783824", "Richard Elliot",
                                  "After Dark", "Jazz", (float) 12.99);
            md[1] = new MusicData("16126513829", "Karla Bonoff",
                                  "New World", "Rock", (float) 11.49);
            md[2] = new MusicData("724387755925", "Cusco",
                                  "Apurimac II", "New Age", (float) 12.99);
            System.out.println("Submitting new inventory...");
            boolean success = inv.addInventory(md);
            if (success)
                System.out.println("New inventory added to database.");
            else
                System.out.println("Can't add new inventory.");
        }
        catch (Exception e) {
            System.out.println("Exception adding inventory.");
        }
    }
}
