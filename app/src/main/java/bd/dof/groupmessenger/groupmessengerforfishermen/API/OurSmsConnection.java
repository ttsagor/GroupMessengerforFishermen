package bd.dof.groupmessenger.groupmessengerforfishermen.API;

public class OurSmsConnection {
    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://esms.dianahost.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static <S> S cteateService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);

}
