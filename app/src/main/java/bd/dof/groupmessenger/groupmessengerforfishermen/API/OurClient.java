package bd.dof.groupmessenger.groupmessengerforfishermen.API;

import java.util.ArrayList;


import bd.dof.groupmessenger.groupmessengerforfishermen.Response.userResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OurClient {

    @GET("auth/userLogin.php")
    Call<ArrayList<userResponse>> getLogin(@Query("phone_no") String phone_no, @Query("password") String password);


}
