package mengqi.finalproject_wecarry;

/**
 * Created by Lu on 2/23/2016.
 */
public class Good {
    public String departureArea;
    public String arrivalArea;

    public String getDatePreferred() {
        return datePreferred;
    }

    public void setDatePreferred(String datePreferred) {
        this.datePreferred = datePreferred;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDepartureArea() {
        return departureArea;
    }

    public void setDepartureArea(String departureArea) {
        this.departureArea = departureArea;
    }

    public String getArrivalArea() {
        return arrivalArea;
    }

    public void setArrivalArea(String arrivalArea) {
        this.arrivalArea = arrivalArea;
    }

    public String getFlexibility() {
        return flexibility;
    }

    public void setFlexibility(String flexibility) {
        this.flexibility = flexibility;
    }

    public String getWhatToCarry() {
        return whatToCarry;
    }

    public void setWhatToCarry(String whatToCarry) {
        this.whatToCarry = whatToCarry;
    }

    public String datePreferred;
    public String flexibility;
    public String whatToCarry;
    public String userName;


    public Good() {
        this("", "", "", "", "", "");
    }

    public Good(String departureArea, String arrivalArea, String datePreferred, String flexibility, String whatToCarry, String userName) {
        this.departureArea = departureArea;
        this.arrivalArea = arrivalArea;
        this.datePreferred = datePreferred;
        this.flexibility = flexibility;
        this.whatToCarry = whatToCarry;
        this.userName = userName;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Good)) {
            return false;
        }

        Good good = (Good) object;
        return whatToCarry == good.whatToCarry;//there should be a particular var for each good
    }
}
