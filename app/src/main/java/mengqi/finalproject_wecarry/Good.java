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

    public Good() {
        this("", "", "", "", "");
    }

    public Good(String departureArea, String arrivalArea, String datePreferred, String flexibility, String whatToCarry) {
        this.departureArea = departureArea;
        this.arrivalArea = arrivalArea;
        this.datePreferred = datePreferred;
        this.flexibility = flexibility;
        this.whatToCarry = whatToCarry;
    }
}
