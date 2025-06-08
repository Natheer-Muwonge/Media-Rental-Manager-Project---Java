package mediaRentalManager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MediaRentalManager implements MediaRentalManagerInt {

    private int limitedPlanLimit=2; // Default maximum for LIMITED plans
    private List<Customer> customers; // List to store customer data
    private List<Media> media; // List to store media data

    // Constructor
    public MediaRentalManager() {
        this.limitedPlanLimit = 2; // Default limit
        this.customers = new ArrayList<>(); // Initialize customer list
        this.media = new ArrayList<>(); // Initialize media list
    }
	@Override
	public void addCustomer(String name, String address, String plan) {

		customers.add(new Customer(name, address, plan));
	}

	// Add movie
	@Override
	public void addMovie(java.lang.String title, int copiesAvailable, java.lang.String rating) {

		media.add(new Movie(title, copiesAvailable, rating));

	}

	@Override
	public void addAlbum(java.lang.String title, int copiesAvailable, java.lang.String artist, java.lang.String songs) {

		media.add(new Album(title, copiesAvailable, artist, songs));

	}


	// Process requests
	@Override
	public String processRequests() {
	    // Sort customers alphabetically
	    customers.sort(Comparator.comparing(Customer::getName));

	    StringBuilder result = new StringBuilder();

	    for (Customer customer : customers) {
	        // Get customer's request and rented queues
	        ArrayList<String> requestedQueue = customer.getQueue();
	        ArrayList<String> rentedQueue = customer.getRented();
	        String plan = customer.getPlan();

	        // Determine remaining limit based on the customer's plan
	        int rentedCount = rentedQueue.size();
	        int remainingLimit = plan.equalsIgnoreCase("LIMITED") 
	                ? limitedPlanLimit - rentedCount 
	                : Integer.MAX_VALUE;

	        // Create a copy of the request queue to avoid modification during iteration
	        ArrayList<String> requestQueueCopy = new ArrayList<>(requestedQueue);

	        for (String mediaTitle : requestQueueCopy) {
	            if (remainingLimit <= 0) {
	                break; // Stop processing if no more rentals are allowed
	            }

	            Media media = findMediaByTitle(mediaTitle);
	            if (media != null && media.getCopiesAvailable() > 0) {
	                // Rent the media
	                media.rentCopy(); // Decrease available copies
	                rentedQueue.add(mediaTitle); // Add to rented queue
	                requestedQueue.remove(mediaTitle); // Remove from request queue
	                remainingLimit--; // Decrement remaining limit

	                // Append result for this rental
	                result.append("Sending ").append(mediaTitle)
	                      .append(" to ").append(customer.getName()).append("\n");
	            }
	        }
	    }

	    return result.toString().trim();
	}




	// Get all customers
	@Override
	public String getAllCustomersInfo() {
	    customers.sort(Comparator.comparing(Customer::getName)); // Alphabetical order
	    StringBuilder result = new StringBuilder("***** Customers' Information *****\n");

	    for (Customer customer : customers) {
	        result.append(customer.toString()).append("\n");
	    }
	    return result.toString().trim();
	}

	@Override
	public String getAllMediaInfo() {
	    media.sort(Comparator.comparing(Media::getTitle)); // Alphabetical order
	    StringBuilder result = new StringBuilder("***** Media Information *****\n");

	    for (Media item : media) {
	        result.append(item.toString()).append("\n");
	    }
	    return result.toString().trim();
	}


	// Search media
	@Override
	public ArrayList<String> searchMedia(String title, String rating, String artist, String songs) {
	    ArrayList<String> results = new ArrayList<>();
	    for (Media item : media) {
	        if ((title == null || item.getTitle().toLowerCase().contains(title.toLowerCase()))
	                && (rating == null || (item instanceof Movie && ((Movie) item).getRating().equalsIgnoreCase(rating)))
	                && (artist == null || (item instanceof Album && ((Album) item).getArtist().toLowerCase().contains(artist.toLowerCase())))
	                && (songs == null || (item instanceof Album && ((Album) item).getSongs().toLowerCase().contains(songs.toLowerCase())))) {
	            results.add(item.getTitle());
	        }
	    }
	    results.sort(String::compareToIgnoreCase);
	    return results;
	}


	// Return media
	@Override
	public boolean returnMedia(String customerName, String mediaTitle) {
	    // Find the customer and the rented media by their respective names
	    Customer customer = findCustomerByName(customerName);
	    Media rentedMedia = findMediaByTitle(mediaTitle);

	    if (customer != null && rentedMedia != null) {
	        // Loop through the rented list to find the specified media
	        for (int i = 0; i < customer.getRented().size(); i++) {
	            if (customer.getRented().get(i).equals(mediaTitle)) {
	                // Remove the media from the customer's rented list
	                customer.getRented().remove(i);

	                // Increase the available copies of the media
	                rentedMedia.returnCopy();

	                // Return true to indicate the media was successfully returned
	                return true;
	            }
	        }
	    }
	    // Return false if the customer or media was not found or if the media wasn't rented
	    return false;
	}

	// Add to queue
	@Override
	public boolean addToQueue(String customerName, String mediaTitle) {
	    Customer customer = findCustomerByName(customerName);
	    if (customer != null) {
	    	for(int i = 0; i < customer.getQueue().size(); i++) {
	    		if(customer.getQueue().get(i).equals(mediaTitle)){
	    			return false;
	    		}
	    	}
	    	customer.getQueue().add(mediaTitle);
	    	return true;
	    }
	    return false;
	}


	@Override
	public boolean removeFromQueue(String customerName, String mediaTitle) {
	    Customer customer = findCustomerByName(customerName);
	    if (customer != null) {
	    	for(int i = 0; i < customer.getQueue().size(); i++) {
	    		if(customer.getQueue().get(i).equals(mediaTitle)){
	    			customer.getQueue().remove(i);
	    			return true;
	    		}
	    	}

	    }
	    return false;
	}
	   

	// Utility to find media by title
	private Media findMediaByTitle(String title) {
		for (Media item : media) {
			if (item.getTitle().equalsIgnoreCase(title)) {
				return item;
			}
		}
		return null;
	}

	// Utility to find customer by name
	private Customer findCustomerByName(String name) {
		for (Customer customer : customers) {
			if (customer.getName().equalsIgnoreCase(name)) {
				return customer;
			}
		}
		return null;
	}
	// Set plan limit
		public void setLimitedPlanLimit(int limit) {
		
			limitedPlanLimit = limit;
		}
}
