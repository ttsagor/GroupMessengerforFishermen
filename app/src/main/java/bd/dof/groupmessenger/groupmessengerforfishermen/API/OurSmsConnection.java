package bd.dof.groupmessenger.groupmessengerforfishermen.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OurSmsConnection {
    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://71bulksms.com/sms_api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static <S> S cteateService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }

}
