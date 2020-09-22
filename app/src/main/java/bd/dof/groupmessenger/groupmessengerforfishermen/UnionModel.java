package bd.dof.groupmessenger.groupmessengerforfishermen;

/**
 * Created by sagor on 6/22/2016.
 */
public class UnionModel {
    private String unionID;
    private String upazillaID;
    private String districtID;
    private String divisionID;
    private String unionName;

    public void setUnionID(String unionID) {
        this.unionID = unionID;
    }

    public void setUpazillaID(String upazillaID) {
        this.upazillaID = upazillaID;
    }

    public void setDistrictID(String districtID) {
        this.districtID = districtID;
    }

    public void setDivisionID(String divisionID) {
        this.divisionID = divisionID;
    }

    public void setUnionName(String unionName) {
        this.unionName = unionName;
    }

    public String getUnionID() {
        return unionID;
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

    public String getUnionName() {
        return unionName;
    }
}
