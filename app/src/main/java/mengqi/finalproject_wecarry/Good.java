package mengqi.finalproject_wecarry;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lu on 2/23/2016.
 */
public class Good implements Parcelable{

    public String departureArea;
    public String arrivalArea;
    public String datePreferred;
    public String flexibility;
    public String whatToCarry;
    public String userEmail;
    public String photo;



    public Good() {
        this("", "", "", "", "","","");
    }

    public Good(String departureArea, String arrivalArea, String datePreferred, String flexibility, String whatToCarry, String userEmail,String photo) {
        this.departureArea = departureArea;
        this.arrivalArea = arrivalArea;
        this.datePreferred = datePreferred;
        this.flexibility = flexibility;
        this.whatToCarry = whatToCarry;
        this.photo=photo;
        this.userEmail=userEmail;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Good)) {
            return false;
        }

        Good good = (Good) object;
        return whatToCarry == good.whatToCarry;
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
    public static final Parcelable.Creator<Good> CREATOR = new Parcelable.Creator<Good>() {
        public Good createFromParcel(Parcel in) {
            return new Good(in);
        }

        public Good[] newArray(int size) {
            return new Good[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private Good(Parcel in) {
        mData = in.readInt();
    }
}
