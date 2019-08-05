package bd.dof.groupmessenger.groupmessengerforfishermen;

/**
 * Created by sagor on 6/22/2016.
 */
public class VillegeModel {
    private String villageID;
    private String unionID;
    private String upazillaID;
    private String districtID;
    private String divisionID;
    private String villageName;

    public void setVillageID(String villageID) {
        this.villageID = villageID;
    }

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

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public String getVillageID() {
        return villageID;
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

    public String getVillageName() {
        return villageName;
    }
}
