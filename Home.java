package com.example.reservationportal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class Home<HomeViewHolder extends RecyclerView.ViewHolder> extends AppCompatActivity {
    RecyclerView recyclerView;
    private ProgressDialog progressDialog;
    private DatabaseReference mdatabasereference;
    FirebaseRecyclerAdapter<User,UserViewHolder> firebaseRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        progressDialog = new ProgressDialog(Home.this);
        progressDialog.setMessage("Loading ..Please Wait...");
        progressDialog.show();

        mdatabasereference = FirebaseDatabase.getInstance().getReference("Database");

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);


    }
    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<User> options = new FirebaseRecyclerOptions.Builder<User>()
                .setQuery(mdatabasereference, User.class)
                .build();
        Log.d("Options"," data : "+options);
        FirebaseRecyclerAdapter<User,UserViewHolder>firebaseRecyclerAdapter= new FirebaseRecyclerAdapter<User, UserViewHolder>
                (options) {
            @Override
            protected void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int position, @NonNull User user) {
                userViewHolder.setName(user.getName());
                userViewHolder.setEmail(user.getEmail());
                userViewHolder.setMobile(user.getMobile());
                userViewHolder.setSpinner(user.getSpinner());




            }

            @NonNull
            @Override
            public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.details_layout,parent,false);
                progressDialog.dismiss();
                return  new UserViewHolder(view);

            }
        };

        recyclerView.setAdapter(firebaseRecyclerAdapter);
        firebaseRecyclerAdapter.startListening();


    }

    public static class UserViewHolder extends RecyclerView.ViewHolder{
        View mView;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            mView=itemView;
        }
        public void setName(String name)
        {
            TextView ename=(TextView)mView.findViewById(R.id.name);
            ename.setText(name);
        }

        public void setMobile(String mobile) {
            TextView emobile=(TextView)mView.findViewById(R.id.mobile);
            emobile.setText(mobile);
        }

        public void setEmail(String email) {
            TextView eemail=(TextView)mView.findViewById(R.id.email);
            eemail.setText(email);
        }


        public void setSpinner(String spinner) {
            TextView espinner=(TextView)mView.findViewById(R.id.spinner);
            espinner.setText(spinner);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }


}
