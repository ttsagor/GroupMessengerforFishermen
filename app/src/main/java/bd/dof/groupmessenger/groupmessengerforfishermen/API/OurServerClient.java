package bd.dof.groupmessenger.groupmessengerforfishermen.API;

import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OurServerClient {

    /**
     * Change the Base Url From Here .
     *
     */

    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://batpars.com/m/")
            .client(new OkHttpClient.Builder()
                    .connectTimeout(160, TimeUnit.SECONDS)
                    .readTimeout(160,TimeUnit.SECONDS).build())
            .addConverterFactory(GsonConverterFactory.create( new GsonBuilder()
                    .setLenient()
                    .create()))
            .build();

    public static <S> S cteateService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
