package musicstore;

import java.io.Serializable;


public class MusicData implements Serializable {

    String upc;
    String title;
    String artist;
    String type;
    float  price;

    public MusicData() {
        this("upc", "title", "artist", "type", (float) 0);
    }

    public MusicData(String upc, String title,
                     String artist, String type, float price) {
        this.upc = upc;
        this.title = title;
        this.artist = artist;
        this.type = type;
        this.price = price;
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
