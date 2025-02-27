package org.example.databases.JDBI;

public class Personnel {
    private int id;
    private String firstName;
    private String lastName;
    private String position;

    public Personnel() {}

    public Personnel(String firstName, String lastName, String position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Personnel{firstName='" + firstName + "', lastName='" + lastName + "', position='" + position + "'}";
    }
}


