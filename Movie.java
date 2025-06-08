package mediaRentalManager;
public class Movie extends Media {
    private String rating;

    public Movie(String title, int copiesAvailable, String rating) {
        super(title, copiesAvailable);
        this.rating = rating;
    }

    public String getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return super.toString() + ", Rating: " + rating;
    }
}
