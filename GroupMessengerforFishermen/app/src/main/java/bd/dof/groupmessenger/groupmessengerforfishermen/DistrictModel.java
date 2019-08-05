package bd.dof.groupmessenger.groupmessengerforfishermen;

/**
 * Created by sagor on 6/22/2016.
 */
public class DistrictModel {
    private String districtID;
    private String divisionID;
    private String districtName;

    public void setDistrictID(String districtID) {
        this.districtID = districtID;
    }

    public void setDivisionID(String divisionID) {
        this.divisionID = divisionID;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getDistrictID() {
        return districtID;
    }

    public String getDivisionID() {
        return divisionID;
    }

    public String getDistrictName() {
        return districtName;
    }
}
