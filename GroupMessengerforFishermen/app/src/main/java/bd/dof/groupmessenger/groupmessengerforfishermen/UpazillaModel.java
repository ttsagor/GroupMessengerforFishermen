package bd.dof.groupmessenger.groupmessengerforfishermen;

/**
 * Created by sagor on 6/22/2016.
 */
public class UpazillaModel {
    private String upazillaID;
    private String districtID;
    private String divisionID;
    private String upazillaName;

    public void setUpazillaID(String upazillaID) {
        this.upazillaID = upazillaID;
    }

    public void setDistrictID(String districtID) {
        this.districtID = districtID;
    }

    public void setDivisionID(String divisionID) {
        this.divisionID = divisionID;
    }

    public void setUpazillaName(String upazillaName) {
        this.upazillaName = upazillaName;
    }

    public String getUpazillaID() {
        return upazillaID;
    }

    public String getDistrictID() {
        return districtID;
    }

    public String getDivisionID() {
        return divisionID;
    }

    public String getUpazillaName() {
        return upazillaName;
    }
}
