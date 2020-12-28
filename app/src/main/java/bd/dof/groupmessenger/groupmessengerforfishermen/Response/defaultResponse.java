package bd.dof.groupmessenger.groupmessengerforfishermen.Response;

public class defaultResponse {
    String response;
    String data;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public defaultResponse(String response, String data) {
        this.response = response;
        this.data = data;
    }
}
