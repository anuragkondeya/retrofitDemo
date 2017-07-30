package com.anuragkondeya.example.retrofitsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ListView repoListView;
    private final String BASEURL = "https://api.github.com";
    private ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        repoListView = (ListView) findViewById(R.id.repoList);
        mProgressBar = (ProgressBar) findViewById(R.id.progress);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();


        GithubClient githubClient = retrofit.create(GithubClient.class);
        Call<List<GitHubRepo>> call = githubClient.reposForUser("anuragkondeya");
        call.enqueue(new Callback<List<GitHubRepo>>() {
            @Override
            public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {

                List<GitHubRepo> repoList = response.body();
                repoListView.setAdapter(new GithubRepoListViewAdapter(MainActivity.this,repoList));
                mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Error ",Toast.LENGTH_LONG).show();
            }
        });

    }
}
