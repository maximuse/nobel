package hu.nyirszikszi;

class Nobel {
    private int year;
    private String type;
    private String firstName;
    private String lastName;

    Nobel(int year, String type, String firstName, String lastName) {
        this.year = year;
        this.type = type;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    int getYear() {
        return year;
    }

    String getType() {
        return type;
    }

    String getFirstName() {
        return firstName;
    }

    String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Nobel{" +
                "year=" + year +
                ", type='" + type + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}