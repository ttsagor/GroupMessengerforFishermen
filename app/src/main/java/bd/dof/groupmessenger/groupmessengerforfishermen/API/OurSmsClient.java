package bd.dof.groupmessenger.groupmessengerforfishermen.API;

import org.apache.xmlbeans.impl.jam.JParameter;

import java.util.Map;

import bd.dof.groupmessenger.groupmessengerforfishermen.Response.smsResponse;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface OurSmsClient {

    //send sms system
    @POST("bulk_sms_sender_2.php")
    @FormUrlEncoded
    Call<smsResponse> sendSmsToFarmers(@FieldMap Map<String,String> parameter);
}

