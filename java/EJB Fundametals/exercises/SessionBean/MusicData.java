package musicstore;

import java.io.Serializable;


public class MusicData implements Serializable {

    // instance variables

    public MusicData() {
        this("upc", "title", "artist", "type", (float) 0);
    }

    public MusicData(String upc, String title,
        String artist, String type, float price) {

        // set instance variables here

     }

    public String toString() {
        return "[UPC = " + upc +
               ", Title = " + title +
               ", Artist = " + artist +
               ", Type = " + type +
               ", Price = " + price +
               "]";
    }
}
