package bd.dof.groupmessenger.groupmessengerforfishermen;

/**
 * Created by sagor on 6/22/2016.
 */
public class SystemInformationModel {
    private int userID;
    private String userName;
    private String userPhoneNumber;
    private String userDivisionID;
    private String userDistrictID;
    private String userUpazillaID;
    private int dataLoaded;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserID() {
        return userID;
    }

    public String getUserDivisionID() {
        return userDivisionID;
    }

    public String getUserDistrictID() {
        return userDistrictID;
    }

    public String getUserUpazillaID() {
        return userUpazillaID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setUserDivisionID(String userDivisionID) {
        this.userDivisionID = userDivisionID;
    }

    public void setUserDistrictID(String userDistrictID) {
        this.userDistrictID = userDistrictID;
    }

    public void setUserUpazillaID(String userUpazillaID) {
        this.userUpazillaID = userUpazillaID;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public void setDataLoaded(int dataLoaded) {
        this.dataLoaded = dataLoaded;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public int getDataLoaded() {
        return dataLoaded;
    }
}
