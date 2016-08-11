package app;

import java.util.concurrent.atomic.AtomicInteger;

public class Patient {

    private final static AtomicInteger counter = new AtomicInteger();
    private final int id;
    private String firstName;
    private String lastName;

    public Patient(String firstName, String lastName) {
        this.id = counter.incrementAndGet();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
