package myapp.training.newitventure.com.mynewsapplication.Remote;

import java.util.List;

import myapp.training.newitventure.com.mynewsapplication.model.News;
//import myapp.training.newitventure.com.mynewsapplication.model.NewsList;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {

    /**
     * @return
     */
    @GET("news?source[]=nepaljapan")

    Call<List<News>> getNewsData();
}
