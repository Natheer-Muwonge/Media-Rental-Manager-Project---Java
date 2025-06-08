package mediaRentalManager;

import java.util.ArrayList;

public class Customer implements Comparable<Customer> {
    // The name of the customer
    private String name;
    // The address of the customer
    private String address;
    // The plan type of the customer, "LIMITED" or "UNLIMITED"
    private String plan;
    // List to store the media titles the customer has requested
    private ArrayList<String> queue;
    // List to store the media titles the customer has rented
    private ArrayList<String> rented;

    // Constructor to initialize the customer's name, address, plan, and lists
    public Customer(String name, String address, String plan) {
        this.name = name;
        this.address = address;
        this.plan = plan;
        this.queue = new ArrayList<>();
        this.rented = new ArrayList<>();
    }

    // Getter method for the customer's name
    public String getName() {
        return name;
    }

    // Getter method for the customer's address
    public String getAddress() {
        return address;
    }

    // Getter method for the customer's plan
    public String getPlan() {
        return plan;
    }

    // Getter method for the customer's request queue
    public ArrayList<String> getQueue() {
        return queue;
    }

    // Getter method for the customer's rented list
    public ArrayList<String> getRented() {
        return rented;
    }

    // Adds a title to the customer's queue if not already present
    public boolean addToQueue(String mediaTitle) {
        if (queue.contains(mediaTitle)) {
            return false;
        }
        queue.add(mediaTitle);
        return true;
    }

    // Removes a title from the customer's queue
    public boolean removeFromQueue(String mediaTitle) {
        return queue.remove(mediaTitle);
    }

    // Adds a title to the customer's rented list
    public void rentMedia(String mediaTitle) {
        rented.add(mediaTitle);
    }

    // Removes a title from the customer's rented list
    public boolean returnMedia(String mediaTitle) {
        return rented.remove(mediaTitle);
    }

    // Returns a string representation of the customer
    @Override
    public String toString() {
        return "Name: " + name + ", Address: " + address + ", Plan: " + plan + "\n" +
               "Rented: " + rented + "\n" +
               "Queue: " + queue;
    }

    // Compares customers based on their names
    @Override
    public int compareTo(Customer other) {
        return this.name.compareTo(other.getName());
    }
}
