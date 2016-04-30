package mengqi.finalproject_wecarry;

/**
 * Created by Lu on 2/23/2016.
 */
public class Flight {
    public String departure;
    public String arrival;
    public String number;
    public String departDate;
    public String spaceAvailable;
    public String specialNote;
    public String userEmail;



    public Flight() {
        this("", "", "", "", "", "","");
    }

    public Flight(String departure, String arrival, String number, String departDate,
                  String spaceAvailable, String specialNote, String userEmail) {
        this.departure = departure;
        this.arrival = arrival;
        this.number = number;
        this.departDate = departDate;
        this.spaceAvailable = spaceAvailable;
        this.specialNote = specialNote;
        this.userEmail=userEmail;

    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Flight)) {
            return false;
        }

        Flight flight = (Flight) object;
        return number.equals(flight.number);
    }

}
