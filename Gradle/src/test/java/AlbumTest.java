import static org.junit.Assert.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Tests for Album, includes standard coding practice checks.
 * 
 * @author raflores
 * @version 20191021
 */
public class AlbumTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();


    /**
     * Create a list of E. Use this instead of List.of(E... e)
     *
     * @param e
     *            vargargs strings from which to build a list
     * @return list of E
     */
    @SafeVarargs
	private static <E> List<E> listOf(E... e) {
        List<E> l = new ArrayList<E>();
        for (E s : e) {
            if (s == null) {
                throw new NullPointerException("listOf element is null");
            }
            l.add(s);
        }
        return l;
    }


    private <
        T> void assertListEqualsUnordered(List<T> expected, List<T> actual) {
        assertNotNull("actual was null", actual);
        List<T> aList = new ArrayList<>(actual);
        List<T> eList = new ArrayList<>(expected);
        while (!eList.isEmpty()) {
            T e = eList.remove(0);
            if (aList.isEmpty()) {
                String msg = String.format(
                    "missing results %s%n" + "expected %s%n" + "actual %s%n",
                    eList,
                    expected,
                    actual);
                fail(msg);
            }
            else {
                String msg = String.format(
                    "'%s' not found.%n" + "expected %s%n" + "actual %s%n"
                        + "remaining expected %s%n" + "remaining actual %s",
                    e,
                    expected,
                    actual,
                    eList,
                    aList);
                assertTrue(msg, aList.remove(e));
            }
        }
        if (!aList.isEmpty()) {
            String msg = String.format(
                "excess results %s%n" + "expected %s%n" + "actual %s",
                aList,
                expected,
                actual);
            fail(msg);
        }
    }


    @Test
    public void testAllFieldsPrivateNonStatic() {
        Class<?> iClass = Album.class;
        Field[] iFields = iClass.getDeclaredFields();

        for (Field f : iFields) {
            if (!f.isSynthetic()) {
                assertTrue(
                    "Field \"" + f.getName() + "\" should be private",
                    Modifier.isPrivate(f.getModifiers()));
                assertFalse(
                    "Field \"" + f.getName() + "\" can't be static",
                    Modifier.isStatic(f.getModifiers()));
            }
        }
    }


    @Test
    public void testNameNull() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("name cannot be null");
        new Album(null, "Led Zeppelin");
    }


    @Test
    public void testBandNull() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("band cannot be null");
        new Album("Back in Black", null);
    }


    @Test
    public void testNewAlbum() {
        String name = "Guitar Heaven: The Greatest Guitar Classics of All Time";
        String band = "Santana";
        Album album = new Album(new String(name), new String(band));
        {
            String actual = album.getName();
            assertEquals("incorrect result", name, actual);
        }
        {
            String actual = album.getBand();
            assertEquals("incorrect result", band, actual);
        }
        {
            Duration expected = Duration.ZERO;
            Duration actual = album.getLength();
            assertEquals("incorrect result", expected, actual);
        }
        {
            List<Duration> expected = listOf();
            List<Duration> actual = album.getSongs();
            assertListEqualsUnordered(expected, actual);
        }
    }


    @Test
    public void testAddSongNull() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("length cannot be null");
        new Album("Complete Greatest Hits", "The Cars").addSong(null);
    }


    @Test
    public void testAddOneSongGetLength() {
        Album album = new Album("Complete Greatest Hits", "The Cars");
        album.addSong(Duration.ofMinutes(4).plusSeconds(56));

        Duration expected = Duration.ofMinutes(4).plusSeconds(56);
        Duration actual = album.getLength();
        assertEquals("incorrect result", expected, actual);
    }


    @Test
    public void testAddOneSongGetSongs() {
        Album album = new Album("Complete Greatest Hits", "The Cars");
        album.addSong(Duration.ofMinutes(4).plusSeconds(56));

        List<Duration> expected =
            listOf(Duration.ofMinutes(4).plusSeconds(56));
        List<Duration> actual = album.getSongs();
        assertListEqualsUnordered(expected, actual);
    }


    @Test
    public void testAddTwoSongsGetLength() {
        Album album = new Album("Greatest Hits", "ZZ Top");
        album.addSong(Duration.ofMinutes(3).plusSeconds(59));
        album.addSong(Duration.ofMinutes(4).plusSeconds(15));

        Duration expected = Duration.ofMinutes(8).plusSeconds(14);
        Duration actual = album.getLength();
        assertEquals("incorrect result", expected, actual);
    }


    @Test
    public void testAddTwoSongsGetSongs() {
        Album album = new Album("Greatest Hits", "ZZ Top");
        album.addSong(Duration.ofMinutes(3).plusSeconds(59));
        album.addSong(Duration.ofMinutes(4).plusSeconds(15));

        List<Duration> expected = listOf(
            Duration.ofMinutes(3).plusSeconds(59),
            Duration.ofMinutes(4).plusSeconds(15));
        List<Duration> actual = album.getSongs();
        assertListEqualsUnordered(expected, actual);
    }


    @Test
    public void testAddManySongsGetLength() {
        Album album = new Album("Queen: Greatest Hits I & II", "Queen");
        album.addSong(Duration.ofMinutes(5).plusSeconds(58));
        album.addSong(Duration.ofMinutes(3).plusSeconds(37));
        album.addSong(Duration.ofMinutes(4).plusSeconds(16));
        album.addSong(Duration.ofMinutes(3).plusSeconds(1));
        album.addSong(Duration.ofMinutes(3).plusSeconds(30));
        album.addSong(Duration.ofMinutes(2).plusSeconds(24));
        album.addSong(Duration.ofMinutes(4).plusSeconds(54));
        album.addSong(Duration.ofMinutes(3).plusSeconds(32));
        album.addSong(Duration.ofMinutes(2).plusSeconds(3));
        album.addSong(Duration.ofMinutes(3).plusSeconds(1));
        album.addSong(Duration.ofMinutes(4).plusSeconds(3));
        album.addSong(Duration.ofMinutes(5).plusSeconds(43));
        album.addSong(Duration.ofMinutes(4).plusSeconds(2));
        album.addSong(Duration.ofMinutes(4).plusSeconds(23));
        album.addSong(Duration.ofMinutes(4).plusSeconds(15));
        album.addSong(Duration.ofMinutes(3).plusSeconds(1));

        Duration expected = Duration.ofHours(1).plusMinutes(1).plusSeconds(43);
        Duration actual = album.getLength();
        assertEquals("incorrect result", expected, actual);
    }


    @Test
    public void testAddFourSongsGetLength() {
        Album album = new Album("Queen: Greatest Hits I & II", "Queen");
        album.addSong(Duration.ofMinutes(5).plusSeconds(58));
        album.addSong(Duration.ofMinutes(3).plusSeconds(37));
        album.addSong(Duration.ofMinutes(4).plusSeconds(16));
        album.addSong(Duration.ofMinutes(3).plusSeconds(1));
        album.addSong(Duration.ofMinutes(3).plusSeconds(30));
        album.addSong(Duration.ofMinutes(2).plusSeconds(24));
        album.addSong(Duration.ofMinutes(4).plusSeconds(54));
        album.addSong(Duration.ofMinutes(3).plusSeconds(32));
        album.addSong(Duration.ofMinutes(2).plusSeconds(3));
        album.addSong(Duration.ofMinutes(3).plusSeconds(1));
        album.addSong(Duration.ofMinutes(4).plusSeconds(3));
        album.addSong(Duration.ofMinutes(5).plusSeconds(43));
        album.addSong(Duration.ofMinutes(4).plusSeconds(2));
        album.addSong(Duration.ofMinutes(4).plusSeconds(23));
        album.addSong(Duration.ofMinutes(4).plusSeconds(15));
        album.addSong(Duration.ofMinutes(3).plusSeconds(1));

        List<Duration> expected = listOf(
            Duration.ofMinutes(5).plusSeconds(58),
            Duration.ofMinutes(3).plusSeconds(37),
            Duration.ofMinutes(4).plusSeconds(16),
            Duration.ofMinutes(3).plusSeconds(1),
            Duration.ofMinutes(3).plusSeconds(30),
            Duration.ofMinutes(2).plusSeconds(24),
            Duration.ofMinutes(4).plusSeconds(54),
            Duration.ofMinutes(3).plusSeconds(32),
            Duration.ofMinutes(2).plusSeconds(3),
            Duration.ofMinutes(3).plusSeconds(1),
            Duration.ofMinutes(4).plusSeconds(3),
            Duration.ofMinutes(5).plusSeconds(43),
            Duration.ofMinutes(4).plusSeconds(2),
            Duration.ofMinutes(4).plusSeconds(23),
            Duration.ofMinutes(4).plusSeconds(15),
            Duration.ofMinutes(3).plusSeconds(1));
        List<Duration> actual = album.getSongs();
        assertListEqualsUnordered(expected, actual);
    }


    @Test
    public void testGetSongsReturnsNewList() {
        Album album = new Album("Greatest Hits", "ZZ Top");
        album.addSong(Duration.ofMinutes(3).plusSeconds(59));
        album.addSong(Duration.ofMinutes(4).plusSeconds(15));
        {
            List<Duration> actual = album.getSongs();
            assertEquals("unexpected result", 2, actual.size());
            actual.add(Duration.ofMinutes(42));
        }
        {
            List<Duration> actual = album.getSongs();
            assertEquals("unexpected result", 2, actual.size());
        }
    }


    @Test
    public void testEqualsToNull() {
        boolean expected = false;
        boolean actual = new Album(
            "Sultans of Swing: The Very Best of Dire Straits",
            "Dire Straits").equals(null);
        assertEquals("unexpected result", expected, actual);
    }


    @Test
    public void testEqualsReflectivity() {
        Object one = new Album("Slippery When Wet", "Bon Jovi");
        assertEquals("unexpected result", one, one);
    }


    @Test
    public void testEqualsSymmetryEqualState() {
        Object one = new Album(
            new String("Beauty and the Beat"),
            new String("The Go-Go's"));
        Object two = new Album(
            new String("Beauty and the Beat"),
            new String("The Go-Go's"));
        assertEquals("unexpected result", one, two);
    }


    @Test
    public void testEqualsSymmetryDifferentType() {
        Object one = new Album("Cosmic Thing", "The B-52's");
        assertNotEquals(
            "unexpected result",
            one,
            Double.toString(new Random().nextDouble()));
        assertNotEquals("unexpected result", one, Integer.valueOf(1));
    }


    @Test
    public void testEqualsSymmetryDifferentState() {
        Object one = new Album("Greatest Hits", "The Bangles");
        {
            Object two = new Album("All Over the Place", "The Bangles");
            assertNotEquals("unexpected result", one, two);
        }
        {
            Object two = new Album("Greatest Hits", "ZZ Top");
            assertNotEquals("unexpected result", one, two);
        }
    }


    @Test
    public void testEqualsSymmetryDifferentClass() {
        Object one = new Album("1984", "Van Halen");
        Object two = new Album("1984", "Van Halen") {
        };
        assertNotEquals("unexpected result", one, two);
    }


    @Test
    public void testToStringOne() {
        Album album = new Album("Bulletproof Dreams", "Minutes Til Midnight");
        String expected =
            "Album [name=Bulletproof Dreams,band=Minutes Til Midnight,length=0:03:02]";
        album.addSong(Duration.ofMinutes(3).plusSeconds(2));
        String actual = album.toString();
        assertEquals("unexpected result", expected, actual);
    }


    @Test
    public void testToStringTwo() {
        Album album = new Album("1984", "Van Halen");
        album.addSong(Duration.ofMinutes(42).plusSeconds(1));
        album.addSong(Duration.ofMinutes(20).plusSeconds(2));
        String expected = "Album [name=1984,band=Van Halen,length=1:02:03]";
        String actual = album.toString();
        assertEquals("unexpected result", expected, actual);
    }
}
