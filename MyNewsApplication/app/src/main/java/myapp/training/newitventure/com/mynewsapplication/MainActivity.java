package myapp.training.newitventure.com.mynewsapplication;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import myapp.training.newitventure.com.mynewsapplication.Remote.RetrofitClient;
import myapp.training.newitventure.com.mynewsapplication.Remote.RetrofitInterface;
import myapp.training.newitventure.com.mynewsapplication.adapter.MainAdapter;
import myapp.training.newitventure.com.mynewsapplication.model.News;
//import myapp.training.newitventure.com.mynewsapplication.model.NewsList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends NewsActivity {


    RetrofitInterface retrofitInterface;
    public RecyclerView recyclerView;


    List<News> newsList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        printKeyHash();
        getNewsList();

    }

    private void getNewsList() {
        retrofitInterface = RetrofitClient.getRetrofitClient().create(RetrofitInterface.class);
        Call<List<News>> call = retrofitInterface.getNewsData();

        call.enqueue(new Callback<List<News>>() {
            @Override

            public void onResponse(@NonNull Call<List<News>> call, @NonNull Response<List<News>> response) {

                try {
                    newsList = response.body();

                    recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                    MainAdapter mainAdapter = new MainAdapter(getApplicationContext(), (List<News>) newsList);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(mainAdapter);


                } catch (Exception e) {
                    Log.d("onResponse", "There is an error");
                    e.printStackTrace();
                }
            }

            @Override

            public void onFailure(@NonNull Call<List<News>> call, @NonNull Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void printKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo("myapp.training.newitventure.com.mynewsapplication",
                    PackageManager.GET_SIGNATURES);

            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));

            }

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
  }


