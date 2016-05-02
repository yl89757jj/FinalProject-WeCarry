package mengqi.finalproject_wecarry;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lu on 2/23/2016.
 */
public class Flight implements Parcelable{
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

    private int mData;

    /* everything below here is for implementing Parcelable */

    // 99.9% of the time you can just ignore this
    @Override
    public int describeContents() {
        return 0;
    }

    // write your object's data to the passed-in Parcel
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(mData);
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<Flight> CREATOR = new Parcelable.Creator<Flight>() {
        public Flight createFromParcel(Parcel in) {
            return new Flight(in);
        }

        public Flight[] newArray(int size) {
            return new Flight[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private Flight(Parcel in) {
        mData = in.readInt();
    }
    
}
