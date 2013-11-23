package logic;
/**
 * Created with IntelliJ IDEA.
 * logic.User: St1ch
 * Date: 31.10.13
 * Time: 14:14
 * Package name: PACKAGE_NAME
 * Project name: VkMusicApplication
 */

/**
 * This class represents a VkAudio object
 */
public class VkAudio
{
    private long aid;
    private long owner_id;
    private String artist;
    private String title;
    private int duration;
    private String url;
    private int lyrics_id;
    private int album;
    private int genre;

    /**
     * @param aid       record id
     * @param owner_id  user id
     * @param artist    artist
     * @param title     the title of the song
     * @param duration  duration
     * @param url       record url
     * @param lyrics_id lyrics id
     * @param album     album id
     * @param genre     genre id
     */
    public VkAudio(long aid, long owner_id, String artist, String title, int duration, String url, int lyrics_id, int album, int genre)
    {
        this.aid = aid;
        this.owner_id = owner_id;
        this.artist = artist;
        this.title = title;
        this.duration = duration;
        this.url = url;
        this.lyrics_id = lyrics_id;
        this.album = album;
        this.genre = genre;
    }

    public long getAid()
    {
        return aid;
    }

    public long getOwner_id()
    {
        return owner_id;
    }

    public String getArtist()
    {
        return artist;
    }

    public String getTitle()
    {
        return title;
    }

    public int getDuration()
    {
        return duration;
    }

    public String getUrl()
    {
        return url;
    }

    public int getLyrics_id()
    {
        return lyrics_id;
    }

    public int getAlbum()
    {
        return album;
    }

    public int getGenre()
    {
        return genre;
    }

    @Override
    public String toString()
    {
        return "logic.VkAudio[" +
                "aid=" + aid +
                ", owner_id=" + owner_id +
                ", artist='" + artist + '\'' +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                ", url='" + url + '\'' +
                ", lyrics_id=" + lyrics_id +
                ", album=" + album +
                ", genre=" + genre +
                ']';
    }

}
