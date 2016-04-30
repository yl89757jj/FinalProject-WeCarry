package mengqi.finalproject_wecarry;

/**
 * Created by Lu on 2/23/2016.
 */
public class Good {
    public String departureArea;
    public String arrivalArea;
    public String datePreferred;
    public String flexibility;
    public String whatToCarry;
    public String userName;
    public String photo;


    public Good() {
        this("", "", "", "", "", "", "");
    }

    public Good(String departureArea, String arrivalArea, String datePreferred, String flexibility, String whatToCarry, String userName, String photo) {
        this.departureArea = departureArea;
        this.arrivalArea = arrivalArea;
        this.datePreferred = datePreferred;
        this.flexibility = flexibility;
        this.whatToCarry = whatToCarry;
        this.userName = userName;
        this.photo = photo;
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
