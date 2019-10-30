import java.util.ArrayList;
import java.time.Duration;

/**
 * creates album objects to show length, name, and band name of songs
 * 
 * @author amacleod
 * @version 10292019
 */
public class Album {

    private String              songName;
    private String              band;
    private ArrayList<Duration> songs = new ArrayList<Duration>();

// /**
// * Create a new Album object.
// */
// public Album() {
// SongName = "";
// band = "";
// }

    /**
     * Create a new Album object.
     * 
     * @param name
     *            name of album
     * @param band
     *            name of band
     */
    public Album(String name, String band) {
        if (name == null) {
            throw new IllegalArgumentException("name cannot be null");
        }
        else if (band == null) {
            throw new IllegalArgumentException("band cannot be null");
        }
        this.songName = name;
        this.band = band;
    }


    /**
     * Get the current value of name.
     * 
     * @return The value of name for this object.
     */
    public String getName() {
        return songName;
    }


    /**
     * Set the value of name for this object.
     * 
     * @param name
     *            The new value for name.
     */
    public void setName(String name) {
        this.songName = name;
    }


    /**
     * Get the current value of band.
     * 
     * @return The value of band for this object.
     */
    public String getBand() {
        return band;
    }


    /**
     * Set the value of band for this object.
     * 
     * @param band
     *            The new value for band.
     */
    public void setBand(String band) {
        this.band = band;
    }


    // use toMinutes() % 60 to get remaining minutes
    /**
     * Gets full duration of album
     * 
     * @return full duration of album
     */
    public Duration getLength() {
        Duration completeLength = Duration.ofMinutes(0).plusSeconds(0);
        for (int i = 0; i < songs.size(); i++) {
            Duration fullDuration =
                Duration.ofMinutes(songs.get(i).toMinutes() % 60)
                    .plusSeconds(songs.get(i).getSeconds() % 60);
            completeLength = completeLength.plus(fullDuration);
        }
        return completeLength;
    }


    /**
     * Adds a song duration to song arrayList
     * 
     * @param length
     *            length of a song
     */
    public void addSong(Duration length) {
        if (length == null) {
            throw new IllegalArgumentException("length cannot be null");
        }
        songs.add(length);
    }


    /**
     * creates new list of durations
     * 
     * @return arraylist of all song durations
     */
    public ArrayList<Duration> getSongs() {
        ArrayList<Duration> newList = new ArrayList<Duration>();
        for (int i = 0; i < songs.size(); i++) {
            newList.add(songs.get(i));
        }
        return newList;
    }


    @Override
    public String toString() {
        String ts = String.format(
            "Album [name=%s,band=%s,length=%d:%02d:%02d]",
            this.songName,
            this.band,
            this.getLength().toHours(),
            this.getLength().toMinutes(),
            this.getLength().toMinutes());
        return ts;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        else if (!obj.getClass().equals(Album.class)) {
            return false;
        }
        else {
            return true;

        }
    }

}
