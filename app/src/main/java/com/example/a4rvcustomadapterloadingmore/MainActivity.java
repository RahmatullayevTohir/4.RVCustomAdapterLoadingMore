package com.example.a4rvcustomadapterloadingmore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.util.Log;

import com.example.a4rvcustomadapterloadingmore.adapter.CustomAdapter;
import com.example.a4rvcustomadapterloadingmore.listener.onButtomReachedListener;
import com.example.a4rvcustomadapterloadingmore.model.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        List<User> users = prepareUserList();

        refreshAdapter(users);
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(context,1));
    }

    private void refreshAdapter(List<User> users){
        CustomAdapter adapter = new CustomAdapter(context, users, new onButtomReachedListener(){
            @Override
            public void onButtomReached(int position){
                Log.d("@@@","@@@position" +position);

            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                GridLayoutManager layoutManager=GridLayoutManager.class.cast(recyclerView.getLayoutManager());
                int totalItemCount = layoutManager.getItemCount();
                int lastVisible = layoutManager.findLastVisibleItemPosition();

                boolean endHasBeenReached = lastVisible + 5 >= totalItemCount;
                if (totalItemCount > 0 && endHasBeenReached) {
                    //you have reached to the bottom of your recycler view
                    adapter.setUsers(users);

                }
            }
        });
    }
    private List<User> prepareUserList(){
        List<User>users = new ArrayList<>();
        users.add(new User());

        for (int i=1; i<20;i++){
            if (i==2||i==5||i==10){
                users.add(new User("Android  "+i,"Tohir "+i,false));
            }else {
                users.add(new User("Android "+i,"B12 "+i,true));
            }
        }
        users.add(new User()); //  for Footer
        return users;
    }
}