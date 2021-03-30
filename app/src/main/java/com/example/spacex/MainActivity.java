package com.example.spacex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private CrewMemberViewModel crewMemberViewModel;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all_data:
                crewMemberViewModel.delete();
                Toast.makeText(this, "All Data Delete", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        CrewMemberAdapter adapter = new CrewMemberAdapter();
        recyclerView.setAdapter(adapter);

        crewMemberViewModel = new ViewModelProvider(this).get(CrewMemberViewModel.class);
        crewMemberViewModel.getAllCrewMembers().observe(this, new Observer<List<CrewMember>>() {
            @Override
            public void onChanged(List<CrewMember> crewMembers) {

                adapter.setCrews(crewMembers);
                //update RecyclerView
                //Toast.makeText(MainActivity.this, "OnChanged", Toast.LENGTH_SHORT).show();
            }
        });

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.spacexdata.com/v4/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Crew>> call = jsonPlaceHolderApi.getCrewMembers();
        call.enqueue(new Callback<List<Crew>>() {
            @Override
            public void onResponse(Call<List<Crew>> call, Response<List<Crew>> response) {

                if (!response.isSuccessful()) {
                    //textViewResult.setText("Code: " + response.code());
                    Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Crew> crews = response.body();
                for (Crew crew : crews) {
//                    String content = "";
//                    content += "ID: " + crew.getId() +"\n";
//                    content += "Name: " + crew.getName() +"\n";
//                    content += "Agency: " + crew.getAgency() +"\n";
//                    content += "Wikipedia: " + crew.getWiki() +"\n";
//                    content += "Image: " + crew.getImg() +"\n";
//                    content += "Status: " + crew.getStatus() +"\n\n";
//
//                    Log.i("Data", content);

                    String id = crew.getId();
                    String name = crew.getName();
                    String agency = crew.getAgency();
                    String status = crew.getStatus();
                    String img = crew.getImg();
                    String wiki = crew.getWiki();

                    crewMemberViewModel.insert(new CrewMember(id, name, agency,img, wiki, status));

                }

            }

            @Override
            public void onFailure(Call<List<Crew>> call, Throwable t) {
                //textViewResult.setText(t.getMessage());
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}