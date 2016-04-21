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


    public String getSpecialNote() {
        return specialNote;
    }

    public void setSpecialNote(String specialNote) {
        this.specialNote = specialNote;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDepartDate() {
        return departDate;
    }

    public void setDepartDate(String departDate) {
        this.departDate = departDate;
    }

    public String getSpaceAvailable() {
        return spaceAvailable;
    }

    public void setSpaceAvailable(String spaceAvailable) {
        this.spaceAvailable = spaceAvailable;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String specialNote;
    public String userName;



    public Flight() {
        this("", "", "", "", "", "", "");
    }

    public Flight(String departure, String arrival, String number, String departDate,
                  String spaceAvailable, String specialNote, String userName) {
        this.departure = departure;
        this.arrival = arrival;
        this.number = number;
        this.departDate = departDate;
        this.spaceAvailable = spaceAvailable;
        this.specialNote = specialNote;
        this.userName = userName;
        //this.fligtId=fligtId;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Flight)) {
            return false;
        }

        Flight flight = (Flight) object;
        return number.equals(flight.number);//there should be a particular var for each flight
    }

}
