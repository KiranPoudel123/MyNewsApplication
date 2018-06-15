package myapp.training.newitventure.com.mynewsapplication.Remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit;
    public static Retrofit getRetrofitClient()
    {

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://my.ntentertainment.net/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        return retrofit;
    }

}
