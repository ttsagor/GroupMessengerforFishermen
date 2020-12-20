package bd.dof.groupmessenger.groupmessengerforfishermen.Response;

public class smsResponse {
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public smsResponse() {
    }

    public smsResponse(String message) {
        this.message = message;
    }
}
