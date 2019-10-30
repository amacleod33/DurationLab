import java.util.ArrayList;
import java.time.Duration;
/**
 *  Write a one-sentence summary of your class here.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 * 
 *  @author 
 *  @version 
 */
public class Album {
    private String name;
    private String band;
    private ArrayList<Duration> songs = new ArrayList<Duration>();
    public Album() {
        name = "";
        band = "";
    }
    public Album(String name, String band) {
        if (name == null) {
            throw new IllegalArgumentException("name cannot be null");
        }
        else if (band == null) {
            throw new IllegalArgumentException("band cannot be null");
        }
        this.name = name;
        this.band = band;
    }
    
    /**
     * Get the current value of name.
     * @return The value of name for this object.
     */
    public String getName() {
        return name;
    }
    
    /**
     * Set the value of name for this object.
     * @param name The new value for name.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Get the current value of band.
     * @return The value of band for this object.
     */
    public String getBand() {
        return band;
    }
    
    /**
     * Set the value of band for this object.
     * @param band The new value for band.
     */
    public void setBand(String band) {
        this.band = band;
    }
    // use toMinutes() % 60 to get remaining minutes
    public Duration getLength() {
        Duration completeLength = Duration.ofMinutes(0).plusSeconds(0);
        for (int i = 0; i < songs.size(); i++) {
            Duration fullDuration = Duration.ofMinutes(songs.get(i).toMinutes() % 60).plusSeconds(songs.get(i).getSeconds() % 60);
            completeLength = completeLength.plus(fullDuration);
        }
        return completeLength;
    }
    public void addSong(Duration length) {
        if (length == null) {
            throw new IllegalArgumentException("length cannot be null");
        }
        songs.add(length);
    }
    public ArrayList<Duration> getSongs(){
        ArrayList<Duration> newList = new ArrayList<Duration>();
        for (int i = 0; i < songs.size(); i++) {
            newList.add(songs.get(i));
        }
        return newList;
    }
    @Override
    public String toString() {
        String ts = String.format("Album [name=%s,band=%s,length=%d:%02d:%02d]", this.name, this.band, this.getLength().toHours(), this.getLength().toMinutes() % 60, this.getLength().toSeconds() % 60);
        return ts;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!obj.getClass().equals(Album.class)) {
            return false;
        }
        else {
            Album o = (Album) obj;
            if (this.name.equals(o.name) && this.band.equals(o.band)) {
                return true;
            }
        return false;
        }
    }
    
}
