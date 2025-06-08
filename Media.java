package mediaRentalManager;

public class Media implements Comparable<Media> {
    // The title of the media item
    protected String title;
    // The number of copies available for the media item
    protected int copiesAvailable;

    // Constructor to initialize the title and copies available for the media item
    public Media(String title, int copiesAvailable) {
        this.title = title;
        this.copiesAvailable = copiesAvailable;
    }

    // Getter method for the title of the media item
    public String getTitle() {
        return title;
    }

    // Getter method for the number of copies available for the media item
    public int getCopiesAvailable() {
        return copiesAvailable;
    }

    // Adjust the number of available copies
    public void rentCopy() {
        if (copiesAvailable > 0) {
            copiesAvailable--;
        } else {
            throw new IllegalStateException("No copies available to rent.");
        }
    }

    public void returnCopy() {
        copiesAvailable++;
    }

    // Returns a string representation of the media item
    @Override
    public String toString() {
        return "Title: " + title + ", Copies Available: " + copiesAvailable;
    }

    // Compare media items based on the title
    @Override
    public int compareTo(Media other) {
        return this.title.compareToIgnoreCase(other.getTitle());
    }
}
