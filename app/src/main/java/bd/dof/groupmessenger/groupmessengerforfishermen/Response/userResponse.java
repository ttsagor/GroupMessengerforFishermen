package bd.dof.groupmessenger.groupmessengerforfishermen.Response;

public class userResponse {
    String id;
    String division_id;
    String district_id;
    String upazilla_id;
    String user_name;
    String user_phone;
    String user_password;
    String status;
    String date;
    String time;
    String ip;

    public userResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDivision_id() {
        return division_id;
    }

    public void setDivision_id(String division_id) {
        this.division_id = division_id;
    }

    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    public String getUpazilla_id() {
        return upazilla_id;
    }

    public void setUpazilla_id(String upazilla_id) {
        this.upazilla_id = upazilla_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public userResponse(String id, String division_id, String district_id, String upazilla_id, String user_name, String user_phone, String user_password, String status, String date, String time, String ip) {
        this.id = id;
        this.division_id = division_id;
        this.district_id = district_id;
        this.upazilla_id = upazilla_id;
        this.user_name = user_name;
        this.user_phone = user_phone;
        this.user_password = user_password;
        this.status = status;
        this.date = date;
        this.time = time;
        this.ip = ip;
    }
}
