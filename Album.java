package mediaRentalManager;

public class Album extends Media {
    private String artist;
    private String songs; // Comma-separated list of song titles

    // Constructor with validation
    public Album(String title, int copiesAvailable, String artist, String songs) {
        super(title, copiesAvailable);
        if (artist == null || artist.trim().isEmpty()) {
            throw new IllegalArgumentException("Artist cannot be null or empty.");
        }
        if (songs == null || songs.trim().isEmpty()) {
            throw new IllegalArgumentException("Songs cannot be null or empty.");
        }
        this.artist = artist.trim();
        this.songs = songs.trim();
    }

    // Getters
    public String getArtist() {
        return artist;
    }

    public String getSongs() {
        return songs;
    }

    // Override toString to include Album-specific details
    @Override
    public String toString() {
        return super.toString() + ", Artist: " + artist + ", Songs: " + songs;
    }
}
